package vvpotapenko.fmanager.service.ftp;

import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;

import javax.swing.*;

public class FtpHostItem extends BaseFileItem {

    private final FtpHost host;

    public FtpHostItem(FtpHost host, IFileItem parent) {
        super(parent);
        this.host = host;
    }

    public FtpHost getHost() {
        return host;
    }

    @Override
    public String getName() {
        return host.getHostname();
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
