///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.pico.standard.starter;

import static org.mifos.commons.boot.core.MifosCommonsBootConstants.MIFOS_COMMONS_BOOT_DEBUG_PROPERTY_TUI_TYPE;
import static org.mifos.tools.mfg.cli.pico.standard.core.StandardPicoConstants.MIFOS_TOOLS_MFG_CLI_PICO_STANDARD_CORE_PACKAGE;
import static org.mifos.tools.mfg.cli.pico.standard.core.StandardPicoConstants.MIFOS_TOOLS_MFG_CLI_PICO_STANDARD_IMPLEMENTATION_PACKAGE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Slf4j
@ComponentScan(MIFOS_TOOLS_MFG_CLI_PICO_STANDARD_CORE_PACKAGE)
@ComponentScan(MIFOS_TOOLS_MFG_CLI_PICO_STANDARD_IMPLEMENTATION_PACKAGE)
@Import({StandardPicoStatusConfiguration.class})
@ConditionalOnProperty(value = MIFOS_COMMONS_BOOT_DEBUG_PROPERTY_TUI_TYPE, havingValue = "PICO")
final class StandardPicoAutoConfiguration {}
