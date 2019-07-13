package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

public class DestroyTreeChildrenTask extends BaseTask<Object, Object> {

    private final DirectoryItem directoryItem;
    private final Application app;

    public DestroyTreeChildrenTask(DirectoryItem directoryItem, Application app) {
        this.directoryItem = directoryItem;
        this.app = app;
    }

    @Override
    protected Object doInBackground() throws Exception {
        for (FileItem fileItem : directoryItem.getChildren()) {
            fileItem.getFileSource().destroy();
        }
        return null;
    }

    @Override
    protected void done() {
        try {
            get();
            directoryItem.clearChildren();
            app.treeChildrenLoaded(directoryItem);
        } catch (Exception e) {
            handleException(e);
        }
    }
}
