package vvpotapenko.fmanager.service.zip;

import org.apache.commons.io.FileUtils;
import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class ZipFileItem extends BaseFileItem {

    final File file;

    private final String name;
    private final Icon systemIcon;
    private final String displaySize;

    public ZipFileItem(File file, IFileItem parent) {
        super(parent);

        this.file = file;

        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        name = fileSystemView.getSystemDisplayName(file);
        systemIcon = fileSystemView.getSystemIcon(file);

        long length = file.length();
        displaySize = FileUtils.byteCountToDisplaySize(length);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDisplaySize() {
        return displaySize;
    }

    @Override
    public FileItemType getFileType() {
        return null;
    }

    @Override
    public Icon getSystemIcon() {
        return systemIcon;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}
