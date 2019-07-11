package vvpotapenko.fmanager.model;

import vvpotapenko.fmanager.providers.IFileSource;

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

    public IFileSource getFileSource() {
        return fileSource;
    }

    public boolean isDirectory() {
        return false;
    }
}
