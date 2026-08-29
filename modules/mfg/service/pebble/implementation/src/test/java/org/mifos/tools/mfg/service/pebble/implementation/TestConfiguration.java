///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.pebble.implementation;

import static org.mifos.tools.mfg.service.pebble.core.PebbleServiceConstants.MIFOS_TOOLS_MFG_SERVICE_PEBBLE_CORE_PACKAGE;
import static org.mifos.tools.mfg.service.pebble.core.PebbleServiceConstants.MIFOS_TOOLS_MFG_SERVICE_PEBBLE_IMPLEMENTATION_PACKAGE;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_PEBBLE_CORE_PACKAGE)
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_PEBBLE_IMPLEMENTATION_PACKAGE)
final class TestConfiguration {}
