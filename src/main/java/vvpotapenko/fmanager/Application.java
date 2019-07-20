package vvpotapenko.fmanager;

import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.FileList;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.FileItemService;
import vvpotapenko.fmanager.service.IFileItemService;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;
import vvpotapenko.fmanager.service.root.RootFileItem;
import vvpotapenko.fmanager.tasks.CreateFtpHostTask;
import vvpotapenko.fmanager.tasks.LoadChildrenTask;
import vvpotapenko.fmanager.tasks.LoadPreviewImageTask;
import vvpotapenko.fmanager.tasks.LoadPreviewTextTask;
import vvpotapenko.fmanager.ui.IMainFrameListener;
import vvpotapenko.fmanager.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Application implements IMainFrameListener {

    private final IFileItemService fileItemService = new FileItemService();

    private FileList fileList;
    private MainFrame mainFrame;

    void run() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        fileList = new FileList();
        createView();

        openDirectory(new RootFileItem());
    }

    private void createView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainFrame = new MainFrame(fileList, this);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
    }

    public void handleException(Exception e) {
        e.printStackTrace();
        mainFrame.showErrorMessage(e.getMessage());
    }

    public void childrenLoaded(List<IFileItem> children) {
        fileList.setItems(children);
        mainFrame.refreshTable();
        mainFrame.refreshActions();
    }

    private void openDirectory(IFileItem fileItem) {
        fileList.setCurrentDirectory(fileItem);

        fileList.showLoading();
        mainFrame.refreshTable();

        new LoadChildrenTask(fileList.getCurrentDirectory(), fileItemService, this).execute();
    }

    @Override
    public void rowClicked(IFileItem fileItem) {
        if (fileItem.isDirectory()) {
            openDirectory(fileItem);
        } else if (fileItem.getFileType() == FileItemType.UP) {
            IFileItem parent = fileList.getCurrentDirectory().getParent();
            if (parent != null) openDirectory(parent);
        } else if (fileItem.getFileType() == FileItemType.LOADING) {
            // ignore loading indicator
        } else {
            handlePreviewFile(fileItem);
        }
    }

    @Override
    public void ftpHostCreated(FtpHost ftpHost) {
        fileList.showLoading();
        mainFrame.refreshTable();

        new CreateFtpHostTask(ftpHost, this).execute();
    }

    private void handlePreviewFile(IFileItem fileItem) {
        FileItemType fileType = fileItem.getFileType();
        if (fileType == null || fileType == FileItemType.UNKNOWN) {
            mainFrame.clearPreview();
            mainFrame.showWarningMessage(Resources.getString("file.type.was.not.detected"));
            return;
        }

        switch (fileType) {
            case TEXT:
                new LoadPreviewTextTask(fileItem, fileItemService, this).execute();
                break;
            case JPG:
            case PNG:
                new LoadPreviewImageTask(fileItem, fileItemService, this).execute();
                break;
        }
    }

    public void previewImageLoaded(Image image, String fileName) {
        mainFrame.showPreviewImage(image, fileName);
    }

    public void previewTextLoaded(String text, String fileName) {
        mainFrame.showPreviewText(text, fileName);
    }

    public void ftpHostListSaved() {
        new LoadChildrenTask(fileList.getCurrentDirectory(), fileItemService, this).execute();
    }
}
