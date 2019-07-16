package vvpotapenko.fmanager.service.local;

import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.model.UpFileItem;
import vvpotapenko.fmanager.service.FileTypeDetector;
import vvpotapenko.fmanager.service.IFileItemProvider;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LocalFileItemProvider implements IFileItemProvider {

    private static final Comparator<IFileItem> defaultFilesComparator = (o1, o2) -> {
        if (o1.isDirectory() && !o2.isDirectory()) {
            return -1;
        }
        if (o2.isDirectory() && !o1.isDirectory()) {
            return 1;
        }
        return o1.getName().compareTo(o2.getName());
    };

    private final FileTypeDetector fileTypeDetector = new FileTypeDetector();

    @Override
    public boolean canHandle(IFileItem fileItem) {
        return fileItem instanceof BaseLocalFileItem;
    }

    @Override
    public List<IFileItem> listFiles(IFileItem directory) {
        List<IFileItem> items = new LinkedList<>();

        BaseLocalFileItem localFileItem = (BaseLocalFileItem) directory;
        File[] files = localFileItem.file.listFiles();
        if (files != null) {
            for (File file : files) {
                items.add(createFileItem(file, directory));
            }
        }
        items.sort(defaultFilesComparator);
        items.add(0, UpFileItem.getInstance());

        return items;
    }

    private IFileItem createFileItem(File file, IFileItem directory) {
        if (file.isDirectory()) {
            return new LocalDirectoryItem(file, directory);
        } else if (file.getName().toLowerCase().endsWith(".zip")) {
            // TODO
            return new LocalFileItem(file, FileItemType.UNKNOWN, directory);
        } else {
            FileItemType fileType = fileTypeDetector.getFileType(file.getName());
            return new LocalFileItem(file, fileType, directory);
        }
    }
}
