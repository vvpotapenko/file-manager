package vvpotapenko.fmanager.service;

import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.local.LocalDirectoryItem;
import vvpotapenko.fmanager.service.local.LocalFileItem;
import vvpotapenko.fmanager.service.zip.ZipFileItem;

import java.io.File;
import java.io.InputStream;

public abstract class BaseFileItemProvider implements IFileItemProvider {

    protected final FileTypeDetector fileTypeDetector = new FileTypeDetector();

    protected IFileItem createLocalFileItem(File file, IFileItem directory) {
        if (file.isDirectory()) {
            return new LocalDirectoryItem(file, directory);
        } else if (file.getName().toLowerCase().endsWith(".zip")) {
            return new ZipFileItem(file, directory);
        } else {
            FileItemType fileType = fileTypeDetector.getFileType(file.getName());
            return new LocalFileItem(file, fileType, directory);
        }
    }

    @Override
    public InputStream createInputStream(IFileItem fileItem) throws Exception {
        throw new Exception("Not Supported");
    }
}
