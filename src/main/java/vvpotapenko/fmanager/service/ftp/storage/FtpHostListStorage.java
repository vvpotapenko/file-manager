package vvpotapenko.fmanager.service.ftp.storage;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class FtpHostListStorage {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void save(List<FtpHost> list) throws Exception {
        // TODO
    }

    public List<FtpHost> load() throws Exception {
        // TODO
        List<FtpHost> hosts = new ArrayList<>();
        FtpHost host = new FtpHost();
        host.setHostname("mirror.waia.asn.au");
        host.setPassiveMode(true);
        host.setAnonymous(true);
        hosts.add(host);

        host = new FtpHost();
        host.setHostname("ftp.netspace.net.au");
        host.setPassiveMode(true);
        host.setAnonymous(true);
        hosts.add(host);

        return hosts;
    }
}
