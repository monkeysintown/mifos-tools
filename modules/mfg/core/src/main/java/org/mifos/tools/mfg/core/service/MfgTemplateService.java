///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.core.service;

import java.util.Map;

public interface MfgTemplateService {
    String evalValue(String templateValue, Map<String, Object> context);

    String eval(String templateRef, Map<String, Object> context);
}
