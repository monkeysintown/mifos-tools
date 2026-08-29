///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.core.exception;

import static org.mifos.tools.mfg.core.MfgConstants.MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT;
import static org.mifos.tools.mfg.core.MfgConstants.MIFOS_TOOLS_MFG_ERROR_CODE_START;
import static org.mifos.tools.mfg.core.MfgConstants.MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX;

import java.io.Serial;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mifos.commons.boot.core.exception.MifosBaseException;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.commons.boot.core.model.MifosErrorCode;

public class MfgException extends MifosBaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public MfgException(MifosError error) {
        super(error);
    }

    @Getter
    @RequiredArgsConstructor
    public enum MifosGeneratorErrorCode implements MifosErrorCode {
        MIFOS_TOOLS_MFG_ERROR_UNKNOWN(
                MIFOS_TOOLS_MFG_ERROR_CODE_START, MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".unknown"),
        MIFOS_TOOLS_MFG_TEMPLATE_ERROR_UNKNOWN(
                MIFOS_TOOLS_MFG_ERROR_CODE_START + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".template.unknown"),
        MIFOS_TOOLS_MFG_TEMPLATE_ERROR_NOT_FOUND(
                MIFOS_TOOLS_MFG_TEMPLATE_ERROR_UNKNOWN.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".template.not-found"),
        MIFOS_TOOLS_MFG_TEMPLATE_MISSING(
                MIFOS_TOOLS_MFG_TEMPLATE_ERROR_NOT_FOUND.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".template.missing"),
        MIFOS_TOOLS_MFG_TEMPLATE_ERROR_LOAD(
                MIFOS_TOOLS_MFG_TEMPLATE_MISSING.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".template.load"),
        MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL(
                MIFOS_TOOLS_MFG_TEMPLATE_ERROR_LOAD.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".template.eval"),
        MIFOS_TOOLS_MFG_TEMPLATE_ERROR_INDEX(
                MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".template.index"),
        MIFOS_TOOLS_MFG_FILE_ERROR_UNKNOWN(
                MIFOS_TOOLS_MFG_TEMPLATE_ERROR_INDEX.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".file.unknown"),
        MIFOS_TOOLS_MFG_FILE_ERROR_NOT_FOUND(
                MIFOS_TOOLS_MFG_FILE_ERROR_UNKNOWN.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".file.not-found"),
        MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_UNKNOWN(
                MIFOS_TOOLS_MFG_FILE_ERROR_NOT_FOUND.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".dependency.unknown"),
        MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_NOT_FOUND(
                MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_UNKNOWN.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".dependency.not-found"),
        MIFOS_TOOLS_MFG_VCS_ERROR_UNKNOWN(
                MIFOS_TOOLS_MFG_DEPENDENCY_ERROR_NOT_FOUND.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".vcs.unknown"),
        MIFOS_TOOLS_MFG_VCS_ERROR_INVALID_REF_NAME(
                MIFOS_TOOLS_MFG_VCS_ERROR_UNKNOWN.getValue() + MIFOS_TOOLS_MFG_ERROR_CODE_INCREMENT,
                MIFOS_TOOLS_MFG_MESSAGE_ERROR_PREFIX + ".vcs.invalid-ref-name"),
        ;

        private final int value;
        private final String key;

        @Override
        public String getName() {
            return name();
        }
    }
}
