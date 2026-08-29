///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.core.service;

import java.util.List;
import org.mifos.tools.mfg.core.model.MfgVcsCommitData;
import org.mifos.tools.mfg.core.model.MfgVcsInitData;
import org.mifos.tools.mfg.core.model.MfgVcsLogData;

public interface MfgVcsService {
    void init(MfgVcsInitData data);

    void commit(MfgVcsCommitData data);

    List<String> log(MfgVcsLogData data);
}
