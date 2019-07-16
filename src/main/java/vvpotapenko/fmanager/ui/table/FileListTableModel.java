package vvpotapenko.fmanager.ui.table;

import vvpotapenko.fmanager.Resources;
import vvpotapenko.fmanager.model.FileList;
import vvpotapenko.fmanager.model.IFileItem;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

class FileListTableModel extends AbstractTableModel {

    private static final int COLUMN_COUNT = 3;

    private final FileList fileList;

    FileListTableModel(FileList fileList) {
        this.fileList = fileList;
    }

    @Override
    public int getRowCount() {
        return fileList.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IFileItem item = fileList.getItems().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getSystemIcon();
            case 1:
                return item.getName();
            case 2:
                return item.getDisplaySize();
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

    IFileItem getItem(int row) {
        return fileList.getItems().get(row);
    }
}
