package vvpotapenko.fmanager.ui;

import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;

public interface IMainFrameListener {

    void rowClicked(IFileItem fileItem);

    void ftpHostCreated(FtpHost ftpHost);
}
