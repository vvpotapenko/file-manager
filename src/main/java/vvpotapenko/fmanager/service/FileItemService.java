package vvpotapenko.fmanager.service;

import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.local.LocalFileItemProvider;
import vvpotapenko.fmanager.service.local.MyComputerFileItemProvider;
import vvpotapenko.fmanager.service.root.RootFileItemProvider;

import java.util.ArrayList;
import java.util.List;

public class FileItemService implements IFileItemService {

    private final List<IFileItemProvider> providers = new ArrayList<>();

    public FileItemService() {
        providers.add(new RootFileItemProvider());
        providers.add(new MyComputerFileItemProvider());
        providers.add(new LocalFileItemProvider());
    }

    @Override
    public List<IFileItem> getChildren(IFileItem parent) throws Exception {
        for (IFileItemProvider provider : providers) {
            if (provider.canHandle(parent)) {
                return provider.listFiles(parent);
            }
        }
        throw new Exception("There is no provider for " + parent.getClass());
    }
}
