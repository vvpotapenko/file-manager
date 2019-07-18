package vvpotapenko.fmanager.service.ftp;

import vvpotapenko.fmanager.model.IFileItem;

import java.io.InputStream;
import java.util.List;

public class FtpFileItemProvider extends BaseFtpItemProvider {

    @Override
    public boolean canHandle(IFileItem fileItem) {
        return fileItem instanceof FtpFileItem || fileItem instanceof FtpDirectoryItem;
    }

    @Override
    public List<IFileItem> listFiles(IFileItem directory) throws Exception {
        FtpDirectoryItem ftpDirectoryItem = (FtpDirectoryItem) directory;

        String path = ftpDirectoryItem.getParentPath() + '/' + ftpDirectoryItem.getName() + '/';
        return listFtpFiles(directory, ftpDirectoryItem.getHost(), path);
    }

    @Override
    public InputStream createInputStream(IFileItem fileItem) throws Exception {
        FtpFileItem ftpFileItem = (FtpFileItem) fileItem;
        String filePath = ftpFileItem.getParentPath() + "/" + ftpFileItem.getName();
        return retrieveFtpFile(ftpFileItem.getHost(), filePath);
    }
}
