package vvpotapenko.fmanager.service.root;

import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.IFileItemProvider;
import vvpotapenko.fmanager.service.local.MyComputerFileItem;

import java.util.ArrayList;
import java.util.List;

public class RootFileItemProvider implements IFileItemProvider {

    @Override
    public boolean canHandle(IFileItem fileItem) {
        return fileItem instanceof RootFileItem;
    }

    @Override
    public List<IFileItem> listFiles(IFileItem directory) throws Exception {
        List<IFileItem> items = new ArrayList<>();
        items.add(new MyComputerFileItem(directory));

        return items;
    }
}
