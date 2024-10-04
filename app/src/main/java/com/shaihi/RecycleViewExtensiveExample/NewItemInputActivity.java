package com.shaihi.RecycleViewExtensiveExample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewItemInputActivity extends AppCompatActivity  implements AdapterInterface{
    int[] images = {R.drawable.icon25,R.drawable.icon26,
            R.drawable.icon27,R.drawable.icon28,R.drawable.icon29,R.drawable.icon30,
            R.drawable.icon31,R.drawable.icon32,R.drawable.icon33,R.drawable.icon34,
            R.drawable.icon35,R.drawable.icon36,R.drawable.icon37,R.drawable.icon38,
            R.drawable.icon39,R.drawable.icon40,R.drawable.icon41,R.drawable.icon42,
            R.drawable.icon43,R.drawable.icon44};

    private GalleryAdapter adapter; // Declare the adapter
    ArrayList<GalleryModel> model = new ArrayList<>();
    int picked_position, previous_position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_item_input);
        setupModel();
        setupRecyclerView();
        setupButtons();
    }

    void setupModel(){
        for (int i = 0; i < images.length; i++) {
            model.add(new GalleryModel(images[i], GalleryModel.NOT_SELECTED));
        }
    }
    private void setupRecyclerView() {
        RecyclerView recycleView = findViewById(R.id.imagepicker_view);
        adapter = new GalleryAdapter(model, this, this); // Initialize the adapter
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new GridLayoutManager(this,3));
    }

    private void setupButtons() {
        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });

        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void save(View v) {
        Intent intent = new Intent();
        EditText et = findViewById(R.id.itemNameInput);
        intent.putExtra(getString(R.string.new_item_text), et.getText().toString());
        intent.putExtra(getString(R.string.new_icon_picked), images[picked_position]);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onItemClickListener(int position) {
        if (previous_position != -1) {
            model.get(previous_position).isSelected = GalleryModel.NOT_SELECTED;
            adapter.notifyItemChanged(previous_position);
        }
        model.get(position).isSelected = GalleryModel.SELECTED;
        picked_position = position;
        previous_position = position;
        adapter.notifyItemChanged(position);
    }
}
