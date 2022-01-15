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

public class NotVerifyAdaptor extends ArrayAdapter {

    public Activity mContext;
    List<Will> ViewWill_Creator_ListNotVerify;


    public NotVerifyAdaptor(Activity mContext, List<Will> ViewWill_Creator_ListNotVerify){
        super(mContext,R.layout.activity_view_will_creator_list_not_verify, ViewWill_Creator_ListNotVerify);
        this.mContext = mContext;
        this.ViewWill_Creator_ListNotVerify = ViewWill_Creator_ListNotVerify;
    }

    //override method dari arrayadapter (keno import class dulu) getviewpositionint
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View viewNotVerifyC  = inflater.inflate(R.layout.activity_view_will_creator_list_not_verify,null,true); //null, true

        //TextView evPickupPoint = listStudentView.findViewById(R.id.evPickupPoint);
        TextView CUwilltitle = viewNotVerifyC.findViewById(R.id.CUwilltitle);
        TextView CUwilldescription = viewNotVerifyC.findViewById(R.id.CUwilldescription);

        Will Will1 = ViewWill_Creator_ListNotVerify.get(position);

       // evPickupPoint.setText(Ambulance1.getPickuptime());
        CUwilltitle.setText(Will1.getwilltitle());
        CUwilldescription.setText(Will1.getwilldescription());

        return viewNotVerifyC;
    }
}
