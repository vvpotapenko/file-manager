package vvpotapenko.fmanager.ui.table;

import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FilesTable extends JScrollPane {

    private final IFilesTableListener listener;

    private JTable table;
    private DirectoryItem currentDir;

    public FilesTable(IFilesTableListener listener) {
        this.listener = listener;
        initializeLayout();
    }

    public void showFiles(DirectoryItem directoryItem) {
        currentDir = directoryItem;
        getModel().updateRows(currentDir);
    }

    private FilesTableModel getModel() {
        return (FilesTableModel) table.getModel();
    }

    private void initializeLayout() {
        table = new JTable();
        table.setModel(new FilesTableModel());
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.setRowHeight(32);
        table.getColumnModel().getColumn(0).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setMaxWidth(150);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                if (row >= 0 && e.getClickCount() == 2) {
                    handleDoubleClickRow(row);
                }
            }
        });

        setViewportView(table);
    }

    private void handleDoubleClickRow(int row) {
        FileItem fileItem = getModel().getFileItemByRow(row);
        if (fileItem == null) {
            listener.upClicked(currentDir);
        } else if (fileItem.isDirectory()) {
            listener.directoryClicked((DirectoryItem) fileItem);
        } else {
            listener.fileClicked(fileItem);
        }
    }
}
