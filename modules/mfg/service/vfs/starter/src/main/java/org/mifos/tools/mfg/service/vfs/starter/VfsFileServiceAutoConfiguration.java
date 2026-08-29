///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.vfs.starter;

import static org.mifos.tools.mfg.service.vfs.core.VfsFileServiceConstants.MIFOS_TOOLS_MFG_SERVICE_VFS_CORE_PACKAGE;
import static org.mifos.tools.mfg.service.vfs.core.VfsFileServiceConstants.MIFOS_TOOLS_MFG_SERVICE_VFS_IMPLEMENTATION_PACKAGE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_VFS_CORE_PACKAGE)
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_VFS_IMPLEMENTATION_PACKAGE)
final class VfsFileServiceAutoConfiguration {}
