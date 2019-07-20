package vvpotapenko.fmanager.service.ftp;

import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.model.UpFileItem;
import vvpotapenko.fmanager.service.BaseFileItemProvider;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;
import vvpotapenko.fmanager.service.ftp.storage.FtpHostList;
import vvpotapenko.fmanager.service.ftp.storage.FtpHostListStorage;

import java.util.ArrayList;
import java.util.List;

public class FtpRootItemProvider extends BaseFileItemProvider {

    private final FtpHostListStorage storage = new FtpHostListStorage();

    @Override
    public boolean canHandle(IFileItem fileItem) {
        return fileItem instanceof FtpRootItem;
    }

    @Override
    public List<IFileItem> listFiles(IFileItem directory) throws Exception {
        List<IFileItem> items = new ArrayList<>();
        items.add(UpFileItem.getInstance());

        FtpHostList ftpHosts = storage.load();
        for (FtpHost host : ftpHosts.getHosts()) {
            items.add(new FtpHostItem(host, directory));
        }

        return items;
    }
}
