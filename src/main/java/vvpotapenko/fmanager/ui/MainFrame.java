package vvpotapenko.fmanager.ui;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.FileItemActionType;
import vvpotapenko.fmanager.model.FileList;
import vvpotapenko.fmanager.service.ftp.storage.FtpHost;
import vvpotapenko.fmanager.ui.ftp.FtpHostEditDialog;
import vvpotapenko.fmanager.ui.preview.PreviewPanel;
import vvpotapenko.fmanager.ui.table.FileListTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class MainFrame extends JFrame {

    private final IMainFrameListener listener;

    private final FileList fileList;

    private final FileListTable fileListTable;
    private final PreviewPanel previewPanel;
    private final JButton newFtpButton;

    public MainFrame(FileList fileList, IMainFrameListener listener) throws HeadlessException {
        super(Resources.getString("app.name"));
        this.listener = listener;

        this.fileList = fileList;

        fileListTable = new FileListTable(fileList, listener);
        previewPanel = new PreviewPanel();

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        newFtpButton = new JButton();
        newFtpButton.setVisible(false);
        newFtpButton.setText(Resources.getString("new.ftp.host"));
        newFtpButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFtpHostClicked();
            }
        });
        toolBar.add(newFtpButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(toolBar, BorderLayout.NORTH);
        panel.add(fileListTable, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, previewPanel);
        splitPane.setDividerLocation(1000);
        getContentPane().add(splitPane);
    }

    public void refreshActions() {
        Collection<FileItemActionType> availableActions = fileList.getCurrentDirectory().getAvailableActions();
        newFtpButton.setVisible(availableActions.contains(FileItemActionType.NEW_FTP_HOST));
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

    private void newFtpHostClicked() {
        final FtpHost host = new FtpHost();

        FtpHostEditDialog dialog = new FtpHostEditDialog(this, host, Resources.getString("new.ftp.host"));
        dialog.setListener(() -> {
            listener.ftpHostCreated(host);
        });
        dialog.setVisible(true);
    }
}
