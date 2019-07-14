package vvpotapenko.fmanager.tasks;

import org.apache.commons.io.IOUtils;
import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.FileItem;

import java.io.InputStream;

public class LoadPreviewTextTask extends BaseTask<String, Object> {

    private final FileItem fileItem;

    public LoadPreviewTextTask(FileItem fileItem, Application app) {
        super(app);
        this.fileItem = fileItem;
    }

    @Override
    protected String doInBackground() throws Exception {
        InputStream inputStream = fileItem.getFileSource().createInputStream();
        return IOUtils.toString(inputStream, "UTF-8");
    }

    @Override
    void handleResult(String result) {
        app.previewTextLoaded(result, fileItem.getName());
    }

}
