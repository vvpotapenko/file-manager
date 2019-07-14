package vvpotapenko.fmanager;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.model.FileType;
import vvpotapenko.fmanager.providers.RootSource;
import vvpotapenko.fmanager.tasks.*;
import vvpotapenko.fmanager.ui.MainFrame;
import vvpotapenko.fmanager.ui.table.IFilesTableListener;
import vvpotapenko.fmanager.ui.tree.IFilesTreeListener;

import javax.swing.*;
import java.awt.*;

public class Application {

    private MainFrame mainFrame;
    private DirectoryItem treeRoot;

    void run() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        treeRoot = new RootSource(true).createDirectoryItem();
        createView();

        DirectoryItem tableDir = new DirectoryItem("top", new RootSource(false));
        new LoadTableChildrenTask(tableDir, this).execute();
    }

    private void createView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainFrame = new MainFrame(treeRoot,
                new IFilesTreeListener() {
                    @Override
                    public void directoryExpanded(DirectoryItem directoryItem) {
                        new LoadTreeChildrenTask(directoryItem, Application.this).execute();
                    }

                    @Override
                    public void directoryCollapsed(DirectoryItem directoryItem) {
                        new DestroyTreeChildrenTask(directoryItem, Application.this).execute();
                    }

                    @Override
                    public void directorySelected(DirectoryItem directoryItem) {
                        new LoadTableFromTreeTask(directoryItem, Application.this).execute();
                    }
                },
                new IFilesTableListener() {
                    @Override
                    public void directoryClicked(DirectoryItem directoryItem) {
                        new LoadTableChildrenTask(directoryItem, Application.this).execute();
                    }

                    @Override
                    public void upClicked(DirectoryItem currentDir) {
                        new LoadParentTableChildrenTask(currentDir, Application.this).execute();
                    }

                    @Override
                    public void fileClicked(FileItem fileItem) {
                        handlePreviewFile(fileItem);
                    }
                });
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
    }

    private void handlePreviewFile(FileItem fileItem) {
        FileType fileType = fileItem.getFileType();
        if (fileType == null || fileType == FileType.UNKNOWN) {
            mainFrame.showWarningMessage(Resources.getString("file.type.was.not.detected"));
            return;
        }

        switch (fileType) {
            case TEXT:
                new LoadPreviewTextTask(fileItem, this).execute();
                break;
            case JPG:
            case PNG:
                new LoadPreviewImageTask(fileItem, this).execute();
                break;
        }
    }

    public void tableChildrenLoaded(DirectoryItem directoryItem) {
        mainFrame.updateTable(directoryItem);
    }

    public void treeChildrenLoaded(DirectoryItem directoryItem) {
        mainFrame.refreshTree(directoryItem);
    }

    public void previewTextLoaded(String text, String fileName) {
        mainFrame.showPreviewText(text, fileName);
    }

    public void previewImageLoaded(Image img, String fileName) {
        mainFrame.showPreviewImage(img, fileName);
    }
}
