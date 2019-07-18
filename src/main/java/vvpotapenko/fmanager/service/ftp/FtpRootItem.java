package vvpotapenko.fmanager.service.ftp;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;

import javax.swing.*;

public class FtpRootItem extends BaseFileItem {

    public FtpRootItem(IFileItem parent) {
        super(parent);
    }

    @Override
    public String getName() {
        return Resources.getString("ftp.hosts");
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
