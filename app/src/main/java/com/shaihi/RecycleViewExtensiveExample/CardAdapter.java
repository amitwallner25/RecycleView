package com.shaihi.RecycleViewExtensiveExample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    Context context;

    AdapterInterface adapterInterface;

    ArrayList<CardModel> models;

    public CardAdapter(Context context,
                       ArrayList<CardModel> models,
                       AdapterInterface adapterInterface) {
        this.context = context;
        this.models = models;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout, viewGroup,false);
        return new MyViewHolder(view, adapterInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyViewHolder myViewHolder, int position) {
        myViewHolder.text.setText(models.get(position).text);
        myViewHolder.iv.setImageResource(models.get(position).image);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    static public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView iv;
        public MyViewHolder(@NonNull View itemView, AdapterInterface adapterInterface) {
            super(itemView);
            //this is like the OnCreate
            text = itemView.findViewById(R.id.textView2);
            iv = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterInterface != null){
                        adapterInterface.onItemClickListener(getAdapterPosition());
                    }
                }
            });
        }
    }
}
