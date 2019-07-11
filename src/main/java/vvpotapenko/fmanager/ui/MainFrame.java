package vvpotapenko.fmanager.ui;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.DirectoryItem;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(DirectoryItem treeRoot) throws HeadlessException {
        super(Resources.getString("app.name"));

        TreePanel treePanel = new TreePanel(treeRoot);
        ListPanel listPanel = new ListPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, listPanel);
        splitPane.setDividerLocation(150);

        getContentPane().add(splitPane);
    }
}
