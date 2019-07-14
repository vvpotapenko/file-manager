package vvpotapenko.fmanager.providers.local;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.providers.IDirectorySource;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyComputerSource extends LocalDirectorySource implements IDirectorySource {

    public MyComputerSource(IDirectorySource parent, boolean onlyDirectories) {
        super(null, parent, onlyDirectories);
    }

    @Override
    public List<FileItem> loadChildren() {
        File[] files = File.listRoots();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        } else {
            List<FileItem> children = new ArrayList<>();
            for (File file : files) {
                children.add(createFileItem(file, this, this.onlyDirectories));
            }
            return children;
        }
    }

    @Override
    public Icon getSystemIcon() {
        return null;
    }

    @Override
    public DirectoryItem createDirectoryItem() {
        String name = Resources.getString("my.computer.label");
        return new DirectoryItem(name, new MyComputerSource(this, onlyDirectories));
    }

    @Override
    public IDirectorySource clone(boolean onlyDirs) {
        IDirectorySource parent = getParent();
        IDirectorySource clonedParent = parent != null ? parent.clone(onlyDirs) : null;
        return new MyComputerSource(clonedParent, onlyDirs);
    }
}
