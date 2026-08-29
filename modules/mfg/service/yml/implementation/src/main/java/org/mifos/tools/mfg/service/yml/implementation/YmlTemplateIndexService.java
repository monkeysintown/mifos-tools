///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.yml.implementation;

import static java.util.Objects.requireNonNull;
import static org.mifos.commons.boot.core.exception.MifosCliException.MifosCliErrorCode.MIFOS_COMMONS_ERROR_CLI_GENERIC;
import static org.mifos.tools.mfg.core.MfgConstants.MIFOS_TOOLS_MFG_CACHE_TEMPLATE;
import static org.mifos.tools.mfg.core.model.MfgTemplateIndexData.TemplateParameterType.SELECT_MULTI;
import static org.mifos.tools.mfg.core.model.MfgTemplateIndexData.TemplateParameterType.SELECT_SINGLE;

import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mifos.commons.boot.core.exception.MifosCliException;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.model.MfgTemplateIndexData;
import org.mifos.tools.mfg.core.service.MfgTemplateIndexService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tools.jackson.dataformat.yaml.YAMLMapper;

@Slf4j
@RequiredArgsConstructor
@Cacheable(MIFOS_TOOLS_MFG_CACHE_TEMPLATE)
@Service
class YmlTemplateIndexService implements MfgTemplateIndexService {
    private final YAMLMapper mapper;

    @Override
    public MfgTemplateIndexData parse(InputStream data) {
        return mapper.readValue(data, MfgTemplateIndexData.class);
    }

    @Override
    public void validate(MfgTemplateIndexData index) {
        requireNonNull(index, "Template is required");
        requireNonNull(index.getMetadata(), "Template metadata is required");

        if (StringUtils.isBlank(index.getMetadata().getDescription())) {
            throw new MifosCliException(
                    MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, "name", "Template name is required"));
        }

        validateGroups(index.getGroups());
    }

    private void validateGroups(List<MfgTemplateIndexData.TemplateGroup> groups) {
        requireNonNull(groups, "Groups are required");

        for (var group : groups) {
            if (StringUtils.isBlank(group.getName())) {
                throw new MifosCliException(
                        MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, "name", "Group name is required"));
            }

            validateParameters(group.getParameters());
            validateFiles(group.getFiles());
        }
    }

    private void validateParameters(List<MfgTemplateIndexData.TemplateParameter> params) {
        if (params == null || params.isEmpty()) {
            throw new MifosCliException(
                    MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, "parameters", "At least one parameter is required"));
        }

        for (var param : params) {
            if (StringUtils.isBlank(param.getName())) {
                throw new MifosCliException(
                        MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, "name", "Parameter name is required"));
            }

            if (param.getType() == null) {
                throw new MifosCliException(
                        MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, param.getName(), "Parameter type is required"));
            }

            if ((SELECT_SINGLE.equals(param.getType()) || SELECT_MULTI.equals(param.getType()))
                    && (param.getOptions() == null || param.getOptions().isEmpty())) {
                throw new MifosCliException(MifosError.of(
                        MIFOS_COMMONS_ERROR_CLI_GENERIC, param.getName(), "Select parameter requires options"));
            }
        }
    }

    private void validateFiles(List<MfgTemplateIndexData.TemplateFileDefinition> files) {
        if (files == null || files.isEmpty()) {
            throw new MifosCliException(MifosError.of(
                    MIFOS_COMMONS_ERROR_CLI_GENERIC, "files", "At least one file definition is required"));
        }

        for (var file : files) {
            if (StringUtils.isBlank(file.getPath())) {
                throw new MifosCliException(
                        MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, "path", "File path is required"));
            }
            if (StringUtils.isBlank(file.getTemplate())) {
                throw new MifosCliException(MifosError.of(
                        MIFOS_COMMONS_ERROR_CLI_GENERIC, "template", "File template reference is required"));
            }
            if (file.getType() == null) {
                throw new MifosCliException(
                        MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, "type", "File type is required"));
            }
        }
    }
}
