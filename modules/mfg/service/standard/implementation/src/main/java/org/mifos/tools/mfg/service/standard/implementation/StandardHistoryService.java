///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.standard.implementation;

import static org.mifos.commons.boot.core.exception.MifosCliException.MifosCliErrorCode.MIFOS_COMMONS_ERROR_CLI_GENERIC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mifos.commons.boot.core.exception.MifosCliException;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.service.MfgHistoryService;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.dataformat.yaml.YAMLMapper;

@Slf4j
@RequiredArgsConstructor
@Service
final class StandardHistoryService implements MfgHistoryService {
    private final YAMLMapper mapper;

    private final Path historyPath = Path.of(".mifos/mfg/history.yml");

    @Override
    public Map<String, Object> load() {
        if (!Files.exists(historyPath)) {
            return Map.of();
        }

        return mapper.readValue(historyPath.toFile(), new TypeReference<>() {});
    }

    @Override
    public void store(Map<String, Object> data) {
        try {
            if (!Files.exists(historyPath.getParent())) {
                Files.createDirectories(historyPath.getParent());
            }

            var history = new HashMap<>(load());
            history.putAll(data);

            mapper.writeValue(historyPath, history);
        } catch (IOException e) {
            throw new MifosCliException(MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, e));
        }
    }
}
