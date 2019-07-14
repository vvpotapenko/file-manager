package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

public class DestroyTreeChildrenTask extends BaseTask<Object, Object> {

    private final DirectoryItem directoryItem;

    public DestroyTreeChildrenTask(DirectoryItem directoryItem, Application app) {
        super(app);
        this.directoryItem = directoryItem;
    }

    @Override
    protected Object doInBackground() {
        for (FileItem fileItem : directoryItem.getChildren()) {
            fileItem.getFileSource().destroy();
        }
        return null;
    }

    @Override
    void handleResult(Object result) {
        directoryItem.clearChildren();
        app.treeChildrenLoaded(directoryItem);
    }
}
