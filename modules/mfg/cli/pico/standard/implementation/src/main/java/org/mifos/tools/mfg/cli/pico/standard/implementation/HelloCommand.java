///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.pico.standard.implementation;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jline.reader.LineReader;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@RequiredArgsConstructor
@Command(name = "hello", description = "Print a greeting message")
final class HelloCommand implements Runnable {
    private final LineReader lineReader;

    @Option(
            names = {"-m", "--message"},
            description = "Message",
            defaultValue = "World",
            completionCandidates = Messages.class)
    private String message;

    @Override
    @SuppressWarnings("java:S106")
    public void run() {
        var result = lineReader.readLine("hello: ");

        var str = CommandLine.Help.Ansi.AUTO.string("@|bold,green,underline Hello, %s!|@".formatted(message));
        System.out.println(str);
        System.out.println(result);
    }

    static class Messages extends ArrayList<String> {
        Messages() {
            super(List.of("Earth", "Mars", "Pluto"));
        }
    }
}
