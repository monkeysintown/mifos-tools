///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.yml.starter;

import static org.mifos.tools.mfg.service.yml.core.YmlTemplateIndexServiceConstants.MIFOS_TOOLS_MFG_SERVICE_YML_CORE_PACKAGE;
import static org.mifos.tools.mfg.service.yml.core.YmlTemplateIndexServiceConstants.MIFOS_TOOLS_MFG_SERVICE_YML_IMPLEMENTATION_PACKAGE;
import static tools.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static tools.jackson.dataformat.yaml.YAMLReadFeature.EMPTY_STRING_AS_NULL;
import static tools.jackson.dataformat.yaml.YAMLWriteFeature.LITERAL_BLOCK_STYLE;
import static tools.jackson.dataformat.yaml.YAMLWriteFeature.MINIMIZE_QUOTES;
import static tools.jackson.dataformat.yaml.YAMLWriteFeature.SPLIT_LINES;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tools.jackson.dataformat.yaml.YAMLMapper;

@Slf4j
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_YML_CORE_PACKAGE)
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_YML_IMPLEMENTATION_PACKAGE)
final class YmlTemplateIndexServiceAutoConfiguration {
    @Bean
    YAMLMapper yamlMapper() {
        return YAMLMapper.builder()
                .disable(EMPTY_STRING_AS_NULL)
                .disable(FAIL_ON_EMPTY_BEANS)
                .disable(SPLIT_LINES)
                .enable(MINIMIZE_QUOTES)
                .enable(LITERAL_BLOCK_STYLE)
                .build();
    }
}
