package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.providers.IDirectorySource;

import java.util.List;

public class LoadParentTableChildrenTask extends BaseTask<DirectoryItem, Object> {

    private final DirectoryItem directoryItem;
    private final Application app;

    public LoadParentTableChildrenTask(DirectoryItem directoryItem, Application app) {
        this.directoryItem = directoryItem;
        this.app = app;
    }

    @Override
    protected DirectoryItem doInBackground() throws Exception {
        IDirectorySource parent = directoryItem.getFileSource().getParent();
        if (parent != null) {
            DirectoryItem parentDir = parent.createDirectoryItem();
            List<FileItem> fileItems = parentDir.getFileSource().loadChildren();
            parentDir.setChildren(fileItems);

            return parentDir;
        } else {
            return directoryItem;
        }
    }

    @Override
    protected void done() {
        try {
            DirectoryItem directoryItem = get();
            app.tableChildrenLoaded(directoryItem);
        } catch (Exception e) {
            handleException(e);
        }
    }
}
