///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.pico.standard.implementation;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jline.reader.LineReader;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@RequiredArgsConstructor
@Command(name = "print", description = "Generate code")
final class PrintCommand implements Runnable {
    private final LineReader lineReader;

    @Option(
            names = {"-d", "--dependency"},
            description = "Maven 'g:a:v' template bundle dependency")
    private String dependency;

    @Option(
            names = {"-f", "--file"},
            description = "Local template bundle file")
    private String file;

    @Option(
            names = {"-t", "--template"},
            description = "Template")
    private String template;

    @Option(
            names = {"-g", "--group"},
            description = "Template Group")
    private String group;

    @Option(
            names = {"-P", "--params"},
            mapFallbackValue = Option.NULL_VALUE)
    Map<String, Optional<String>> params;

    @Override
    @SuppressWarnings("java:S106")
    public void run() {
        System.out.println("Print template...");
    }
}
