package vvpotapenko.fmanager.ui;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.FileList;
import vvpotapenko.fmanager.ui.preview.PreviewPanel;
import vvpotapenko.fmanager.ui.table.FileListTable;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final FileListTable fileListTable;
    private final PreviewPanel previewPanel;

    public MainFrame(FileList fileList, IMainFrameListener listener) throws HeadlessException {
        super(Resources.getString("app.name"));

        fileListTable = new FileListTable(fileList, listener);
        previewPanel = new PreviewPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fileListTable, previewPanel);
        splitPane.setDividerLocation(700);
        getContentPane().add(splitPane);
    }

    public void refreshTable() {
        fileListTable.refreshList();
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                Resources.getString("app.name"),
                JOptionPane.ERROR_MESSAGE);
    }

    public void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                Resources.getString("app.name"),
                JOptionPane.WARNING_MESSAGE);
    }

    public void showPreviewImage(Image image, String fileName) {
        previewPanel.showPreviewImage(image, fileName);
    }

    public void clearPreview() {
        previewPanel.clear();
    }

    public void showPreviewText(String text, String fileName) {
        previewPanel.showPreviewText(text, fileName);
    }
}
