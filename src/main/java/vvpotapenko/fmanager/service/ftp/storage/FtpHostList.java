package vvpotapenko.fmanager.service.ftp.storage;

import java.util.ArrayList;
import java.util.List;

public class FtpHostList {

    private List<FtpHost> hosts = new ArrayList<>();

    public List<FtpHost> getHosts() {
        return hosts;
    }

    public void setHosts(List<FtpHost> hosts) {
        this.hosts = hosts;
    }
}
