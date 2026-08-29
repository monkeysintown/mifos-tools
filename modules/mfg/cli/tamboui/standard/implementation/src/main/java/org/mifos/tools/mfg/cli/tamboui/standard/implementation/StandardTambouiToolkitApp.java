///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation;

import static dev.tamboui.toolkit.event.EventResult.HANDLED;
import static dev.tamboui.toolkit.event.EventResult.UNHANDLED;
import static org.mifos.tools.mfg.cli.tamboui.standard.core.StandardTambouiConstants.MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_BUNDLE;
import static org.mifos.tools.mfg.cli.tamboui.standard.core.StandardTambouiConstants.MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_GROUP;
import static org.mifos.tools.mfg.cli.tamboui.standard.core.StandardTambouiConstants.MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_PARAMETER;

import dev.tamboui.toolkit.app.ToolkitApp;
import dev.tamboui.toolkit.element.Element;
import dev.tamboui.tui.TuiConfig;
import lombok.RequiredArgsConstructor;
import org.mifos.tools.mfg.cli.tamboui.standard.implementation.main.StandardTambouiMainView;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
final class StandardTambouiToolkitApp extends ToolkitApp {
    private final StandardTambouiMainView mainView;
    private final TuiConfig tuiConfig;

    @Override
    protected TuiConfig configure() {
        return tuiConfig;
    }

    @Override
    protected Element render() {
        return mainView.render().onKeyEvent(event -> {
            switch (event.code()) {
                case F1 -> {
                    runner().focusManager().setFocus(MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_BUNDLE);
                    return HANDLED;
                }
                case F2 -> {
                    runner().focusManager().setFocus(MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_GROUP);
                    return HANDLED;
                }
                case F3 -> {
                    runner().focusManager().setFocus(MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_PARAMETER);
                    return HANDLED;
                }
                default -> {
                    return UNHANDLED;
                }
            }
        });
    }
}
