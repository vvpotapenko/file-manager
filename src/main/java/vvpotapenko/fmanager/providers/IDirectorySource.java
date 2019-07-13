package vvpotapenko.fmanager.providers;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import java.util.List;

public interface IDirectorySource extends IFileSource {

    List<FileItem> loadChildren() throws Exception;

    boolean hasParent();

    IDirectorySource getParent();

    DirectoryItem createDirectoryItem();
}
