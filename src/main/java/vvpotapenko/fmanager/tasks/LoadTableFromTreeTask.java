package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import java.util.List;

public class LoadTableFromTreeTask extends BaseTask<DirectoryItem, Object> {

    private final DirectoryItem treeDirectoryItem;
    private final Application app;

    public LoadTableFromTreeTask(DirectoryItem treeDirectoryItem, Application app) {
        this.treeDirectoryItem = treeDirectoryItem;
        this.app = app;
    }

    @Override
    protected DirectoryItem doInBackground() throws Exception {
        DirectoryItem directoryItem = treeDirectoryItem.getFileSource().clone(false).createDirectoryItem();
        List<FileItem> fileItems = directoryItem.getFileSource().loadChildren();
        directoryItem.setChildren(fileItems);

        return directoryItem;
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
