package vvpotapenko.fmanager.service.local;

import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.IFileItem;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

abstract class BaseLocalFileItem extends BaseFileItem {

    final File file;

    private final String name;
    private final Icon systemIcon;

    BaseLocalFileItem(File file, IFileItem parent) {
        super(parent);
        this.file = file;

        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        name = fileSystemView.getSystemDisplayName(file);
        systemIcon = fileSystemView.getSystemIcon(file);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Icon getSystemIcon() {
        return systemIcon;
    }
}
