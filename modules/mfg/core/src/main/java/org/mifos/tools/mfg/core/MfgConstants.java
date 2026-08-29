///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.core;

import static org.mifos.tools.common.core.MifosToolsConstants.MIFOS_TOOLS_ERROR_CODE_INCREMENT;
import static org.mifos.tools.common.core.MifosToolsConstants.MIFOS_TOOLS_ERROR_CODE_START;
import static org.mifos.tools.common.core.MifosToolsConstants.MIFOS_TOOLS_MESSAGE_BASE;
import static org.mifos.tools.common.core.MifosToolsConstants.MIFOS_TOOLS_MESSAGE_PREFIX;
import static org.mifos.tools.common.core.MifosToolsConstants.MIFOS_TOOLS_PACKAGE_BASE;
import static org.mifos.tools.common.core.MifosToolsConstants.MIFOS_TOOLS_PROPERTIES_PREFIX;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MfgConstants {
    public static final String MIFOS_TOOLS_MFG_MESSAGE_BASE = MIFOS_TOOLS_MESSAGE_BASE + "/mfg/messages";
    public static final String MIFOS_TOOLS_MFG_MESSAGE_PREFIX = MIFOS_TOOLS_MESSAGE_PREFIX + ".mfg";
    public static final String MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX = MIFOS_TOOLS_MFG_MESSAGE_PREFIX + ".error";
    public static final String MIFOS_TOOLS_MFG_PACKAGE_BASE = MIFOS_TOOLS_PACKAGE_BASE + ".mfg";
    public static final String MIFOS_TOOLS_MFG_CORE_PACKAGE = MIFOS_TOOLS_MFG_PACKAGE_BASE + ".core";
    public static final String MIFOS_TOOLS_MFG_MAPPING_PACKAGE = MIFOS_TOOLS_MFG_PACKAGE_BASE + ".mapping";
    public static final String MIFOS_TOOLS_MFG_STARTER_PACKAGE = MIFOS_TOOLS_MFG_PACKAGE_BASE + ".starter";
    public static final String MIFOS_TOOLS_MFG_SERVICE_PACKAGE_BASE = MIFOS_TOOLS_MFG_PACKAGE_BASE + ".service";
    public static final String MIFOS_TOOLS_MFG_USECASE_PACKAGE_BASE = MIFOS_TOOLS_MFG_PACKAGE_BASE + ".usecase";
    public static final String MIFOS_TOOLS_MFG_CLI_PACKAGE_BASE = MIFOS_TOOLS_MFG_PACKAGE_BASE + ".cli";
    public static final String MIFOS_TOOLS_MFG_CLI_SHELL_PACKAGE_BASE = MIFOS_TOOLS_MFG_CLI_PACKAGE_BASE + ".shell";
    public static final String MIFOS_TOOLS_MFG_CLI_PICO_PACKAGE_BASE = MIFOS_TOOLS_MFG_CLI_PACKAGE_BASE + ".pico";
    public static final String MIFOS_TOOLS_MFG_CLI_TAMBOUI_PACKAGE_BASE = MIFOS_TOOLS_MFG_CLI_PACKAGE_BASE + ".tamboui";
    public static final String MIFOS_TOOLS_MFG_PROPERTIES_PREFIX = MIFOS_TOOLS_PROPERTIES_PREFIX + ".generator";
    public static final String MIFOS_TOOLS_MFG_PROPERTIES_ENABLED = MIFOS_TOOLS_MFG_PROPERTIES_PREFIX + ".enabled";
    public static final int MIFOS_TOOLS_MFG_ERROR_CODE_START = MIFOS_TOOLS_ERROR_CODE_START + 100;
    public static final int MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT = MIFOS_TOOLS_ERROR_CODE_INCREMENT;
    public static final String MIFOS_TOOLS_MFG_CACHE = "mifos.tools.mfg.cache";
    public static final String MIFOS_TOOLS_MFG_CACHE_TEMPLATE = MIFOS_TOOLS_MFG_CACHE + ".template";
    public static final String MIFOS_TOOLS_MFG_CACHE_DEPENDENCY = MIFOS_TOOLS_MFG_CACHE + ".dependency";
}
