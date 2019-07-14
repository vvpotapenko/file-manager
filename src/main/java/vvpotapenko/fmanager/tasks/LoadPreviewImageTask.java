package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.FileItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class LoadPreviewImageTask extends BaseTask<Image, Object> {

    private final FileItem fileItem;
    private final Application app;

    public LoadPreviewImageTask(FileItem fileItem, Application app) {
        this.fileItem = fileItem;
        this.app = app;
    }

    @Override
    protected Image doInBackground() throws Exception {
        InputStream inputStream = fileItem.getFileSource().createInputStream();
        BufferedImage image = ImageIO.read(inputStream);

        int width = image.getWidth();
        int height = image.getHeight();
        while (height > 768) {
            width = width / 2;
            height = height / 2;
        }

        return image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    @Override
    protected void done() {
        try {
            Image img = get();
            app.previewImageLoaded(img, fileItem.getName());
        } catch (Exception e) {
            handleException(e);
        }
    }
}
