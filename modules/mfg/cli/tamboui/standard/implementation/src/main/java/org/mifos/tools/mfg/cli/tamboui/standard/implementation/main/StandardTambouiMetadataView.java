///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import static dev.tamboui.style.Color.DARK_GRAY;
import static dev.tamboui.style.Color.YELLOW;
import static dev.tamboui.toolkit.Toolkit.column;
import static dev.tamboui.toolkit.Toolkit.markupText;
import static dev.tamboui.toolkit.Toolkit.panel;
import static dev.tamboui.toolkit.Toolkit.text;
import static dev.tamboui.toolkit.Toolkit.waveText;

import dev.tamboui.toolkit.element.Element;
import dev.tamboui.toolkit.elements.Panel;
import dev.tamboui.toolkit.elements.WaveTextElement;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
final class StandardTambouiMetadataView {
    private final StandardTambouiMetadataController controller;
    private final WaveTextElement instructionsElement =
            waveText("!!! Instructions !!!").color(YELLOW).bold();

    Panel render() {
        return panel(
                        " %s "
                                .formatted(controller
                                        .metadata()
                                        .flatMap(m -> Optional.ofNullable(m.getDescription()))
                                        .orElse("---")),
                        metadata())
                .vertical()
                .rounded()
                .borderColor(DARK_GRAY)
                .padding(1);
    }

    private Element metadata() {
        return controller
                .metadata()
                .map(metadata -> column(
                                text(Optional.ofNullable(metadata.getVersion()).orElse("---")),
                                text(Optional.ofNullable(metadata.getAuthor()).orElse("---")),
                                instructionsElement,
                                markupText(Optional.ofNullable(metadata.getInstructions())
                                                .orElse("*No Instructions.*"))
                                        .yellow())
                        .fill())
                .orElse(column(text("No metadata available")).margin(2));
    }

    @Scheduled(fixedDelay = 20_000)
    void oscillate() {
        instructionsElement.oscillate();
    }
}
