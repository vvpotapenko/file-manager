package vvpotapenko.fmanager.model;

import javax.swing.*;
import java.util.Collection;

public interface IFileItem extends Comparable<IFileItem> {

    String getName();

    String getDisplaySize();

    FileItemType getFileType();

    Icon getSystemIcon();

    boolean isDirectory();

    IFileItem getParent();

    Collection<FileItemActionType> getAvailableActions();
}
