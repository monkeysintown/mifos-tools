///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import static dev.tamboui.style.Color.DARK_GRAY;
import static dev.tamboui.toolkit.Toolkit.panel;
import static dev.tamboui.toolkit.Toolkit.row;
import static dev.tamboui.toolkit.Toolkit.spacer;
import static dev.tamboui.toolkit.Toolkit.text;

import dev.tamboui.toolkit.element.Element;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
final class StandardTambouiStatusView {
    private final StandardTambouiStatusController controller;

    Element render() {
        return panel(
                        row(text("[F1] Focus Bundles | [F2] Focus Groups | [F3] Focus Parameters | [F5] Generate | [F6] Init Git | [q] Quit")
                                        .dim()
                                        .cyan())
                                .fill(),
                        spacer(),
                        text(" Error: %s ".formatted(controller.error().orElse("---")))
                                .red())
                .rounded()
                .borderColor(DARK_GRAY)
                .horizontal()
                .fill();
    }
}
