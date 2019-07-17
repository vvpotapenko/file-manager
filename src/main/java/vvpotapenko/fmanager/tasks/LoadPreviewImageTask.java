package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.IFileItemService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class LoadPreviewImageTask extends BaseTask<Image, Object> {

    private final IFileItem fileItem;
    private final IFileItemService service;

    public LoadPreviewImageTask(IFileItem fileItem, IFileItemService service, Application app) {
        super(app);
        this.fileItem = fileItem;
        this.service = service;
    }

    @Override
    protected Image doInBackground() throws Exception {
        InputStream inputStream = service.createInputStream(fileItem);
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
    void afterJob(Image result) {
        app.previewImageLoaded(result, fileItem.getName());
    }
}
