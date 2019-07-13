package vvpotapenko.fmanager.providers.local;

import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.providers.IDirectorySource;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyComputerSource extends BaseLocalFileSource implements IDirectorySource {

    private final boolean onlyDirectories;

    public MyComputerSource(boolean onlyDirectories) {
        this.onlyDirectories = onlyDirectories;
    }

    @Override
    public List<FileItem> loadChildren() {
        File[] files = File.listRoots();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        } else {
            List<FileItem> children = new ArrayList<>();
            for (File file : files) {
                children.add(createFileItem(file, this.onlyDirectories));
            }
            return children;
        }
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
