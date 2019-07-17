package vvpotapenko.fmanager.service.local;

import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;

import java.io.File;

public class LocalDirectoryItem extends BaseLocalFileItem {

    public LocalDirectoryItem(File file, IFileItem parent) {
        super(file, parent);
    }

    @Override
    public String getDisplaySize() {
        return null;
    }

    @Override
    public FileItemType getFileType() {
        return null;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}
