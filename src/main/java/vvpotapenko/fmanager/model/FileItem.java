package vvpotapenko.fmanager.model;

import vvpotapenko.fmanager.providers.IFileSource;

import javax.swing.*;

public class FileItem {

    private final String name;
    private final FileType fileType;
    private final IFileSource fileSource;

    public FileItem(String name, FileType fileType, IFileSource fileSource) {
        this.name = name;
        this.fileType = fileType;
        this.fileSource = fileSource;
    }

    public String getName() {
        return name;
    }

    public FileType getFileType() {
        return fileType;
    }

    public Icon getSystemIcon() {
        return fileSource.getSystemIcon();
    }

    public String getDisplaySize() {
        return fileSource.getDisplaySize();
    }

    public IFileSource getFileSource() {
        return fileSource;
    }

    public boolean isDirectory() {
        return false;
    }
}
