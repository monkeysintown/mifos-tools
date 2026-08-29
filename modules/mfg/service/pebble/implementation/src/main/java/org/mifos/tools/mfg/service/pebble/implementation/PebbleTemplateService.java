///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.pebble.implementation;

import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL;

import io.pebbletemplates.pebble.PebbleEngine;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;
import org.mifos.tools.mfg.core.service.MfgTemplateService;
import org.mifos.tools.mfg.service.pebble.implementation.loader.PebbleLoaderFactory;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
final class PebbleTemplateService implements MfgTemplateService {
    @Override
    public String evalValue(String templateValue, Map<String, Object> context) {
        var engine = new PebbleEngine.Builder()
                .cacheActive(false)
                .newLineTrimming(false)
                .build();

        var template = engine.getLiteralTemplate(templateValue);

        try (var writer = new StringWriter()) {
            template.evaluate(writer, context);
            return writer.toString();
        } catch (IOException ioe) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL, ioe, List.of(templateValue)));
        }
    }

    @Override
    public String eval(String templateRef, Map<String, Object> context) {
        var engine = new PebbleEngine.Builder()
                .loader(PebbleLoaderFactory.getLoader(templateRef))
                .cacheActive(false)
                .newLineTrimming(false)
                .build();

        var template = engine.getTemplate(templateRef);

        try (var writer = new StringWriter()) {
            template.evaluate(writer, context);
            return writer.toString();
        } catch (IOException ioe) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL, ioe, List.of(templateRef)));
        }
    }
}
