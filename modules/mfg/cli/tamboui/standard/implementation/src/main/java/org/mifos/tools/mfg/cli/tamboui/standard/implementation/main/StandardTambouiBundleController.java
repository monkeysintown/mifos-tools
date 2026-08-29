///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_NOT_FOUND;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;
import org.mifos.tools.mfg.core.service.MfgFileService;
import org.mifos.tools.mfg.core.service.MfgTemplateDependencyService;
import org.mifos.tools.mfg.core.service.MfgTemplateIndexService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
final class StandardTambouiBundleController {
    private final MfgTemplateDependencyService dependencyService;
    private final MfgFileService fileService;
    private final MfgTemplateIndexService indexService;
    private final ApplicationEventPublisher publisher;
    private final List<String> items = new ArrayList<>(
            List.of("org.mifos.conventions.templates:mifos-conventions-templates-project:tgz:tpl:0.1.0-SNAPSHOT"));
    private int selected;

    void previous() {
        selected = Math.max(0, selected - 1);
        refresh();
    }

    void next() {
        selected = Math.min(items.size() - 1, selected + 1);
        refresh();
    }

    int selected() {
        return selected;
    }

    List<String> items() {
        return items;
    }

    int size() {
        return items.size();
    }

    void refresh() {
        var resolvedDependency = dependencyService.resolve(items.get(selected));

        var dependencyUri = "tgz:file://" + resolvedDependency;

        try (var is = fileService.open(dependencyUri + "!/.idx.yml")) {
            var idx = indexService.parse(is);

            publisher.publishEvent(idx);
        } catch (IOException ioe) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_NOT_FOUND, ioe));
        }
    }
}
