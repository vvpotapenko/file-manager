package vvpotapenko.fmanager.ui;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.ui.tasks.LoadChildrenTreeTask;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreePanel extends JScrollPane {

    private JTree tree;

    TreePanel(DirectoryItem root) {
        initializeTree(root);
    }

    public void updateNode(DefaultMutableTreeNode node) {
        node.removeAllChildren();

        DirectoryItem directoryItem = (DirectoryItem) node.getUserObject();
        for (FileItem fileItem : directoryItem.getChildren()) {
            node.add(createNode(fileItem));
        }

        ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(node);
    }

    private void initializeTree(DirectoryItem root) {
        DefaultMutableTreeNode rootNode = createNode(root);
        loadNode(rootNode);

        tree = new JTree(new DefaultTreeModel(rootNode));
        tree.setRootVisible(false);

        setViewportView(tree);
    }

    private DefaultMutableTreeNode createNode(FileItem fileItem) {
        if (fileItem.isDirectory()) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileItem, true);
            node.add(new DefaultMutableTreeNode("Loading...", false)); // TODO loading child

            return node;
        } else {
            return new DefaultMutableTreeNode(fileItem);
        }
    }

    private void loadNode(DefaultMutableTreeNode node) {
        new LoadChildrenTreeTask(this, node).execute();
    }
}
