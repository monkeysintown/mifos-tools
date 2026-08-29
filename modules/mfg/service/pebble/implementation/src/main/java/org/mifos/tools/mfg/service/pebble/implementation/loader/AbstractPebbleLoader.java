///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.pebble.implementation.loader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_TEMPLATE_ERROR_LOAD;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_TEMPLATE_ERROR_NOT_FOUND;

import io.pebbletemplates.pebble.loader.Loader;
import io.pebbletemplates.pebble.utils.PathUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.VFS;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;

@Slf4j
@Data
abstract class AbstractPebbleLoader implements Loader<String> {
    protected Charset charset = UTF_8;

    protected String prefix = "";

    protected String suffix = "";

    @Override
    public Reader getReader(String templateName) {
        try {
            return new InputStreamReader(getStream(templateName), charset);
        } catch (IOException ioe) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_LOAD, ioe, List.of(templateName)));
        }
    }

    @Override
    public void setCharset(String charset) {
        this.charset = Charset.forName(charset);
    }

    @Override
    public String resolveRelativePath(String relativePath, String anchorPath) {
        return PathUtils.resolveRelativePath(relativePath, anchorPath, File.separatorChar);
    }

    @Override
    public String createCacheKey(String templateName) {
        return templateName;
    }

    @Override
    public boolean resourceExists(String templateName) {
        try (var file = VFS.getManager().resolveFile(templateName)) {
            return file.exists() && file.getType() == FileType.FILE;
        } catch (IOException ioe) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_LOAD, ioe, List.of(templateName)));
        }
    }

    protected abstract String getVfsUri();

    @SuppressWarnings("java:S106")
    private static InputStream getStream(String templateName) throws IOException {
        var file = VFS.getManager().resolveFile(templateName);

        if (file.exists() && file.getType() == FileType.FILE) {
            return file.getContent().getInputStream();
        }

        throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_NOT_FOUND));
    }
}
