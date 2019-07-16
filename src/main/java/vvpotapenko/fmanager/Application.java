package vvpotapenko.fmanager;

import vvpotapenko.fmanager.model.FileItemType;
import vvpotapenko.fmanager.model.FileList;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.service.FileItemService;
import vvpotapenko.fmanager.service.IFileItemService;
import vvpotapenko.fmanager.service.root.RootFileItem;
import vvpotapenko.fmanager.tasks.LoadChildrenTask;
import vvpotapenko.fmanager.ui.IMainFrameListener;
import vvpotapenko.fmanager.ui.MainFrame;

import javax.swing.*;
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

    public void childrenLoaded(List<IFileItem> children) {
        fileList.setItems(children);
        mainFrame.refreshTable();
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
            // TODO go up
        } else if (fileItem.getFileType() == FileItemType.LOADING) {
            // ignore loading indicator
        } else {
            // TODO show preview
        }
    }
}
