package vvpotapenko.fmanager.ui;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.FileList;
import vvpotapenko.fmanager.ui.preview.PreviewImageDialog;
import vvpotapenko.fmanager.ui.preview.PreviewTextDialog;
import vvpotapenko.fmanager.ui.table.FileListTable;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final FileListTable fileListTable;

    public MainFrame(FileList fileList, IMainFrameListener listener) throws HeadlessException {
        super(Resources.getString("app.name"));

        fileListTable = new FileListTable(fileList, listener);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(fileListTable, BorderLayout.CENTER);

        getContentPane().add(panel);
    }

    public void refreshTable() {
        fileListTable.refreshList();
    }

    public void showPreviewText(String text, String title) {
        PreviewTextDialog dialog = new PreviewTextDialog(this, title, text);
        dialog.setVisible(true);
    }

    public void showPreviewImage(Image img, String title) {
        PreviewImageDialog dialog = new PreviewImageDialog(this, title, img);
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
