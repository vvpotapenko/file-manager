package vvpotapenko.fmanager.service.local;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.BaseFileItem;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;

import javax.swing.*;

public class MyComputerFileItem extends BaseFileItem {

    public MyComputerFileItem(IFileItem parent) {
        super(parent);
    }

    @Override
    public String getName() {
        return Resources.getString("my.computer.label");
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
    public Icon getSystemIcon() {
        return null;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}
