///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
// @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MfgVcsGpgKeyData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    ///
    /// null on primary keys, set on subkeys
    ///
    private Long primaryKeyId;
    ///
    /// 16-hex-char long key id, e.g. "123B56C786CEDFA97"
    ///
    private String keyId;
    ///
    /// armored block; see note below
    ///
    private String rawKey;
    ///
    /// base64 of the *binary* key packets (no armor)
    ///
    private String publicKey;
    private List<MfgVcsGpgEmail> emails = new ArrayList<>();
    ///
    /// same schema -> recursive type
    ///
    private List<MfgVcsGpgKeyData> subkeys = new ArrayList<>();
    private boolean canSign;
    private boolean canEncryptComms;
    private boolean canEncryptStorage;
    private boolean canCertify;
    private Instant createdAt;
    ///
    /// null = never expires
    ///
    private Instant expiresAt;
    private boolean revoked;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MfgVcsGpgEmail implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String email;
        private boolean verified;
    }
}
