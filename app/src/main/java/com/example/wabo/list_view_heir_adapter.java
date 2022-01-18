package com.example.wabo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class list_view_heir_adapter extends ArrayAdapter {

    public Activity mContact;
    List<Heir> heirsList;

    public list_view_heir_adapter(Activity mContact, List<Heir> heirsList)
    {
        super(mContact,R.layout.activity_account,heirsList);
        this.mContact = mContact;
        this.heirsList = heirsList;


    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parents)
    {
        LayoutInflater inflater = mContact.getLayoutInflater();
        View heirView = inflater.inflate(R.layout.list_view_heir, null, true);


        TextView Heirin = heirView.findViewById(R.id.Heirin);
        TextView Heiric = heirView.findViewById(R.id.Heiric);

        Heir heir = heirsList.get(position);

        Heirin.setText(heir.getHeirsname());
        Heiric.setText(heir.getHeirsic());

        return heirView;

    }


}
