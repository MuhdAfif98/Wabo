package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class VerifyAdaptor extends ArrayAdapter {

    public Activity mContext2;
    List<Will> ViewWill_Creator_ListVerify;


    public VerifyAdaptor(Activity mContext2, List<Will> ViewWill_Creator_ListVerify){
        super(mContext2,R.layout.activity_view_will_creator_list_verify, ViewWill_Creator_ListVerify);
        this.mContext2 = mContext2;
        this.ViewWill_Creator_ListVerify = ViewWill_Creator_ListVerify;
    }

    //override method dari arrayadapter (keno import class dulu) getviewpositionint
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext2.getLayoutInflater();
        View viewVerifyC  = inflater.inflate(R.layout.activity_view_will_creator_list_verify,null,true); //null, true

        //TextView evPickupPoint = listStudentView.findViewById(R.id.evPickupPoint);
        TextView CVwilltitle = viewVerifyC.findViewById(R.id.CVwilltitle);
        TextView CVwilldescription = viewVerifyC.findViewById(R.id.CVwilldescription);

        Will Will1 = ViewWill_Creator_ListVerify.get(position);

        // evPickupPoint.setText(Ambulance1.getPickuptime());
        CVwilltitle.setText(Will1.getwilltitle());
        CVwilldescription.setText(Will1.getwilldescription());

        return viewVerifyC;
    }
}
