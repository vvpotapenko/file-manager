package vvpotapenko.fmanager;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.providers.RootSource;
import vvpotapenko.fmanager.tasks.DestroyTreeChildrenTask;
import vvpotapenko.fmanager.tasks.LoadTreeChildrenTask;
import vvpotapenko.fmanager.ui.MainFrame;
import vvpotapenko.fmanager.ui.tree.IFilesTreeListener;

import javax.swing.*;

public class Application implements IFilesTreeListener {

    private MainFrame mainFrame;
    private DirectoryItem treeRoot;

    void run() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        createModel();
        createView();
        mainFrame.setVisible(true);
    }

    private void createModel() {
        treeRoot = new DirectoryItem("top", new RootSource(true));

        // TODO create table
    }

    private void createView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainFrame = new MainFrame(treeRoot, this);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.pack();
    }

    public void treeChildrenLoaded(DirectoryItem directoryItem) {
        mainFrame.refreshTree(directoryItem);
    }

    @Override
    public void handleOpenDirectory(DirectoryItem directoryItem) {
        new LoadTreeChildrenTask(directoryItem, this).execute();
    }

    @Override
    public void handleCloseDirectory(DirectoryItem directoryItem) {
        new DestroyTreeChildrenTask(directoryItem, this).execute();
    }
}
