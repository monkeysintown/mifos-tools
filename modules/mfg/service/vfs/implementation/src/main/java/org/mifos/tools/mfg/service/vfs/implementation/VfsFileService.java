///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.vfs.implementation;

import static org.apache.commons.vfs2.FileType.FILE;
import static org.apache.commons.vfs2.FileType.FOLDER;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_FILE_ERROR_NOT_FOUND;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_FILE_ERROR_UNKNOWN;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.vfs2.FileTypeSelector;
import org.apache.commons.vfs2.VFS;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;
import org.mifos.tools.mfg.core.service.MfgFileService;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class VfsFileService implements MfgFileService {
    @Override
    public List<String> list(String path) {
        try (var file = VFS.getManager().resolveFile(path)) {
            if (file.getType() == FOLDER) {
                return Arrays.stream(file.findFiles(new FileTypeSelector(FILE)))
                        .map(fileObject -> fileObject.getURI().toString())
                        .toList();
            }
        } catch (IOException ioe) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_FILE_ERROR_UNKNOWN, ioe));
        }

        return List.of(path);
    }

    @Override
    public InputStream open(String path) throws IOException {
        var file = VFS.getManager().resolveFile(path);

        if (file.getType() == FILE) {
            return file.getContent().getInputStream(1024);
        }

        throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_FILE_ERROR_NOT_FOUND));
    }
}
