package com.shaihi.RecycleViewExtensiveExample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyImageViewHolder> {
    ArrayList<GalleryModel> model;
    Context context;

    AdapterInterface adapterInterface;

    public GalleryAdapter(ArrayList<GalleryModel> model, Context context , AdapterInterface adapterInterface) {
        this.model = model;
        this.context = context;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public GalleryAdapter.MyImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_picker_row, viewGroup,false);
        return new GalleryAdapter.MyImageViewHolder(view, adapterInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.MyImageViewHolder myImageViewHolder, int position) {
        myImageViewHolder.imagePreview.setImageResource(model.get(position).image);
        if (model.get(position).isSelected == GalleryModel.SELECTED) {
            myImageViewHolder.imagePreview.setAlpha(0.5f);
        } else {
            myImageViewHolder.imagePreview.setAlpha(1.0f);
        }
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    static public class MyImageViewHolder extends RecyclerView.ViewHolder {
    ImageView imagePreview;
    public MyImageViewHolder(@NonNull View itemView, AdapterInterface adapterInterface) {
        super(itemView);
        imagePreview = itemView.findViewById(R.id.pickImage);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterface.onItemClickListener(getAdapterPosition());
            }
        });
    }
}
}
