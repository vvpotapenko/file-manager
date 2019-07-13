package vvpotapenko.fmanager.ui.tree;

import vvpotapenko.fmanager.model.DirectoryItem;

public interface IFilesTreeListener {

    void directoryExpanded(DirectoryItem directoryItem);

    void directoryCollapsed(DirectoryItem directoryItem);
}
