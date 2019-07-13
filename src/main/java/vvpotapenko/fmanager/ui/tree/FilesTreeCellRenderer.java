package vvpotapenko.fmanager.ui.tree;

import vvpotapenko.fmanager.model.FileItem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class FilesTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

            Object userObject = node.getUserObject();
            if (userObject instanceof FileItem) {
                FileItem fileItem = (FileItem) userObject;
                label.setText(fileItem.getName());
                label.setIcon(fileItem.getSystemIcon());
            }
        }
        return component;
    }
}
