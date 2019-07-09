package vvpotapenko.fmanager.ui;

import javax.swing.*;

public class TreePanel extends JScrollPane {
    public TreePanel() {
        setViewportView(new JTree());
    }
}
