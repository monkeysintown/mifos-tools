///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.pico.standard.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mifos.commons.boot.cli.pico.command.MifosCliPicoCommand;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.shell.jline3.PicocliCommands;

@Slf4j
@RequiredArgsConstructor
@Component
@Command(
        name = "",
        mixinStandardHelpOptions = true,
        description = "Mifos Fast Generator",
        footer = """

@|bold,yellow,faint ------------------------------- |@
@|bold,yellow ° Mifos Initiative - (c) 2026 ° |@""",
        subcommands = {
            GenCommand.class,
            ListCommand.class,
            PrintCommand.class,
            HistoryCommand.class,
            HelloCommand.class,
            CommandLine.HelpCommand.class,
            PicocliCommands.ClearScreen.class
        })
final class StandardPicoCommands implements MifosCliPicoCommand {
    @Override
    public void run() {
        System.out.println("...");
    }
}
