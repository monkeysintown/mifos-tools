///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.pebble.implementation.loader;

import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_TEMPLATE_ERROR_NOT_FOUND;

import io.pebbletemplates.pebble.loader.Loader;
import lombok.extern.slf4j.Slf4j;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;

@Slf4j
public final class PebbleLoaderFactory {
    private PebbleLoaderFactory() {}

    public static Loader<String> getLoader(String path) {
        if (path.contains(".tgz!/") || path.endsWith(".tgz")) {
            var loader = new PebbleTgzLoader();
            loader.setPrefix("");

            return loader;
        } else if (path.contains(".zip!/") || path.endsWith(".zip")) {
            var loader = new PebbleZipLoader();
            loader.setPrefix("");

            return loader;
        }

        throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_NOT_FOUND, path));
    }
}
