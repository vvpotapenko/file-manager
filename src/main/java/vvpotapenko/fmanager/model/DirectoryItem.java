package vvpotapenko.fmanager.model;

import vvpotapenko.fmanager.providers.IDirectorySource;
import vvpotapenko.fmanager.providers.IFileSource;

import java.util.ArrayList;
import java.util.List;

public class DirectoryItem extends FileItem {

    private final List<FileItem> children = new ArrayList<>();

    public DirectoryItem(String name, IFileSource fileSource) {
        super(name, fileSource);
    }

    public List<FileItem> getChildren() {
        return children;
    }

    public void setChildren(List<FileItem> newChildren) {
        children.clear();
        children.addAll(newChildren);
    }

    public void clearChildren() {
        children.clear();
    }

    @Override
    public IDirectorySource getFileSource() {
        return (IDirectorySource) super.getFileSource();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

}
