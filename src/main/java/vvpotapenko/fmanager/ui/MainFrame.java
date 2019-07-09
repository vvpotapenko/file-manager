package vvpotapenko.fmanager.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        super(Resources.getString("app.name"));

        initializeLayout();
    }

    private void initializeLayout() {
        TreePanel treePanel = new TreePanel();
        ListPanel listPanel = new ListPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, listPanel);
        splitPane.setDividerLocation(150);

        getContentPane().add(splitPane);
    }
}
