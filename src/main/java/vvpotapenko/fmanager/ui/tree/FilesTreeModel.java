package vvpotapenko.fmanager.ui.tree;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;

class FilesTreeModel extends DefaultTreeModel {

    FilesTreeModel(DirectoryItem root) {
        super(null);
        setRoot(createNode(root));
    }

    private DefaultMutableTreeNode createNode(FileItem fileItem) {
        if (fileItem.isDirectory()) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileItem, true);
            node.add(new DefaultMutableTreeNode("Loading...", false));

            return node;
        } else {
            return new DefaultMutableTreeNode(fileItem);
        }
    }

    void refreshNode(DirectoryItem item) {
        DefaultMutableTreeNode node = findNodeByFile(item);
        if (node != null) {
            node.removeAllChildren();
            for (FileItem child : item.getChildren()) {
                node.add(createNode(child));
            }

            if (item.getChildren().isEmpty()) {
                node.add(new DefaultMutableTreeNode("Loading...", false));
            }

            nodeStructureChanged(node);
        } else {
            // TODO
            System.out.println("Something went wrong");
        }
    }

    private DefaultMutableTreeNode findNodeByFile(FileItem item) {
        Object root = getRoot();
        return findNodeByFile((DefaultMutableTreeNode) root, item);
    }

    private DefaultMutableTreeNode findNodeByFile(DefaultMutableTreeNode node, FileItem item) {
        if (item.equals(node.getUserObject())) {
            return node;
        }

        Enumeration<TreeNode> children = node.children();
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode next = (DefaultMutableTreeNode) children.nextElement();
            DefaultMutableTreeNode childNode = findNodeByFile(next, item);
            if (childNode != null) return childNode;
        }

        return null;
    }
}
