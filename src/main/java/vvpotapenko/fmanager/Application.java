package vvpotapenko.fmanager;

import vvpotapenko.fmanager.ui.MainFrame;

import javax.swing.*;

public class Application {

    private MainFrame mainFrame;

    public void run() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // TODO create model
        createView();
        mainFrame.setVisible(true);
    }

    private void createView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.pack();
    }
}
