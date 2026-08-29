///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.git.starter;

import static org.mifos.tools.mfg.service.git.core.VcsServiceConstants.MIFOS_TOOLS_MFG_SERVICE_VCS_CORE_PACKAGE;
import static org.mifos.tools.mfg.service.git.core.VcsServiceConstants.MIFOS_TOOLS_MFG_SERVICE_VCS_IMPLEMENTATION_PACKAGE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_VCS_CORE_PACKAGE)
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_VCS_IMPLEMENTATION_PACKAGE)
final class VcsServiceAutoConfiguration {}
