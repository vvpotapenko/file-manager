package vvpotapenko.fmanager.model;

import javax.swing.*;

public class UpFileItem extends BaseFileItem {

    private static final UpFileItem instance = new UpFileItem();

    public static UpFileItem getInstance() {
        return instance;
    }

    private UpFileItem() {
        super(null);
    }

    @Override
    public String getName() {
        return "..";
    }

    @Override
    public String getDisplaySize() {
        return null;
    }

    @Override
    public FileItemType getFileType() {
        return FileItemType.UP;
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
