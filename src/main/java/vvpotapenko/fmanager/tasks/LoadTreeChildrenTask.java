package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import java.util.List;

public class LoadTreeChildrenTask extends BaseTask<List<FileItem>, Object> {

    private final DirectoryItem directoryItem;
    private final Application app;

    public LoadTreeChildrenTask(DirectoryItem directoryItem, Application app) {
        this.directoryItem = directoryItem;
        this.app = app;
    }

    @Override
    protected List<FileItem> doInBackground() throws Exception {
        return directoryItem.getFileSource().loadChildren();
    }

    @Override
    protected void done() {
        try {
            List<FileItem> fileItems = get();
            directoryItem.setChildren(fileItems);

            app.treeChildrenLoaded(directoryItem);
        } catch (Exception e) {
            handleException(e);
        }
    }
}
