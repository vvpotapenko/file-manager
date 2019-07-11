package vvpotapenko.fmanager;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.providers.RootSource;
import vvpotapenko.fmanager.ui.MainFrame;

import javax.swing.*;

class Application {

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

        mainFrame = new MainFrame(treeRoot);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.pack();
    }

}
