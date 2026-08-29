///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.maven.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.extern.slf4j.Slf4j;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.jupiter.api.Test;

@Slf4j
final class MavenTemplateResolutionServiceTest {
    @Test
    void resolve() {
        var file = Maven.configureResolver()
                .workOffline(false)
                .withClassPathResolution(true)
                .withRemoteRepo(
                        "mifosx-gradle-local", "https://mifos.jfrog.io/artifactory/mifosx-gradle-local", "default")
                .resolve("org.mifos.conventions.templates:mifos-conventions-templates-project:tgz:tpl:0.1.0-SNAPSHOT")
                .withoutTransitivity()
                .asSingleResolvedArtifact()
                .asFile();

        assertNotNull(file, "No files found!");

        log.warn("Resolved: {}", file.getAbsolutePath());
    }
}
