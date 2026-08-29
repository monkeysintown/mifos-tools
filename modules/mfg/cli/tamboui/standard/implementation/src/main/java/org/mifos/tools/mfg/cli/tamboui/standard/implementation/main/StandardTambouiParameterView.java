///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import static dev.tamboui.layout.Padding.horizontal;
import static dev.tamboui.style.Color.CYAN;
import static dev.tamboui.style.Color.DARK_GRAY;
import static dev.tamboui.toolkit.Toolkit.form;
import static dev.tamboui.toolkit.Toolkit.panel;
import static dev.tamboui.toolkit.Toolkit.scrollable;
import static dev.tamboui.toolkit.event.EventResult.HANDLED;
import static dev.tamboui.toolkit.event.EventResult.UNHANDLED;
import static java.util.Objects.nonNull;
import static org.mifos.tools.mfg.cli.tamboui.standard.core.StandardTambouiConstants.MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_PARAMETER;

import dev.tamboui.toolkit.elements.FormElement;
import dev.tamboui.toolkit.elements.Panel;
import dev.tamboui.widgets.form.FieldType;
import dev.tamboui.widgets.form.FormState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mifos.tools.mfg.core.model.MfgTemplateIndexData;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
final class StandardTambouiParameterView {
    private final StandardTambouiParameterController controller;
    private FormElement formElement;

    Panel render() {
        if (formElement == null) {
            buildForm();
        }

        return panel(
                        " Parameters: %s ".formatted(controller.size()),
                        scrollable(formElement).fill())
                .vertical()
                .padding(horizontal(1))
                .rounded()
                .borderColor(DARK_GRAY)
                .focusedBorderColor(CYAN)
                .focusable()
                .id(MIFOS_TOOLS_MFG_CLI_TAMBOUI_VIEW_PARAMETER)
                .onKeyEvent(event -> {
                    switch (event.code()) {
                        case F5 -> {
                            controller.save(formElement.formState());
                            return HANDLED;
                        }
                        case F6 -> {
                            controller.vcs();
                            return HANDLED;
                        }
                        default -> {
                            return UNHANDLED;
                        }
                    }
                });
    }

    @SuppressWarnings("unchecked")
    private void buildForm() {
        var formStateBuilder = FormState.builder();

        for (var item : controller.items()) {
            switch (item.getType()) {
                case SELECT_SINGLE ->
                    formStateBuilder.selectField(
                            item.getName(),
                            item.getOptions(),
                            nonNull(item.getDefaultValueAsString())
                                    ? item.getOptions().indexOf(item.getDefaultValueAsString())
                                    : 0);
                case SELECT_MULTI ->
                    item.getOptions()
                            .forEach(option -> formStateBuilder.booleanField(
                                    item.getName() + ":" + option,
                                    List.class
                                            .cast(controller.history().getOrDefault(item.getName(), List.of()))
                                            .stream()
                                            .anyMatch(o -> o.equals(option))));
                default -> formStateBuilder.textField(item.getName(), item.getDefaultValueAsString());
            }
        }

        formElement = form(formStateBuilder.build());

        for (var item : controller.items()) {
            buildField(item);
        }

        formElement
                .labelWidth(25)
                .rounded()
                .fieldSpacing(1)
                .borderColor(DARK_GRAY)
                .focusedBorderColor(CYAN)
                .fill();
    }

    private void buildField(MfgTemplateIndexData.TemplateParameter item) {
        switch (item.getType()) {
            case SELECT_SINGLE -> formElement.field(item.getName(), item.getMessage(), FieldType.SELECT);
            case SELECT_MULTI -> {
                formElement.group(item.getMessage());

                item.getOptions()
                        .forEach(
                                option -> formElement.field(item.getName() + ":" + option, option, FieldType.CHECKBOX));

                formElement.endGroup();
            }
            default -> formElement.field(item.getName(), item.getMessage(), item.getDescription());
        }
    }
}
