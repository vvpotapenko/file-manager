package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.IFileItemService;

import java.util.List;

public class LoadChildrenTask extends BaseTask<List<IFileItem>, Object> {

    private final IFileItem directory;
    private final IFileItemService service;

    public LoadChildrenTask(IFileItem directory, IFileItemService service, Application app) {
        super(app);
        this.directory = directory;
        this.service = service;
    }

    @Override
    protected List<IFileItem> doInBackground() throws Exception {
        return service.getChildren(directory);
    }

    @Override
    void afterJob(List<IFileItem> result) {
        app.childrenLoaded(result);
    }
}
