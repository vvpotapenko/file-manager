package vvpotapenko.fmanager.ui.table;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

public interface IFilesTableListener {

    void directoryClicked(DirectoryItem directoryItem);

    void upClicked(DirectoryItem currentDir);

    void fileClicked(FileItem fileItem);
}
