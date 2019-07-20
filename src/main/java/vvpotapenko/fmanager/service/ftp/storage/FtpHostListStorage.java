package vvpotapenko.fmanager.service.ftp.storage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class FtpHostListStorage {

    private final ObjectMapper objectMapper;

    public FtpHostListStorage() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void save(FtpHostList hostList) throws Exception {
        File hostFile = getConfigHostFile();
        FileUtils.forceMkdirParent(hostFile);

        objectMapper.writeValue(hostFile, hostList);
    }

    public FtpHostList load() throws Exception {
        File configFile = getConfigHostFile();
        if (configFile.exists()) {
            return objectMapper.readValue(configFile, FtpHostList.class);
        } else {
            return new FtpHostList();
        }
    }

    private File getConfigHostFile() {
        String home = System.getProperty("user.home");
        return new File(home, FilenameUtils.concat(".fs-manager", "ftp-hosts.json"));
    }
}
