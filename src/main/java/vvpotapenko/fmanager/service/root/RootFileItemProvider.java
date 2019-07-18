package vvpotapenko.fmanager.service.root;

import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.BaseFileItemProvider;
import vvpotapenko.fmanager.service.ftp.FtpRootItem;
import vvpotapenko.fmanager.service.local.MyComputerFileItem;

import java.util.ArrayList;
import java.util.List;

public class RootFileItemProvider extends BaseFileItemProvider {

    @Override
    public boolean canHandle(IFileItem fileItem) {
        return fileItem instanceof RootFileItem;
    }

    @Override
    public List<IFileItem> listFiles(IFileItem directory) {
        List<IFileItem> items = new ArrayList<>();
        items.add(new MyComputerFileItem(directory));
        items.add(new FtpRootItem(directory));

        return items;
    }
}
