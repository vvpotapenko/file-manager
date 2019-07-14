package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import java.util.List;

public class LoadTreeChildrenTask extends BaseTask<List<FileItem>, Object> {

    private final DirectoryItem directoryItem;

    public LoadTreeChildrenTask(DirectoryItem directoryItem, Application app) {
        super(app);
        this.directoryItem = directoryItem;
    }

    @Override
    protected List<FileItem> doInBackground() throws Exception {
        return directoryItem.getFileSource().loadChildren();
    }

    @Override
    void handleResult(List<FileItem> result) {
        directoryItem.setChildren(result);
        app.treeChildrenLoaded(directoryItem);
    }
}
