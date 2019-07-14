package vvpotapenko.fmanager.ui;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.ui.preview.PreviewTextDialog;
import vvpotapenko.fmanager.ui.table.FilesTable;
import vvpotapenko.fmanager.ui.table.IFilesTableListener;
import vvpotapenko.fmanager.ui.tree.FilesTree;
import vvpotapenko.fmanager.ui.tree.IFilesTreeListener;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final FilesTree filesTree;
    private final FilesTable filesTable;

    public MainFrame(
            DirectoryItem treeRoot,
            IFilesTreeListener treeListener,
            IFilesTableListener tableListener) throws HeadlessException {
        super(Resources.getString("app.name"));

        filesTree = new FilesTree(treeRoot, treeListener);
        filesTable = new FilesTable(tableListener);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(filesTable, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filesTree, panel);
        splitPane.setDividerLocation(200);

        getContentPane().add(splitPane);
    }

    public void refreshTree(DirectoryItem item) {
        filesTree.refreshTree(item);
    }

    public void updateTable(DirectoryItem directoryItem) {
        filesTable.showFiles(directoryItem);
    }

    public void showPreviewText(String text) {
        PreviewTextDialog dialog = new PreviewTextDialog(this, text);
        dialog.setVisible(true);
    }

    public void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                Resources.getString("app.name"),
                JOptionPane.WARNING_MESSAGE);
    }
}
