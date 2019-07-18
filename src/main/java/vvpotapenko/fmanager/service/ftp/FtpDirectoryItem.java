package vvpotapenko.fmanager.service.ftp;

import org.apache.commons.net.ftp.FTPFile;
import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;

import javax.swing.*;

public class FtpDirectoryItem extends BaseFileItem {

    private final String fileName;
    private final FtpHost host;
    private final String parentPath;

    public FtpDirectoryItem(FTPFile ftpFile, FtpHost host, IFileItem parent, String parentPath) {
        super(parent);
        this.host = host;
        this.parentPath = parentPath;

        this.fileName = ftpFile.getName();
    }

    public FtpHost getHost() {
        return host;
    }

    public String getParentPath() {
        return parentPath;
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getDisplaySize() {
        return null;
    }

    @Override
    public FileItemType getFileType() {
        return null;
    }

    @Override
    public Icon getSystemIcon() {
        return null;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}
