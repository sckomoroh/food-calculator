package selfproduction.com.androfoodcalculator.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import selfproduction.com.androfoodcalculator.R;
import selfproduction.com.androfoodcalculator.model.FoodDetailsItem;
import selfproduction.com.androfoodcalculator.model.FoodGroupItem;
import selfproduction.com.androfoodcalculator.storage.MainListViewStorage;

/**
 * Created by anna on 28.12.16.
 */

public class NewItemDialog extends DialogFragment {
    private TextView nameTextView;
    private TextView priceTextView;
    private TextView countTextView;
    private MainListViewStorage storage;

    public void setStorage(MainListViewStorage storage)
    {
        this.storage = storage;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_new_item, null);
        nameTextView = (TextView)view.findViewById(R.id.new_item_name);
        priceTextView = (TextView)view.findViewById(R.id.new_item_price);
        countTextView = (TextView)view.findViewById(R.id.new_item_count);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Add item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });



        final Dialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface d) {
                AlertDialog alertDialog = (AlertDialog)dialog;
                Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        try {
                            Double.parseDouble(priceTextView.getText().toString());
                        }
                        catch (NumberFormatException ex)
                        {
                            priceTextView.setError("Invalid number format");
                            return;
                        }

                        try {
                            Double.parseDouble(countTextView.getText().toString());
                        }
                        catch (NumberFormatException ex)
                        {
                            priceTextView.setError("Invalid number format");
                            return;
                        }

                        String name = nameTextView.getText().toString();
                        double price = Double.parseDouble(priceTextView.getText().toString());
                        double count = Double.parseDouble(countTextView.getText().toString());

                        FoodDetailsItem detailsItem = new FoodDetailsItem();
                        detailsItem.setPrice(price);
                        detailsItem.setCount(count);

                        FoodGroupItem groupItem = new FoodGroupItem(detailsItem);
                        groupItem.setName(name);
                        groupItem.setInclude(true);

                        storage.addGroup(groupItem);

                        dialog.dismiss();
                    }
                });
            }
        });

        return dialog;
    }
}
