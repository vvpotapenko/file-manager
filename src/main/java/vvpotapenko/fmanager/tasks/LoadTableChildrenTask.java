package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import java.util.List;

public class LoadTableChildrenTask extends BaseTask<List<FileItem>, Object> {

    private final DirectoryItem directoryItem;
    private final Application app;

    public LoadTableChildrenTask(DirectoryItem directoryItem, Application app) {
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
            List<FileItem> items = get();
            directoryItem.setChildren(items);

            app.tableChildrenLoaded(directoryItem);
        } catch (Exception e) {
            handleException(e);
        }
    }
}
