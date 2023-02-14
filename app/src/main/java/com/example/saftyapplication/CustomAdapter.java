package com.example.saftyapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<ContactsModel> {
    Context context;
    List<ContactsModel> contacts;

    public CustomAdapter(@NonNull Context context, List<ContactsModel> contacts) {
        super(context, 0, contacts);
        this.context = context;
        this.contacts = contacts;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // creating a database helper object
        // to handle the database manipulations
        DbHelper db = new DbHelper(context);
        ContactsModel c = getItem(position);
        // check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);

        }
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(com.google.android.material.R.id.linear);
        // lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);

        //populate the data in to the template view using the data object
        tvName.setText(c.getName());
        tvPhone.setText(c.getPhoneNo());

        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // generate an MaterialAlertDialog Box
                new MaterialAlertDialogBuilder(context)
                        .setTitle("Remove Contact")
                        .setMessage("Are you sure want to remove this contact?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                //delete the specified contact from the database
                                db.deleteContact(c);
                                // remove the item from the list
                                contacts.remove(c);
                                //nofify the listview that dataset has been removed
                                notifyDataSetChanged();
                                Toast.makeText(context, "Contact removed", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }


                        })
                        .show();
                return false;
            }
        });
        return convertView;
    }
    //this method will update the ListView
    public void  refresh(List<ContactsModel> list){
        contacts.clear();
        contacts.addAll(list);
        notifyDataSetChanged();
    }
}



