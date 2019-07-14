package vvpotapenko.fmanager.ui.tree;

import vvpotapenko.fmanager.model.DirectoryItem;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FilesTree extends JScrollPane {

    private IFilesTreeListener listener;
    private JTree tree;

    public FilesTree(DirectoryItem root, IFilesTreeListener listener) {
        this.listener = listener;
        initializeTree(root);
    }

    private FilesTreeModel getModel() {
        return (FilesTreeModel) tree.getModel();
    }

    private void initializeTree(DirectoryItem root) {
        tree = new JTree(new FilesTreeModel(root));
        tree.setCellRenderer(new FilesTreeCellRenderer());
        tree.setRootVisible(false);
        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                nodeExpended(node);
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                nodeCollapsed(node);
            }
        });
        tree.addTreeSelectionListener(event -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
            nodeSelected(node);
        });

        listener.directoryExpanded(root);

        setViewportView(tree);
    }

    private void nodeCollapsed(DefaultMutableTreeNode node) {
        Object object = node.getUserObject();
        if (object instanceof DirectoryItem) {
            listener.directoryCollapsed((DirectoryItem) object);
        }
    }

    private void nodeExpended(DefaultMutableTreeNode node) {
        Object object = node.getUserObject();
        if (object instanceof DirectoryItem) {
            listener.directoryExpanded((DirectoryItem) object);
        }
    }

    private void nodeSelected(DefaultMutableTreeNode node) {
        Object object = node.getUserObject();
        if (object instanceof DirectoryItem) {
            listener.directorySelected((DirectoryItem) object);
        }
    }

    public void refreshTree(DirectoryItem item) {
        getModel().refreshNode(item);
    }
}
