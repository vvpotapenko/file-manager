package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import java.util.List;

public class LoadTableFromTreeTask extends BaseTask<DirectoryItem, Object> {

    private final DirectoryItem treeDirectoryItem;

    public LoadTableFromTreeTask(DirectoryItem treeDirectoryItem, Application app) {
        super(app);
        this.treeDirectoryItem = treeDirectoryItem;
    }

    @Override
    protected DirectoryItem doInBackground() throws Exception {
        DirectoryItem directoryItem = treeDirectoryItem.getFileSource().clone(false).createDirectoryItem();
        List<FileItem> fileItems = directoryItem.getFileSource().loadChildren();
        directoryItem.setChildren(fileItems);

        return directoryItem;
    }

    @Override
    void handleResult(DirectoryItem result) {
        app.tableChildrenLoaded(result);
    }
}
