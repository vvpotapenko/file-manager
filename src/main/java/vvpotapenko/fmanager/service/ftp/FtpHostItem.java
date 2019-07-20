package vvpotapenko.fmanager.service.ftp;

import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.FileItemActionType;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;

public class FtpHostItem extends BaseFileItem {

    private static final Collection<FileItemActionType>
            availableActions = Collections.singleton(FileItemActionType.EDIT_FTP_HOST);

    private final FtpHost host;

    FtpHostItem(FtpHost host, IFileItem parent) {
        super(parent);
        this.host = host;
    }

    FtpHost getHost() {
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

    @Override
    public Collection<FileItemActionType> getAvailableActions() {
        return availableActions;
    }
}
