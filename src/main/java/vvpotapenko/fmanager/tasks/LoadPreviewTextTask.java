package vvpotapenko.fmanager.tasks;

import org.apache.commons.io.IOUtils;
import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.FileItem;

import java.io.InputStream;

public class LoadPreviewTextTask extends BaseTask<String, Object> {

    private final FileItem fileItem;
    private final Application app;

    public LoadPreviewTextTask(FileItem fileItem, Application app) {
        this.fileItem = fileItem;
        this.app = app;
    }

    @Override
    protected String doInBackground() throws Exception {
        InputStream inputStream = fileItem.getFileSource().createInputStream();
        return IOUtils.toString(inputStream, "UTF-8");
    }

    @Override
    protected void done() {
        try {
            String data = get();
            app.previewTextLoaded(data);
        } catch (Exception e) {
            handleException(e);
        }
    }
}
