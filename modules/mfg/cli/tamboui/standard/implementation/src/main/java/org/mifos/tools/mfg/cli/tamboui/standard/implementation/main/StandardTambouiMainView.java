///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import static dev.tamboui.toolkit.Toolkit.column;
import static dev.tamboui.toolkit.Toolkit.dock;
import static dev.tamboui.toolkit.Toolkit.percent;

import dev.tamboui.toolkit.elements.DockElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public final class StandardTambouiMainView {
    private final StandardTambouiBundleView dependencyView;
    private final StandardTambouiGroupView groupView;
    private final StandardTambouiParameterView parameterView;
    private final StandardTambouiMetadataView metadataView;
    private final StandardTambouiStatusView statusView;
    private final StandardTambouiBreadcrumbView breadcrumbView;

    public DockElement render() {
        return dock().top(breadcrumbView.render())
                .left(column(
                        dependencyView.render().constraint(percent(35)),
                        groupView.render().constraint(percent(35)),
                        metadataView.render().constraint(percent(30))))
                .leftWidth(percent(40))
                .center(parameterView.render())
                .bottom(statusView.render());
    }
}
