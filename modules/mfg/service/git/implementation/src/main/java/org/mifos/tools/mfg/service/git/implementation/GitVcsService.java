///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.git.implementation;

import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_FILE_ERROR_NOT_FOUND;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_VCS_ERROR_INVALID_REF_NAME;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_VCS_ERROR_UNKNOWN;
import static org.mifos.tools.mfg.service.git.core.VcsServiceConstants.MIFOS_TOOLS_MFG_SERVICE_VCS_BRANCH_MAIN;
import static org.mifos.tools.mfg.service.git.core.VcsServiceConstants.MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_NAME_USER_EMAIL;
import static org.mifos.tools.mfg.service.git.core.VcsServiceConstants.MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_NAME_USER_NAME;
import static org.mifos.tools.mfg.service.git.core.VcsServiceConstants.MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_SECTION_USER;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;
import org.mifos.tools.mfg.core.model.MfgVcsCommitData;
import org.mifos.tools.mfg.core.model.MfgVcsInitData;
import org.mifos.tools.mfg.core.model.MfgVcsLogData;
import org.mifos.tools.mfg.core.service.MfgVcsService;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
final class GitVcsService implements MfgVcsService {
    @Override
    public void init(MfgVcsInitData data) {
        try (var git = Git.init()
                .setDirectory(new File(data.getPath()))
                .setInitialBranch(MIFOS_TOOLS_MFG_SERVICE_VCS_BRANCH_MAIN)
                .call()) {

            var config = git.getRepository().getConfig();
            config.setString(
                    MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_SECTION_USER,
                    null,
                    MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_NAME_USER_NAME,
                    data.getAuthor());
            config.setString(
                    MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_SECTION_USER,
                    null,
                    MIFOS_TOOLS_MFG_SERVICE_VCS_CONFIG_NAME_USER_EMAIL,
                    data.getEmail());

            config.save();
        } catch (IOException ioe) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_FILE_ERROR_NOT_FOUND, ioe));
        } catch (InvalidRefNameException irne) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_VCS_ERROR_INVALID_REF_NAME, irne));
        } catch (GitAPIException gae) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_VCS_ERROR_UNKNOWN, gae));
        }
    }

    @Override
    public void commit(MfgVcsCommitData data) {
        try (var git = Git.init().setDirectory(new File(data.getPath())).call()) {

            var commit = git.commit()
                    .setMessage("chore: initial commit")
                    // .setCredentialsProvider(new UsernamePasswordCredentialsProvider("ignored", "passphrase"))
                    .call();

            log.error("Commit: {}", commit.getFullMessage());
        } catch (InvalidRefNameException irne) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_VCS_ERROR_INVALID_REF_NAME, irne));
        } catch (GitAPIException gae) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_VCS_ERROR_UNKNOWN, gae));
        }
    }

    @Override
    public List<String> log(MfgVcsLogData data) {
        try (var git = Git.init().setDirectory(new File(data.getPath())).call()) {
            var logs = new ArrayList<String>();

            for (var c : git.log().call()) {
                logs.add("%s  %s  %s <%s>  %s%n"
                        .formatted(
                                c.abbreviate(8).name(),
                                c.getAuthorIdent().getWhenAsInstant(),
                                c.getAuthorIdent().getName(),
                                c.getAuthorIdent().getEmailAddress(),
                                c.getShortMessage()));
            }

            return logs;
        } catch (InvalidRefNameException irne) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_VCS_ERROR_INVALID_REF_NAME, irne));
        } catch (GitAPIException gae) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_VCS_ERROR_UNKNOWN, gae));
        }
    }
}
