package vvpotapenko.fmanager.service.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.model.UpFileItem;
import vvpotapenko.fmanager.service.BaseFileItemProvider;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

abstract class BaseFtpItemProvider extends BaseFileItemProvider {

    List<IFileItem> listFtpFiles(IFileItem directory, FtpHost host, String path) throws Exception {
        FTPClient ftpClient = connectToHost(host);
        FTPFile[] ftpFiles = ftpClient.listFiles(path);

        List<IFileItem> items = new LinkedList<>();
        for (FTPFile file : ftpFiles) {
            if (file.isDirectory()) {
                items.add(new FtpDirectoryItem(file, host, directory, path));
            } else if (file.isFile()) {
                FileItemType fileType = fileTypeDetector.getFileType(file.getName());
                items.add(new FtpFileItem(file, fileType, host, directory, path));
            } else {
                // ignore link
            }
        }

        ftpClient.logout();
        ftpClient.disconnect();

        Collections.sort(items);
        items.add(0, UpFileItem.getInstance());
        return items;
    }

    InputStream retrieveFtpFile(FtpHost host, String filePath) throws IOException {
        FTPClient ftpClient = connectToHost(host);
        return ftpClient.retrieveFileStream(filePath);
    }

    private FTPClient connectToHost(FtpHost host) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(host.getHostname());

        if (host.isPassiveMode()) {
            ftpClient.enterLocalPassiveMode();
        }

        if (host.isAnonymous()) {
            ftpClient.login("anonymous", "");
        } else {
            ftpClient.login(host.getUsername(), host.getPassword());
        }
        return ftpClient;
    }


}
