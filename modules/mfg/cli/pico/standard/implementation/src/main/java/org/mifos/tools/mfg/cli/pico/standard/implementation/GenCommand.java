///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.pico.standard.implementation;

import static java.util.Optional.ofNullable;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_NOT_FOUND;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jline.reader.LineReader;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;
import org.mifos.tools.mfg.core.model.MfgTemplateIndexData;
import org.mifos.tools.mfg.core.service.MfgFileService;
import org.mifos.tools.mfg.core.service.MfgTemplateDependencyService;
import org.mifos.tools.mfg.core.service.MfgTemplateIndexService;
import org.mifos.tools.mfg.core.service.MfgTemplateService;
import picocli.CommandLine;

@Slf4j
@RequiredArgsConstructor
@CommandLine.Command(name = "gen", description = "Generate code")
final class GenCommand implements Runnable {
    private final MfgTemplateDependencyService templateDependencyService;
    private final MfgFileService fileService;
    private final MfgTemplateIndexService templateIndexService;
    private final MfgTemplateService templateService;
    private final LineReader lineReader;

    @CommandLine.Option(
            names = {"-d", "--dependency"},
            description = "Maven 'g:a:v' template bundle dependency")
    private String dependency;

    @CommandLine.Option(
            names = {"-f", "--file"},
            description = "Local template bundle file")
    private String file;

    @CommandLine.Option(
            names = {"-g", "--group"},
            description = "Template Group")
    private String group;

    @CommandLine.Option(
            names = {"-P", "--params"},
            mapFallbackValue = CommandLine.Option.NULL_VALUE)
    Map<String, String> params;

    @Override
    @SuppressWarnings("java:S106")
    public void run() {
        var ctx = new HashMap<String, Object>(ofNullable(params).orElse(Map.of()));

        var resolvedDependency = ofNullable(dependency)
                .filter(StringUtils::isNotBlank)
                .map(templateDependencyService::resolve)
                .orElseGet(() -> this.file);

        System.out.println("File: " + resolvedDependency);

        var dependencyUri = "tgz:file://" + resolvedDependency;

        try (var is = fileService.open(dependencyUri + "!/.idx.yml")) {
            var idx = templateIndexService.parse(is);
            System.out.println("Index: author      - " + idx.getMetadata().getAuthor());
            System.out.println("Index: version     - " + idx.getMetadata().getVersion());
            System.out.println("Index: description - " + idx.getMetadata().getDescription());

            if (StringUtils.isEmpty(group)) {
                group = lineReader.readLine("Please specify a group: ");
            }

            var selectedGroup = idx.getGroups().stream()
                    .filter(templateGroup -> templateGroup.getName().equals(group))
                    .findFirst();

            if (selectedGroup.isPresent()) {
                var parameters = selectedGroup.map(MfgTemplateIndexData.TemplateGroup::getParameters);

                if (parameters.isPresent()) {
                    for (var parameter : parameters.get()) {
                        var value = lineReader.readLine(parameter.getMessage() + ":\n");
                        ctx.put(parameter.getName(), value);
                    }
                }

                var files = selectedGroup.map(MfgTemplateIndexData.TemplateGroup::getFiles);

                if (files.isPresent()) {
                    for (var f : files.get()) {
                        var renderedPath = templateService.evalValue(f.getPath(), ctx);

                        System.out.println("File: " + f.getTemplate() + " (" + f.getType() + ") => " + renderedPath);

                        var templatePath =
                                dependencyUri + "!/" + selectedGroup.get().getName() + "/" + f.getTemplate();

                        System.out.println("Template path: " + templatePath);

                        if (MfgTemplateIndexData.TemplateFileType.PEBBLE.equals(f.getType())) {
                            templateService.eval(templatePath, ctx);

                            // NOTE: save
                        } else {
                            // NOTE: just copy
                        }
                    }
                }
            }
        } catch (IOException ioe) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_NOT_FOUND, ioe));
        }
    }
}
