///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import static dev.tamboui.style.Color.DARK_GRAY;
import static dev.tamboui.style.Color.GREEN;
import static dev.tamboui.toolkit.Toolkit.panel;
import static dev.tamboui.toolkit.Toolkit.row;
import static dev.tamboui.toolkit.Toolkit.spacer;
import static dev.tamboui.toolkit.Toolkit.text;
import static dev.tamboui.toolkit.Toolkit.waveText;
import static dev.tamboui.widgets.wavetext.WaveText.Mode.LOOP;
import static org.mifos.commons.boot.core.MifosConstants.MIFOS_SYMBOL_GLOBE;

import dev.tamboui.toolkit.element.Element;
import dev.tamboui.toolkit.elements.WaveTextElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
final class StandardTambouiBreadcrumbView {
    private final StandardTambouiBreadcrumbController controller;
    private final WaveTextElement titleElement =
            waveText("Mifos Fast Generator ").bold().color(GREEN).mode(LOOP);

    Element render() {
        return panel(row(
                                spacer(),
                                titleElement,
                                spacer(),
                                text(MIFOS_SYMBOL_GLOBE).blue(),
                                spacer(),
                                text(" | ").white().dim(),
                                spacer(),
                                text(controller.bundle().orElse("---")).cyan(),
                                spacer(),
                                text(" ▶ ").cyan(),
                                spacer(),
                                text(controller.group().orElse("---")).cyan())
                        .spacing(1))
                .rounded()
                .borderColor(DARK_GRAY)
                .horizontal()
                .fill();
    }

    @Scheduled(fixedDelay = 20_000)
    void oscillate() {
        titleElement.oscillate();
    }
}
