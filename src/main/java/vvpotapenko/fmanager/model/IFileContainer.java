package vvpotapenko.fmanager.model;

import java.util.List;

public interface IFileContainer extends IFile {

    List<IFile> getChildren();

    boolean hasChildren();
}
