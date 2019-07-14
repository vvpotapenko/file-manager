package vvpotapenko.fmanager.ui.table;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.DirectoryItem;
import vvpotapenko.fmanager.model.FileItem;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FilesTableModel extends AbstractTableModel {

    private final FilesTableRow upRow = new FilesTableRow(null);
    private final List<FilesTableRow> rows = new ArrayList<>();

    FileItem getFileItemByRow(int row) {
        return rows.get(row).getFileItem();
    }

    void updateRows(DirectoryItem directoryItem) {
        rows.clear();

        if (directoryItem.hasParent()) {
            rows.add(upRow);
        }

        for (FileItem fileItem : directoryItem.getChildren()) {
            rows.add(new FilesTableRow(fileItem));
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "";
            case 1:
                return Resources.getString("name.label");
            case 2:
                return Resources.getString("size.label");
            default:
                return super.getColumnName(column);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FilesTableRow row = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return row.getSystemIcon();
            case 1:
                return row.getName();
            case 2:
                return row.getSize();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return ImageIcon.class;
        }
        return super.getColumnClass(columnIndex);

    }
}
