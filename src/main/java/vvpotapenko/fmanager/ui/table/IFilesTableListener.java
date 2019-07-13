package vvpotapenko.fmanager.ui.table;

import vvpotapenko.fmanager.model.DirectoryItem;

public interface IFilesTableListener {

    void directoryClicked(DirectoryItem directoryItem);

    void upClicked(DirectoryItem currentDir);
}
