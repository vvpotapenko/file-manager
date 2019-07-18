package vvpotapenko.fmanager.service.ftp;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPFile;
import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;

import javax.swing.*;

public class FtpFileItem extends BaseFileItem {

    private final FtpHost host;
    private final FileItemType fileItemType;
    private final String parentPath;

    private final String fileName;
    private final String displaySize;


    FtpFileItem(FTPFile ftpFile, FileItemType fileItemType, FtpHost host, IFileItem parent, String parentPath) {
        super(parent);
        this.host = host;
        this.fileItemType = fileItemType;
        this.parentPath = parentPath;

        this.fileName = ftpFile.getName();
        long length = ftpFile.getSize();

        displaySize = FileUtils.byteCountToDisplaySize(length);
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
        return displaySize;
    }

    @Override
    public FileItemType getFileType() {
        return fileItemType;
    }

    @Override
    public Icon getSystemIcon() {
        return null;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}
