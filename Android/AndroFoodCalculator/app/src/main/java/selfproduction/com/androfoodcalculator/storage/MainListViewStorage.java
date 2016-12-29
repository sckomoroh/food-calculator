package selfproduction.com.androfoodcalculator.storage;

import java.util.ArrayList;
import java.util.List;

import selfproduction.com.androfoodcalculator.model.FoodGroupItem;

/**
 * Created by anna on 28.12.16.
 */

public class MainListViewStorage {
    private List<MainListViewStorageListener> listeners;
    private List<FoodGroupItem> groups;

    public MainListViewStorage()
    {
        listeners = new ArrayList<>();
        groups = new ArrayList<>();
    }

    public void addListener(MainListViewStorageListener listener)
    {
        listeners.add(listener);
    }

    public void addGroup(FoodGroupItem foodGroupItem)
    {
        groups.add(foodGroupItem);

        fireStorageChanged();
    }

    public void removeGroup(int index)
    {
        groups.remove(index);

        fireStorageChanged();
    }

    public void clear()
    {
        groups.clear();

        fireStorageChanged();
        notifyDataChanged();
    }

    public List<FoodGroupItem> getGroups()
    {
        return groups;
    }

    public int getGroupsCount()
    {
        return groups.size();
    }

    public FoodGroupItem getGroup(int index)
    {
        return groups.get(index);
    }

    public void notifyDataChanged()
    {
        for (MainListViewStorageListener listener : listeners)
        {
            listener.onStorageDataChanged(this);
        }
    }

    private void fireStorageChanged()
    {
        for (MainListViewStorageListener listener : listeners)
        {
            listener.onStorageChanged(this);
        }
    }
}
