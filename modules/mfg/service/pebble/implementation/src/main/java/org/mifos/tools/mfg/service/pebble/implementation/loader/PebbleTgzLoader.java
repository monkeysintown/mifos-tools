///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.pebble.implementation.loader;

final class PebbleTgzLoader extends AbstractPebbleLoader {
    private static final String VFS_URI = "tgz://";

    @Override
    protected String getVfsUri() {
        return VFS_URI;
    }
}
