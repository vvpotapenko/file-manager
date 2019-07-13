package vvpotapenko.fmanager.providers;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.providers.local.MyComputerSource;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RootSource implements IDirectorySource {

    private final boolean onlyDirectories;

    public RootSource(boolean onlyDirectories) {
        this.onlyDirectories = onlyDirectories;
    }

    @Override
    public List<FileItem> loadChildren() throws Exception {
        List<FileItem> items = new ArrayList<>();
        items.add(createMyComputerItem());
        // TODO ftps

        return items;
    }

    private FileItem createMyComputerItem() {
        String name = Resources.getString("my.computer.label");
        return new DirectoryItem(name, new MyComputerSource(this.onlyDirectories));
    }

    @Override
    public Icon getSystemIcon() {
        return null;
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
