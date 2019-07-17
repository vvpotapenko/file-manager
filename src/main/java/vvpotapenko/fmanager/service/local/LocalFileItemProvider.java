package vvpotapenko.fmanager.service.local;

import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.model.UpFileItem;
import vvpotapenko.fmanager.service.BaseFileItemProvider;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LocalFileItemProvider extends BaseFileItemProvider {

    @Override
    public boolean canHandle(IFileItem fileItem) {
        return fileItem instanceof BaseLocalFileItem;
    }

    @Override
    public List<IFileItem> listFiles(IFileItem directory) {
        List<IFileItem> items = new LinkedList<>();

        BaseLocalFileItem localFileItem = (BaseLocalFileItem) directory;
        File[] files = localFileItem.file.listFiles();
        if (files != null) {
            for (File file : files) {
                items.add(createLocalFileItem(file, directory));
            }
        }
        Collections.sort(items);
        items.add(0, UpFileItem.getInstance());

        return items;
    }
}
