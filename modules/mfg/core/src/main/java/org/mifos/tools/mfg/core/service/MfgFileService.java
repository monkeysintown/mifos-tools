///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.core.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface MfgFileService {
    List<String> list(String path);

    InputStream open(String path) throws IOException;
}
