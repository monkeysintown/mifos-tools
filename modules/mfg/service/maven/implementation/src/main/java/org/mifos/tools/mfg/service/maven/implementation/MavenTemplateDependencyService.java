///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.maven.implementation;

import static java.util.Objects.isNull;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_NOT_FOUND;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;
import org.mifos.tools.mfg.core.service.MfgTemplateDependencyService;
import org.mifos.tools.mfg.service.maven.core.MavenTemplateResolutionServiceProperties;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
final class MavenTemplateDependencyService implements MfgTemplateDependencyService {
    private final MavenTemplateResolutionServiceProperties properties;

    @Override
    public String resolve(String spec) {
        var file = Maven.configureResolver()
                .workOffline(properties.getOffline())
                .withClassPathResolution(properties.getWithClasspathResolution())
                .withRemoteRepo(
                        "mifosx-gradle-local", "https://mifos.jfrog.io/artifactory/mifosx-gradle-local", "default")
                .resolve(spec)
                .withoutTransitivity()
                .asSingleResolvedArtifact()
                .asFile();

        if (isNull(file)) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_NOT_FOUND, spec));
        }

        return file.getAbsolutePath();
    }
}
