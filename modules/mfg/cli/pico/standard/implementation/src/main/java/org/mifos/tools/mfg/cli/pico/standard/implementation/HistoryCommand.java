///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.pico.standard.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jline.reader.LineReader;
import picocli.CommandLine.Command;

@Slf4j
@RequiredArgsConstructor
@Command(name = "history", description = "Show history")
final class HistoryCommand implements Runnable {
    private final LineReader lineReader;

    @Override
    public void run() {
        System.out.println("Show history...");
    }
}
