///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_FILE_ERROR_UNKNOWN;

import dev.tamboui.widgets.form.FormState;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;
import org.mifos.tools.mfg.core.model.MfgTemplateIndexData;
import org.mifos.tools.mfg.core.model.MfgVcsInitData;
import org.mifos.tools.mfg.core.service.MfgFileService;
import org.mifos.tools.mfg.core.service.MfgHistoryService;
import org.mifos.tools.mfg.core.service.MfgTemplateDependencyService;
import org.mifos.tools.mfg.core.service.MfgTemplateService;
import org.mifos.tools.mfg.core.service.MfgVcsService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
final class StandardTambouiParameterController {
    private final MfgFileService fileService;
    private final MfgHistoryService historyService;
    private final MfgTemplateService templateService;
    private final MfgTemplateDependencyService dependencyService;
    private final MfgVcsService vcsService;
    private MfgTemplateIndexData.TemplateMetadata metadata;
    private MfgTemplateIndexData.TemplateGroup group;
    private Map<String, Object> context = new HashMap<>();

    List<MfgTemplateIndexData.TemplateParameter> items() {
        return Optional.ofNullable(group)
                .map(MfgTemplateIndexData.TemplateGroup::getParameters)
                .map(parameters -> parameters.stream()
                        .filter(parameter -> !parameter.isIgnore())
                        .map(parameter -> {
                            parameter.setDefaultValue(
                                    history().getOrDefault(parameter.getName(), parameter.getDefaultValue()));
                            return parameter;
                        })
                        .toList())
                .orElse(List.of());
    }

    Map<String, Object> history() {
        return historyService.load();
    }

    int size() {
        return items().size();
    }

    @SuppressWarnings("java:S1135")
    void save(FormState formState) {
        context = new HashMap<>();

        for (var parameter : items()) {
            switch (parameter.getType()) {
                case SELECT_SINGLE -> context.put(parameter.getName(), formState.selectValue(parameter.getName()));
                case SELECT_MULTI -> {
                    var vals = formState.booleanValues().entrySet().stream()
                            .filter(entry -> entry.getKey().startsWith(parameter.getName()))
                            .filter(Map.Entry::getValue)
                            .map(entry -> entry.getKey().split(":")[1])
                            .toList();
                    context.put(parameter.getName(), vals);
                }
                default -> context.put(parameter.getName(), formState.textValue(parameter.getName()));
            }
        }

        historyService.store(context);

        generate();
    }

    void vcs() {
        vcsService.init(MfgVcsInitData.builder()
                .path(Objects.toString(context.get("projectName")))
                .author("%s %s"
                        .formatted(
                                context.getOrDefault("developerFirstname", "Unknown"),
                                context.getOrDefault("developerLastname", "Unknown")))
                .email(context.getOrDefault("developerEmail", "unknown@mifos.org")
                        .toString())
                .build());
    }

    void generate() {
        var files = Optional.ofNullable(group).map(MfgTemplateIndexData.TemplateGroup::getFiles);

        if (files.isPresent()) {
            for (var f : files.get()) {
                var resolvedDependencyPath = dependencyService.resolve(metadata.getDependency());
                var renderedPath = templateService.evalValue(f.getPath(), context);
                var templatePath =
                        "tgz:file://%s!/%s/%s".formatted(resolvedDependencyPath, group.getName(), f.getTemplate());
                var targetPath = Path.of(renderedPath);

                try {
                    if (!Files.exists(targetPath.getParent())) {
                        Files.createDirectories(targetPath.getParent());
                    }

                    if (MfgTemplateIndexData.TemplateFileType.PEBBLE.equals(f.getType())) {
                        var result = templateService.eval(templatePath, context);

                        FileUtils.writeStringToFile(targetPath.toFile(), result, UTF_8);
                    } else {
                        FileUtils.copyInputStreamToFile(fileService.open(templatePath), targetPath.toFile());
                    }
                } catch (IOException e) {
                    throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_FILE_ERROR_UNKNOWN, e));
                }
            }
        }
    }

    @EventListener
    void onIndex(MfgTemplateIndexData idx) {
        this.metadata = idx.getMetadata();
    }

    @EventListener
    void onGroup(MfgTemplateIndexData.TemplateGroup group) {
        this.group = group;
    }
}
