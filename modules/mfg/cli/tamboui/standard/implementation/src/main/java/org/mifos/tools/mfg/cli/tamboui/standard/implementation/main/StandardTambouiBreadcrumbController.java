///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mifos.tools.mfg.core.model.MfgTemplateIndexData;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
final class StandardTambouiBreadcrumbController {
    private String bundle;
    private String group;

    Optional<String> bundle() {
        return Optional.ofNullable(bundle);
    }

    Optional<String> group() {
        return Optional.ofNullable(group);
    }

    @EventListener
    void onIndex(MfgTemplateIndexData idx) {
        this.bundle = idx.getMetadata().getDescription();
    }

    @EventListener
    void onGroup(MfgTemplateIndexData.TemplateGroup group) {
        this.group = group.getDescription();
    }
}
