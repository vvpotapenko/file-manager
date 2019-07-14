package vvpotapenko.fmanager.ui.table;

import vvpotapenko.fmanager.model.FileItem;

import javax.swing.*;

class FilesTableRow {

    private final FileItem fileItem;

    FilesTableRow(FileItem fileItem) {
        this.fileItem = fileItem;
    }

    FileItem getFileItem() {
        return fileItem;
    }

    String getName() {
        return fileItem == null ? ".." : fileItem.getName();
    }

    String getSize() {
        return fileItem == null ? "" : fileItem.getDisplaySize();
    }

    Icon getSystemIcon() {
        return fileItem == null ? null : fileItem.getSystemIcon();
    }
}
