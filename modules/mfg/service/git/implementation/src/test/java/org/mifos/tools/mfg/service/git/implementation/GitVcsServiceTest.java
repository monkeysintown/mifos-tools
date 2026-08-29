///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.git.implementation;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mifos.tools.mfg.core.model.MfgVcsCommitData;
import org.mifos.tools.mfg.core.model.MfgVcsInitData;
import org.mifos.tools.mfg.core.model.MfgVcsLogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest(classes = {TestConfiguration.class})
final class GitVcsServiceTest {
    private final GitVcsService gitVcsService;

    private MfgVcsInitData initData;
    private MfgVcsCommitData commitData;

    @BeforeEach
    void setUp() {
        initData = MfgVcsInitData.builder()
                .path("/tmp/" + LocalDateTime.now().getNano())
                .author("Aleksandar Vidakovic")
                .email("aleks@mifos.org")
                .build();

        commitData = MfgVcsCommitData.builder()
                .path(initData.getPath())
                .message("chore: initial commit")
                .build();

        try {
            Files.createDirectory(Path.of(initData.getPath()));
        } catch (IOException _) {
            // ignore
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(Path.of(initData.getPath()));
        } catch (IOException _) {
            // ignore
        }
    }

    @Test
    void init() throws Exception {
        gitVcsService.init(initData);

        var configPath = Path.of(initData.getPath()).resolve(".git/config");

        var exists = Files.exists(configPath);

        assertTrue(exists);

        var content = FileUtils.readFileToString(configPath.toFile(), UTF_8);

        assertNotNull(content);

        log.error("Git configuration:\n{}", content);
    }

    @Test
    @SuppressWarnings("java:S2699")
    void commit() {
        gitVcsService.commit(commitData);
    }

    @Test
    @Disabled("Figure out why this doesn't work")
    void log() {
        var logs = gitVcsService.log(
                MfgVcsLogData.builder().path(initData.getPath()).build());

        assertNotNull(logs);

        assertEquals(1, logs.size());

        for (var l : logs) {
            log.error("Log: {}", l);
        }
    }
}
