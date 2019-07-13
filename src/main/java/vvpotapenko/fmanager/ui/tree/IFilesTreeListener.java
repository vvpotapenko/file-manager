package vvpotapenko.fmanager.ui.tree;

import vvpotapenko.fmanager.model.DirectoryItem;

public interface IFilesTreeListener {

    void handleOpenDirectory(DirectoryItem directoryItem);

    void handleCloseDirectory(DirectoryItem directoryItem);
}
