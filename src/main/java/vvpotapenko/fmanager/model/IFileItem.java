package vvpotapenko.fmanager.model;

import javax.swing.*;

public interface IFileItem extends Comparable<IFileItem> {

    String getName();

    String getDisplaySize();

    FileItemType getFileType();

    Icon getSystemIcon();

    boolean isDirectory();

    IFileItem getParent();
}
