package vvpotapenko.fmanager.ui;

import javax.swing.*;
import java.awt.*;

public class ListPanel extends JPanel {

    public ListPanel() {
        initializeLayout();
    }

    private void initializeLayout() {
        setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        JButton button = new JButton();
        button.setText("Button 1");
        toolBar.add(button);
        button = new JButton();
        button.setText("Button 2");
        toolBar.add(button);
        button = new JButton();
        button.setText("Button 3");
        toolBar.add(button);

        setPreferredSize(new Dimension(450, 130));
        add(toolBar, BorderLayout.PAGE_START);

        JTable filesTable = new JTable();
        add(new JScrollPane(filesTable), BorderLayout.CENTER);

        // TODO preview panel
    }
}
