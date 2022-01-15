package com.example.wabo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class willAdapter extends FirebaseRecyclerAdapter< will, willAdapter.willViewholder> {
    public willAdapter(@NonNull FirebaseRecyclerOptions<will> options){
        super(options);
    }

    protected void onBindViewHolder(@NonNull willViewholder holder, int position, @NonNull will model){
        holder.willTitle.setText(model.getWillTitle());
        holder.willPenerima.setText(model.getWillPenerima());
    }

    @NonNull
    @Override
    public willViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_will, parent, false);
        return  new willAdapter.willViewholder(view);
    }

    class willViewholder extends RecyclerView.ViewHolder {

        TextView willTitle, willPenerima;

        public willViewholder(@NonNull View itemView){
            super(itemView);

            willTitle = itemView.findViewById(R.id.idTVWillTitle);
            willPenerima = itemView.findViewById(R.id.idTVWillPenerima);
        }
    }
}
