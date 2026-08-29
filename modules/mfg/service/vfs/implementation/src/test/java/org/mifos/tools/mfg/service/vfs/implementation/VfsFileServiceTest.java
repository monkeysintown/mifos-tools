///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.vfs.implementation;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest(classes = {TestConfiguration.class})
final class VfsFileServiceTest {
    private final VfsFileService fileService;

    @Test
    void list() {
        var files = fileService.list("tgz:file://"
                + Path.of("src/test/resources/mifos-conventions-templates-project-0.1.0-SNAPSHOT.tgz!/")
                        .toAbsolutePath());

        assertNotNull(files);
        assertFalse(files.isEmpty());
        assertEquals(21, files.size());

        log.error("Files: {}", files);
    }

    @Test
    void open() throws Exception {
        var is = fileService.open("tgz:file://"
                + Path.of("src/test/resources/mifos-conventions-templates-project-0.1.0-SNAPSHOT.tgz!/.idx.yml")
                        .toAbsolutePath());

        assertEquals(5094, is.available());

        var yml = IOUtils.toString(is, UTF_8);

        log.error("Test: {}", yml);
    }
}
