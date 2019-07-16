package vvpotapenko.fmanager.model;

import vvpotapenko.fmanager.Resources;

import javax.swing.*;

public class LoadingFileItem extends BaseFileItem {

    public LoadingFileItem() {
        super(null);
    }

    @Override
    public String getName() {
        return Resources.getString("loading.label");
    }

    @Override
    public String getDisplaySize() {
        return null;
    }

    @Override
    public FileItemType getFileType() {
        return FileItemType.LOADING;
    }

    @Override
    public Icon getSystemIcon() {
        return null;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}
