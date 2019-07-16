package vvpotapenko.fmanager.model;

import javax.swing.*;

public interface IFileItem {

    String getName();

    String getDisplaySize();

    FileItemType getFileType();

    Icon getSystemIcon();

    boolean isDirectory();

    IFileItem getParent();
}
