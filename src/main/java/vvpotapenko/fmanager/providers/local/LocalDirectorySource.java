package vvpotapenko.fmanager.providers.local;

import org.apache.commons.io.filefilter.FileFilterUtils;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.providers.IDirectorySource;

import javax.swing.*;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalDirectorySource extends BaseLocalFileSource implements IDirectorySource {

    final boolean onlyDirectories;

    private final File file;
    private final IDirectorySource parent;

    LocalDirectorySource(File file, IDirectorySource parent, boolean onlyDirectories) {
        this.file = file;
        this.parent = parent;
        this.onlyDirectories = onlyDirectories;
    }

    @Override
    public Icon getSystemIcon() {
        return getSystemIcon(file);
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
    public IDirectorySource getParent() {
        return parent;
    }

    @Override
    public List<FileItem> loadChildren() {
        FileFilter filter = onlyDirectories ? FileFilterUtils.directoryFileFilter() : FileFilterUtils.trueFileFilter();
        File[] files = file.listFiles(filter);
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        } else {
            List<FileItem> children = new ArrayList<>();
            for (File file : files) {
                children.add(createFileItem(file, this, onlyDirectories));
            }
            return children;
        }
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public DirectoryItem createDirectoryItem() {
        return new DirectoryItem(getSystemDisplayName(file), this);
    }
}
