package com.selfproduction;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sckomoroh
 * on 26.12.16.
 */
public class MainTableModel extends AbstractTableModel {
    private List<MainTableItem> data;
    private List<MainTableModelListener> listeners;

    public MainTableModel()
    {
        data = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void addListener(MainTableModelListener listener)
    {
        listeners.add(listener);
    }

    public void addEmptyItem()
    {
        data.add(new MainTableItem());

        this.fireTableDataChanged();
    }

    public List<MainTableItem> getItems()
    {
        return data;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 4;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;
            case 4:
                return double.class;
        }

        return null;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0:
                return "Included";
            case 1:
                return "Title";
            case 2:
                return "Price";
            case 3:
                return "Count";
            case 4:
                return "Actual price";
        }

        return null;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MainTableItem item = data.get(rowIndex);

        switch (columnIndex)
        {
            case 0:
                return item.isInclude();
            case 1:
                return item.getTitle();
            case 2:
                return item.getPrice();
            case 3:
                return item.getCount();
            case 4:
                return item.getActualPrice();
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        MainTableItem item = data.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                item.setInclude((boolean)aValue);
                break;
            case 1:
                item.setTitle((String)aValue);
                break;
            case 2:
                item.setPrice((double)aValue);
                break;
            case 3:
                item.setCount((double)aValue);
                break;
        }

        fireDataChanged();
    }

    private void fireDataChanged()
    {
        for (MainTableModelListener listener : listeners)
        {
            listener.onDataChanged(this, data);
        }
    }
}
