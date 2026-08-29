///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import static dev.tamboui.style.Color.CYAN;
import static dev.tamboui.style.Color.DARK_GRAY;
import static dev.tamboui.style.Color.YELLOW;
import static dev.tamboui.toolkit.Toolkit.list;
import static dev.tamboui.toolkit.Toolkit.panel;
import static dev.tamboui.toolkit.Toolkit.text;
import static dev.tamboui.toolkit.event.EventResult.HANDLED;
import static dev.tamboui.toolkit.event.EventResult.UNHANDLED;
import static org.mifos.tools.mfg.cli.tamboui.standard.core.StandardTambouiConstants.MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_BUNDLE;

import dev.tamboui.toolkit.elements.ListElement;
import dev.tamboui.toolkit.elements.Panel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
final class StandardTambouiBundleView {
    private final StandardTambouiBundleController controller;
    private ListElement<?> listElement;

    Panel render() {
        if (listElement == null) {
            buildList();
        }

        return panel(" Template Bundles: %s ".formatted(controller.size()), listElement)
                .vertical()
                .rounded()
                .borderColor(DARK_GRAY)
                .focusedBorderColor(CYAN)
                .focusable()
                .id(MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_BUNDLE);
    }

    private void buildList() {
        listElement = list().highlightColor(YELLOW)
                .highlightSymbol("▶ ")
                .autoScroll()
                .scrollbar()
                .scrollbarThumbColor(CYAN)
                .onKeyEvent(event -> {
                    switch (event.code()) {
                        case UP -> {
                            controller.previous();
                            listElement.selected(controller.selected());
                            return HANDLED;
                        }
                        case DOWN -> {
                            controller.next();
                            listElement.selected(controller.selected());
                            return HANDLED;
                        }
                        default -> {
                            return UNHANDLED;
                        }
                    }
                });

        for (var item : controller.items()) {
            var parts = item.split(":");
            listElement.add(
                    text("%s (%s - %s)".formatted(parts[1], parts[0], parts[4])).white());
        }

        listElement.preferredSize().heightOr(30);

        controller.refresh();
    }
}
