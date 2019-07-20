package vvpotapenko.fmanager.tasks;

import vvpotapenko.fmanager.Application;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;
import vvpotapenko.fmanager.service.ftp.storage.FtpHostList;
import vvpotapenko.fmanager.service.ftp.storage.FtpHostListStorage;

import java.util.UUID;

public class CreateFtpHostTask extends BaseTask<Object, Object> {

    private final FtpHost ftpHost;

    public CreateFtpHostTask(FtpHost ftpHost, Application app) {
        super(app);
        this.ftpHost = ftpHost;
    }

    @Override
    protected Object doInBackground() throws Exception {
        String uuid = UUID.randomUUID().toString();
        ftpHost.setUuid(uuid);

        FtpHostListStorage storage = new FtpHostListStorage();
        FtpHostList ftpHostList = storage.load();
        ftpHostList.getHosts().add(ftpHost);

        storage.save(ftpHostList);
        return null;
    }

    @Override
    void afterJob(Object result) {
        app.ftpHostListSaved();
    }
}
