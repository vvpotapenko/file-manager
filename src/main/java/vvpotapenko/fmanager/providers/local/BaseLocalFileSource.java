package vvpotapenko.fmanager.providers.local;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

abstract class BaseLocalFileSource {

    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();

    FileItem createFileItem(File file, boolean onlyDirs) {
        String name = fileSystemView.getSystemDisplayName(file);
        if (file.isDirectory()) {
            return new DirectoryItem(name, new LocalDirectorySource(file, onlyDirs));
        } else {
            return new FileItem(name, new LocalFileSource(file));
        }
    }

    Icon getSystemIcon(File file) {
        return fileSystemView.getSystemIcon(file);
    }
}
