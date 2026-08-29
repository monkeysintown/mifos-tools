///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.pebble.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Paths;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest(classes = {TestConfiguration.class})
final class PebbleTemplateServiceTest {
    private final PebbleTemplateService templateService;

    @Test
    void evalZip() {
        log.error(
                "PATH: {}",
                Paths.get("").resolve("src/test/resources/templates.zip").toAbsolutePath());
        var result = templateService.eval(
                "zip://"
                        + Paths.get("")
                                .resolve("src/test/resources/templates.zip")
                                .toAbsolutePath() + "!/content/Foo.java.peb",
                Map.of("className", "Baz"));

        assertNotNull(result);

        log.error("Result: {}", result);
    }

    @Test
    void evalTgz() {
        var result = templateService.eval(
                "tgz://"
                        + Paths.get("")
                                .resolve("src/test/resources/templates.tgz")
                                .toAbsolutePath() + "!/content/Bar.java.peb",
                Map.of("className", "Baz"));

        assertNotNull(result);

        log.error("Result: {}", result);
    }
}
