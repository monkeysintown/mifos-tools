///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mifos.tools.mfg.core.model.MfgTemplateIndexData;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
final class StandardTambouiGroupController {
    private final ApplicationEventPublisher publisher;
    private final List<MfgTemplateIndexData.TemplateGroup> items = new ArrayList<>();
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

    List<MfgTemplateIndexData.TemplateGroup> items() {
        return items;
    }

    int size() {
        return items.size();
    }

    void refresh() {
        publisher.publishEvent(items.get(selected));
    }

    @EventListener
    void onIndex(MfgTemplateIndexData idx) {
        items.clear();
        items.addAll(idx.getGroups());
    }
}
