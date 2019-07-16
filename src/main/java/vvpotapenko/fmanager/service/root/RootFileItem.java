package vvpotapenko.fmanager.service.root;

import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.FileItemType;

import javax.swing.*;

public class RootFileItem extends BaseFileItem {

    public RootFileItem() {
        super(null);
    }

    @Override
    public String getName() {
        return "top";
    }

    @Override
    public String getDisplaySize() {
        return null;
    }

    @Override
    public FileItemType getFileType() {
        return FileItemType.UNKNOWN;
    }

    @Override
    public Icon getSystemIcon() {
        return null;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}
