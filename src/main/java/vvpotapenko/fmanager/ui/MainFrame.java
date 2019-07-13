package vvpotapenko.fmanager.ui;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.ui.list.FilesList;
import vvpotapenko.fmanager.ui.tree.FilesTree;
import vvpotapenko.fmanager.ui.tree.IFilesTreeListener;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final FilesTree filesTree;

    public MainFrame(DirectoryItem treeRoot, IFilesTreeListener listener) throws HeadlessException {
        super(Resources.getString("app.name"));

        filesTree = new FilesTree(treeRoot, listener);
        FilesList filesList = new FilesList();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filesTree, filesList);
        splitPane.setDividerLocation(200);

        getContentPane().add(splitPane);
    }

    public void refreshTree(DirectoryItem item) {
        filesTree.refreshTree(item);
    }
}
