package com.selfproduction;

import java.util.List;

/**
 * Created by anna
 * on 26.12.16.
 */
public interface MainTableModelListener {
    public void onDataChanged(MainTableModel model, List<MainTableItem> data);
}
