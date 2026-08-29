///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.core.model;

import static java.util.Objects.isNull;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_TEMPLATE_ERROR_INDEX;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.FieldNameConstants;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class MfgTemplateIndexData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private TemplateMetadata metadata;

    @Singular
    private List<TemplateGroup> groups;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    public static class TemplateMetadata implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String version;
        private String description;
        private String author;
        private String dependency;
        private String instructions;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TemplateGroup implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String name;

        private String description;

        @Singular
        private List<TemplateParameterGroup> parameterGroups;

        @Singular
        private List<TemplateParameter> parameters;

        @Singular
        private List<TemplateFileDefinition> files;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TemplateParameterGroup implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String name;

        private String description;

        @Singular
        private List<String> parameterNames;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    public static class TemplateParameter implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String name;
        private TemplateParameterType type;
        private boolean required;
        private String description;

        @JsonProperty("default")
        private Object defaultValue;

        private List<String> options;

        private String pattern;
        private Integer min;
        private Integer max;
        private String message;
        private boolean ignore;

        @JsonIgnore
        public String getDefaultValueAsString() {
            if (isNull(defaultValue)) {
                return null;
            }

            return defaultValue.toString();
        }

        @JsonIgnore
        public Integer getDefaultValueAsInt() {
            if (isNull(defaultValue)) {
                return null;
            }

            if (defaultValue instanceof Integer intValue) {
                return intValue;
            }

            throw new MfgException(MifosError.of(
                    MIFOS_TOOLS_MFG_TEMPLATE_ERROR_INDEX,
                    "default",
                    "Default value is not of type integer: %s"
                            .formatted(defaultValue.getClass().getCanonicalName())));
        }

        @JsonIgnore
        public Double getDefaultValueAsDecimal() {
            if (isNull(defaultValue)) {
                return null;
            }

            if (defaultValue instanceof Double doubleValue) {
                return doubleValue;
            }

            throw new MfgException(MifosError.of(
                    MIFOS_TOOLS_MFG_TEMPLATE_ERROR_INDEX,
                    "default",
                    "Default value is not of type decimal: %s"
                            .formatted(defaultValue.getClass().getCanonicalName())));
        }

        @JsonIgnore
        public Boolean getDefaultValueAsBool() {
            if (isNull(defaultValue)) {
                return null;
            }

            if (defaultValue instanceof Boolean boolValue) {
                return boolValue;
            }

            throw new MfgException(MifosError.of(
                    MIFOS_TOOLS_MFG_TEMPLATE_ERROR_INDEX,
                    "default",
                    "Default value is not of type bool: %s"
                            .formatted(defaultValue.getClass().getCanonicalName())));
        }
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    public static class TemplateFileDefinition implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String path;
        private String template;
        private TemplateFileType type;
    }

    public enum TemplateParameterType {
        STRING,
        INT,
        DECIMAL,
        BOOL,
        SELECT_SINGLE,
        SELECT_MULTI,
        UNKNOWN
    }

    public enum TemplateFileType {
        PEBBLE,
        RAW
    }
}
