package vvpotapenko.fmanager.providers.local;

import org.apache.commons.io.filefilter.FileFilterUtils;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.providers.IDirectorySource;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalDirectorySource extends BaseLocalFileSource implements IDirectorySource {

    private final File file;
    private final boolean onlyDirectories;

    LocalDirectorySource(File file, boolean onlyDirectories) {
        this.file = file;
        this.onlyDirectories = onlyDirectories;
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
                children.add(createFileItem(file, this.onlyDirectories));
            }
            return children;
        }
    }
}
