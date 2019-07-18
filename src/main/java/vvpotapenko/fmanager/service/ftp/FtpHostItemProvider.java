package vvpotapenko.fmanager.service.ftp;

import vvpotapenko.fmanager.model.IFileItem;

import java.util.List;

public class FtpHostItemProvider extends BaseFtpItemProvider {

    @Override
    public boolean canHandle(IFileItem fileItem) {
        return fileItem instanceof FtpHostItem;
    }

    @Override
    public List<IFileItem> listFiles(IFileItem directory) throws Exception {
        FtpHostItem hostItem = (FtpHostItem) directory;
        return listFtpFiles(directory, hostItem.getHost(), "/");
    }
}
