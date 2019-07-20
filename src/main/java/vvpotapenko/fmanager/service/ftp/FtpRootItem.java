package vvpotapenko.fmanager.service.ftp;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.FileItemActionType;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;

public class FtpRootItem extends BaseFileItem {

    private static final Collection<FileItemActionType>
            availableActions = Collections.singleton(FileItemActionType.NEW_FTP_HOST);

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

    @Override
    public Collection<FileItemActionType> getAvailableActions() {
        return availableActions;
    }
}
