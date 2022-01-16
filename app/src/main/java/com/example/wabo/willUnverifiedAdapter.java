package com.example.wabo;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class willUnverifiedAdapter extends ArrayAdapter {

    public Activity mContext1;
    List<Will> willList;
    // ImageView claimedwillC2;


    public willUnverifiedAdapter(Activity mContext1, List<Will> willList){
        super(mContext1,R.layout.activity_view_will_attorney_unverified, willList);
        this.mContext1 = mContext1;
        this.willList = willList;
    }

    //override method dari arrayadapter (keno import class dulu) getviewpositionint
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext1.getLayoutInflater();
        View viewClaimedC  = inflater.inflate(R.layout.view_will_unverified,null,true); //null, true

        //TextView evPickupPoint = listStudentView.findViewById(R.id.evPickupPoint);
        TextView willTitle = viewClaimedC.findViewById(R.id.idTVWillTitleUnverified);
        TextView willPenerima = viewClaimedC.findViewById(R.id.idTVWillPenerimaUnverified);

        Will will = willList.get(position);

        // evPickupPoint.setText(Ambulance1.getPickuptime());
        willTitle.setText(will.getWillTitle());
        willPenerima.setText(will.getWillPenerima());

        return viewClaimedC;
    }
}
