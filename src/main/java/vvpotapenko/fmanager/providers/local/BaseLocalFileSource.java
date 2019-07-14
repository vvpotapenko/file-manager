package vvpotapenko.fmanager.providers.local;

import org.apache.commons.io.FileUtils;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.model.FileType;
import vvpotapenko.fmanager.providers.FileTypeDetector;
import vvpotapenko.fmanager.providers.IDirectorySource;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

abstract class BaseLocalFileSource {

    private final FileTypeDetector fileTypeDetector = new FileTypeDetector();
    private final FileSystemView fileSystemView = FileSystemView.getFileSystemView();

    FileItem createFileItem(File file, IDirectorySource parent, boolean onlyDirs) {
        String name = getSystemDisplayName(file);
        if (file.isDirectory()) {
            return new DirectoryItem(name, new LocalDirectorySource(file, parent, onlyDirs));
        } else {
            FileType fileType = fileTypeDetector.getFileType(file.getName());
            return new FileItem(name, fileType, new LocalFileSource(file));
        }
    }

    String getSystemDisplayName(File file) {
        return fileSystemView.getSystemDisplayName(file);
    }

    Icon getSystemIcon(File file) {
        return fileSystemView.getSystemIcon(file);
    }

    String getDisplaySize(File file) {
        long length = file.length();
        return FileUtils.byteCountToDisplaySize(length);
    }
}
