///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.git.core;

import static org.mifos.tools.mfg.core.MfgConstants.MIFOS_TOOLS_MFG_SERVICE_PACKAGE_BASE;

import lombok.experimental.UtilityClass;

@UtilityClass
public class VcsServiceConstants {
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_PACKAGE_BASE = MIFOS_TOOLS_MFG_SERVICE_PACKAGE_BASE + ".git";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_CORE_PACKAGE =
            MIFOS_TOOLS_MFG_SERVICE_VCS_PACKAGE_BASE + ".core";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_IMPLEMENTATION_PACKAGE =
            MIFOS_TOOLS_MFG_SERVICE_VCS_PACKAGE_BASE + ".implementation";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_STARTER_PACKAGE =
            MIFOS_TOOLS_MFG_SERVICE_VCS_PACKAGE_BASE + ".starter";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_BRANCH_MAIN = "main";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_SECTION_USER = "user";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_SECTION_COMMIT = "commit";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_NAME_USER_NAME = "name";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_NAME_USER_EMAIL = "email";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_NAME_USER_SIGNKEY = "signingkey";
    public static final String MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_NAME_COMMIT_GPGSIGN = "gpgsign";
}
