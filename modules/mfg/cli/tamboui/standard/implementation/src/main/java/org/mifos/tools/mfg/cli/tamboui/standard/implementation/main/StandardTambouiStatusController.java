///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.tamboui.standard.implementation.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mifos.commons.boot.core.model.MifosError;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
final class StandardTambouiStatusController {
    private MifosError error;

    Optional<String> error() {
        return Optional.ofNullable(error).map(MifosError::getMessage);
    }

    @EventListener
    void onError(MifosError error) {
        this.error = error;
    }
}
