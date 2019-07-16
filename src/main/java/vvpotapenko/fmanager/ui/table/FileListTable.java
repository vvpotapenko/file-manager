package vvpotapenko.fmanager.ui.table;

import vvpotapenko.fmanager.model.FileList;
import vvpotapenko.fmanager.model.IFileItem;
import vvpotapenko.fmanager.ui.IMainFrameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FileListTable extends JScrollPane {

    private final JTable table;
    private final IMainFrameListener listener;

    public FileListTable(FileList fileList, IMainFrameListener listener) {
        this.listener = listener;

        table = new JTable();
        table.setModel(new FileListTableModel(fileList));
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

    private FileListTableModel getModel() {
        return (FileListTableModel) table.getModel();
    }

    private void handleDoubleClickRow(int row) {
        IFileItem fileItem = getModel().getItem(row);
        listener.rowClicked(fileItem);
    }

    public void refreshList() {
        getModel().fireTableDataChanged();
    }
}
