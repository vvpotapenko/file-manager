package vvpotapenko.fmanager.model;

import vvpotapenko.fmanager.providers.IFileSource;

import javax.swing.*;

public class FileItem {

    private final String name;
    private final IFileSource fileSource;

    public FileItem(String name, IFileSource fileSource) {
        this.name = name;
        this.fileSource = fileSource;
    }

    public String getName() {
        return name;
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
