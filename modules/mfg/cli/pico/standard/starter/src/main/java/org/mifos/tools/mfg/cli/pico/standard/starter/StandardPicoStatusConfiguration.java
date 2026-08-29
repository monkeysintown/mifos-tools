///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.pico.standard.starter;

import static org.jline.utils.AttributedStyle.BLUE;
import static org.jline.utils.AttributedStyle.DEFAULT;
import static org.jline.utils.AttributedStyle.GREEN;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.Status;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
class StandardPicoStatusConfiguration {
    private final Terminal terminal;
    private final Status status;

    void init() {
        var asb = new AttributedStringBuilder();

        // left aligned segment
        asb.style(DEFAULT.foreground(BLUE)).append("Server: Connected");

        // center segment (with padding)
        int width = terminal.getColumns();
        int leftLen = "Server: Connected".length();
        int rightLen = "Users: 42".length();
        int padding = (width - leftLen - rightLen) / 2;
        for (int i = 0; i < padding; i++) {
            asb.append(" ");
        }

        // right-aligned segment
        asb.style(DEFAULT.foreground(GREEN)).append("Users: 42");

        status.update(List.of(asb.toAttributedString()));
    }
}
