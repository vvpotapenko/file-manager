package vvpotapenko.fmanager.service.local;

import org.apache.commons.io.FileUtils;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;

import java.io.File;

public class LocalFileItem extends BaseLocalFileItem {

    private final FileItemType fileType;

    private final String displaySize;

    public LocalFileItem(File file, FileItemType fileType, IFileItem parent) {
        super(file, parent);
        this.fileType = fileType;

        long length = file.length();
        displaySize = FileUtils.byteCountToDisplaySize(length);
    }

    @Override
    public String getDisplaySize() {
        return displaySize;
    }

    @Override
    public FileItemType getFileType() {
        return fileType;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}
