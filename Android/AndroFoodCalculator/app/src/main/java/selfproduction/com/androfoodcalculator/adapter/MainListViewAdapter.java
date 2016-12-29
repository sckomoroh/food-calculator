package selfproduction.com.androfoodcalculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import selfproduction.com.androfoodcalculator.R;
import selfproduction.com.androfoodcalculator.model.FoodGroupItem;
import selfproduction.com.androfoodcalculator.storage.MainListViewStorage;
import selfproduction.com.androfoodcalculator.storage.MainListViewStorageListener;

/**
 * Created by anna on 28.12.16.
 */

public class MainListViewAdapter
        extends BaseExpandableListAdapter
        implements MainListViewStorageListener
{
    private MainListViewStorage storage;
    private Context context;

    public MainListViewAdapter(Context context)
    {
        this.context = context;
    }

    public void setStorage(MainListViewStorage storage)
    {
        this.storage = storage;
        storage.addListener(this);
    }

    @Override
    public int getGroupCount() {
        return storage.getGroupsCount();
    }

    @Override
    public int getChildrenCount(int i) {
        return 2;
    }

    @Override
    public Object getGroup(int i) {
        return storage.getGroup(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        FoodGroupItem groupItem = storage.getGroup(i);

        switch (i1)
        {
            case 0:
                return groupItem.getFoodDetailsItem().getPrice();
            case 1:
                return groupItem.getFoodDetailsItem().getCount();
        }

        return null;
    }

    @Override
    public long getGroupId(int i) {
        return i * 100;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i * 100 + i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_item_layout, null);
        }

        final FoodGroupItem groupItem = storage.getGroup(i);

        CheckBox nameCheckBox = (CheckBox) view.findViewById(R.id.group_item_name);
        TextView actualPriceTextView = (TextView)view.findViewById(R.id.group_item_actual_price);
        Button removeButton = (Button)view.findViewById(R.id.groud_delete_item_button);

        nameCheckBox.setText(groupItem.getName());
        actualPriceTextView.setText(groupItem.getActualPrice() + "");

        nameCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                groupItem.setInclude(b);
                storage.notifyDataChanged();
            }
        });

        final int groupIndex = i;
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storage.removeGroup(groupIndex);
                MainListViewAdapter.this.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.details_item_layout, null);
        }

        FoodGroupItem groupItem = storage.getGroup(i);

        TextView nameTextView = (TextView)view.findViewById(R.id.details_name);
        TextView valueTextView = (TextView)view.findViewById(R.id.details_value);
        switch (i1)
        {
            case 0:
                nameTextView.setText("Price");
                valueTextView.setText(groupItem.getFoodDetailsItem().getPrice() + "");
                break;
            case 1:
                nameTextView.setText("Count");
                valueTextView.setText(groupItem.getFoodDetailsItem().getCount() + "");
                break;
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public void onStorageChanged(MainListViewStorage storage) {
        this.notifyDataSetChanged();
    }

    @Override
    public void onStorageDataChanged(MainListViewStorage storage) {

    }
}
