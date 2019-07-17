package vvpotapenko.fmanager.service;

import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.local.LocalFileItemProvider;
import vvpotapenko.fmanager.service.local.MyComputerFileItemProvider;
import vvpotapenko.fmanager.service.root.RootFileItemProvider;
import vvpotapenko.fmanager.service.zip.ZipFileItemProvider;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileItemService implements IFileItemService {

    private final List<IFileItemProvider> providers = new ArrayList<>();

    public FileItemService() {
        providers.add(new RootFileItemProvider());
        providers.add(new MyComputerFileItemProvider());
        providers.add(new LocalFileItemProvider());
        providers.add(new ZipFileItemProvider());
    }

    @Override
    public List<IFileItem> getChildren(IFileItem directory) throws Exception {
        for (IFileItemProvider provider : providers) {
            if (provider.canHandle(directory)) {
                return provider.listFiles(directory);
            }
        }
        throw new Exception("There is no provider for " + directory.getClass());
    }

    @Override
    public InputStream createInputStream(IFileItem fileItem) throws Exception {
        for (IFileItemProvider provider : providers) {
            if (provider.canHandle(fileItem)) {
                return provider.createInputStream(fileItem);
            }
        }
        throw new Exception("There is no provider for " + fileItem.getClass());
    }
}
