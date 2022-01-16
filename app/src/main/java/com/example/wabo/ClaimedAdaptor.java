package com.example.wabo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ClaimedAdaptor extends ArrayAdapter {

    public Activity mContext3;
    List<will> ViewWill_Creator_ListClaimed;
   // ImageView claimedwillC2;


    public ClaimedAdaptor(Activity mContext3, List<will> ViewWill_Creator_ListClaimed){
        super(mContext3,R.layout.activity_view_will_creator_list_claimed, ViewWill_Creator_ListClaimed);
        this.mContext3 = mContext3;
        this.ViewWill_Creator_ListClaimed = ViewWill_Creator_ListClaimed;

        //Image Verified
      //  claimedwillC2.setOnClickListener(new View.OnClickListener() {
       //     @Override
      //      public void onClick(View v) {
      //          Intent intent = new Intent(ViewWill_Creator_Claimed.this,ViewWill_Creator_Claimed2.class);
      //          startActivity(intent);
     //       }
     //   });
    }

    //override method dari arrayadapter (keno import class dulu) getviewpositionint
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext3.getLayoutInflater();
        View viewClaimedC  = inflater.inflate(R.layout.activity_view_will_creator_list_claimed,null,true); //null, true

        //TextView evPickupPoint = listStudentView.findViewById(R.id.evPickupPoint);
        TextView CCwilltitle = viewClaimedC.findViewById(R.id.CCwilltitle);
        TextView CCwilldescription = viewClaimedC.findViewById(R.id.CCwilldescription);

        will Will1 = ViewWill_Creator_ListClaimed.get(position);

        // evPickupPoint.setText(Ambulance1.getPickuptime());
        CCwilltitle.setText(Will1.getWillTitle());
        CCwilldescription.setText(Will1.getWillDescription());

        return viewClaimedC;
    }
}