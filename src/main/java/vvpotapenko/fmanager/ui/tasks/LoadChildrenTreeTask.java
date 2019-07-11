package vvpotapenko.fmanager.ui.tasks;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;
import vvpotapenko.fmanager.ui.TreePanel;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

public class LoadChildrenTreeTask extends BaseTask<DirectoryItem, Object> {

    private final TreePanel treePanel;
    private final DefaultMutableTreeNode node;

    public LoadChildrenTreeTask(TreePanel treePanel, DefaultMutableTreeNode node) {
        this.treePanel = treePanel;
        this.node = node;
    }

    @Override
    protected DirectoryItem doInBackground() throws Exception {
        DirectoryItem directoryItem = (DirectoryItem) node.getUserObject();
        List<FileItem> fileItems = directoryItem.getFileSource().loadChildren();
        directoryItem.setChildren(fileItems);

        return directoryItem;
    }

    @Override
    protected void done() {
        try {
            get();
            treePanel.updateNode(node);
        } catch (Exception e) {
            handleException(e);
        }

    }
}
