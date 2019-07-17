package vvpotapenko.fmanager.tasks;

import org.apache.commons.io.IOUtils;
import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.IFileItemService;

import java.io.InputStream;

public class LoadPreviewTextTask extends BaseTask<String, Object> {

    private final IFileItem fileItem;
    private final IFileItemService service;

    public LoadPreviewTextTask(IFileItem fileItem, IFileItemService service, Application app) {
        super(app);
        this.fileItem = fileItem;
        this.service = service;
    }

    @Override
    protected String doInBackground() throws Exception {
        InputStream inputStream = service.createInputStream(fileItem);
        return IOUtils.toString(inputStream, "UTF-8");
    }

    @Override
    void afterJob(String result) {
        app.previewTextLoaded(result, fileItem.getName());
    }
}
