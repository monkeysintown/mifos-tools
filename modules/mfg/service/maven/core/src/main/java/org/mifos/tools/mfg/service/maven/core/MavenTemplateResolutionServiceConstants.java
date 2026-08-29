///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.maven.core;

import static org.mifos.tools.mfg.core.MfgConstants.MIFOS_TOOLS_MFG_PROPERTIES_PREFIX;
import static org.mifos.tools.mfg.core.MfgConstants.MIFOS_TOOLS_MFG_SERVICE_PACKAGE_BASE;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MavenTemplateResolutionServiceConstants {
    public static final String MIFOS_TOOLS_MFG_SERVICE_MAVEN_PACKAGE_BASE =
            MIFOS_TOOLS_MFG_SERVICE_PACKAGE_BASE + ".maven";
    public static final String MIFOS_TOOLS_MFG_SERVICE_MAVEN_CORE_PACKAGE =
            MIFOS_TOOLS_MFG_SERVICE_MAVEN_PACKAGE_BASE + ".core";
    public static final String MIFOS_TOOLS_MFG_SERVICE_MAVEN_IMPLEMENTATION_PACKAGE =
            MIFOS_TOOLS_MFG_SERVICE_MAVEN_PACKAGE_BASE + ".implementation";
    public static final String MIFOS_TOOLS_MFG_SERVICE_MAVEN_STARTER_PACKAGE =
            MIFOS_TOOLS_MFG_SERVICE_MAVEN_PACKAGE_BASE + ".starter";
    public static final String MIFOS_TOOLS_MFG_SERVICE_MAVEN_PROPERTIES_PREFIX =
            MIFOS_TOOLS_MFG_PROPERTIES_PREFIX + ".maven";
    public static final String MIFOS_TOOLS_MFG_PROPERTIES_ENABLED =
            MIFOS_TOOLS_MFG_SERVICE_MAVEN_PROPERTIES_PREFIX + ".enabled";
    public static final String MIFOS_TOOLS_MFG_PROPERTIES_OFFLINE =
            MIFOS_TOOLS_MFG_SERVICE_MAVEN_PROPERTIES_PREFIX + ".offline";
    public static final String MIFOS_TOOLS_MFG_PROPERTIES_SETTINGS_LOCATION =
            MIFOS_TOOLS_MFG_SERVICE_MAVEN_PROPERTIES_PREFIX + ".settings-location";
}
