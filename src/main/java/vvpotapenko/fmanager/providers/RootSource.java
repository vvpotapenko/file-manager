package vvpotapenko.fmanager.providers;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.providers.local.MyComputerSource;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RootSource implements IDirectorySource {

    private final boolean onlyDirectories;

    public RootSource(boolean onlyDirectories) {
        this.onlyDirectories = onlyDirectories;
    }

    @Override
    public List<FileItem> loadChildren() {
        List<FileItem> items = new ArrayList<>();
        items.add(new MyComputerSource(this, onlyDirectories).createDirectoryItem());
        // TODO ftps

        return items;
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public IDirectorySource getParent() {
        return null;
    }

    @Override
    public DirectoryItem createDirectoryItem() {
        return new DirectoryItem("top", this);
    }

    @Override
    public IDirectorySource clone(boolean onlyDirs) {
        return new RootSource(onlyDirs);
    }

    @Override
    public Icon getSystemIcon() {
        return null;
    }

    @Override
    public void destroy() {
        // do nothing
    }

    @Override
    public String getDisplaySize() {
        return "";
    }

    @Override
    public InputStream createInputStream() throws Exception {
        throw new IOException("Root is cannot be read");
    }
}
