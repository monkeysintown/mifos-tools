///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.core.model;

import java.io.Serial;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.mifos.commons.boot.core.model.MifosRequest;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class MfgRunRequest implements MifosRequest {
    @Serial
    private static final long serialVersionUID = 1L;

    private String templateDependency;
    private String templateGroup;
    private String templateFile;
    private String templateRef;
    private String targetFolder;
    private transient Map<String, Object> context;
}
