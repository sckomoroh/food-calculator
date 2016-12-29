package selfproduction.com.androfoodcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import selfproduction.com.androfoodcalculator.adapter.MainListViewAdapter;
import selfproduction.com.androfoodcalculator.dialogs.NewItemDialog;
import selfproduction.com.androfoodcalculator.model.FoodDetailsItem;
import selfproduction.com.androfoodcalculator.model.FoodGroupItem;
import selfproduction.com.androfoodcalculator.storage.MainListViewStorage;
import selfproduction.com.androfoodcalculator.storage.MainListViewStorageListener;

public class MainActivity
        extends AppCompatActivity
        implements MainListViewStorageListener {

    private MainListViewAdapter adapter;
    private MainListViewStorage storage;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_new_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NewItemDialog dialog = new NewItemDialog();
                dialog.setStorage(storage);
                dialog.show(getFragmentManager(), null);
            }
        });

        resultTextView = (TextView)findViewById(R.id.result_textView);

        storage = new MainListViewStorage();
        storage.addListener(this);

        adapter = new MainListViewAdapter(this);
        adapter.setStorage(storage);

        ExpandableListView listView = (ExpandableListView)findViewById(R.id.main_list_view);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            storage.clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStorageChanged(MainListViewStorage storage) {
        double totalPrice = 0;
        for (FoodGroupItem item : storage.getGroups())
        {
            if (item.isInclude())
            {
                totalPrice += item.getActualPrice();
            }
        }

        resultTextView.setText(totalPrice + "");
    }

    @Override
    public void onStorageDataChanged(MainListViewStorage storage) {
        double totalPrice = 0;
        for (FoodGroupItem item : storage.getGroups())
        {
            if (item.isInclude())
            {
                totalPrice += item.getActualPrice();
            }
        }

        resultTextView.setText(totalPrice + "");
    }
}
