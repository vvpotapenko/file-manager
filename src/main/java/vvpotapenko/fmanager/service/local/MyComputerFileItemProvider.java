package vvpotapenko.fmanager.service.local;

import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.model.UpFileItem;
import vvpotapenko.fmanager.service.IFileItemProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyComputerFileItemProvider implements IFileItemProvider {

    @Override
    public boolean canHandle(IFileItem fileItem) {
        return fileItem instanceof MyComputerFileItem;
    }

    @Override
    public List<IFileItem> listFiles(IFileItem directory) throws Exception {
        List<IFileItem> items = new ArrayList<>();
        items.add(UpFileItem.getInstance());

        File[] files = File.listRoots();
        if (files != null) {
            for (File file : files) {
                items.add(new LocalDirectoryItem(file, directory));
            }
        }
        return items;
    }
}
