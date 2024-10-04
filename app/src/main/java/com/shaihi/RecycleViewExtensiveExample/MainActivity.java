package com.shaihi.RecycleViewExtensiveExample;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterInterface{

    int[] images = {R.drawable.baseline_access_alarms_24, R.drawable.baseline_accessible_forward_24,
            R.drawable.baseline_30fps_24, R.drawable.baseline_access_time_filled_24, R.drawable.baseline_5k_24, R.drawable.baseline_1x_mobiledata_24,
            R.drawable.baseline_10k_24, R.drawable.baseline_60fps_24, R.drawable.baseline_add_circle_outline_24, R.drawable.a1,
            R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5,
            R.drawable.ic_android_black_24dp, R.drawable.baseline_airplay_24, R.drawable.baseline_airport_shuttle_24, R.drawable.a6,
            R.drawable.a7, R.drawable.a8};


    ArrayList<CardModel> models = new ArrayList<>();

    private ActivityResultLauncher<Intent> userInputActivityLauncher;

    CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpModel();
        adapter = new CardAdapter(this, models, this);
        RecyclerView recycleView = findViewById(R.id.rcView);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        setUpInputActivityLaunch();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Example usage: Launching an activity for result
                launchGetInputActivity();
            }
        });
    }

    private void launchGetInputActivity() {
        Intent intent = new Intent(this, NewItemInputActivity.class);
        userInputActivityLauncher.launch(intent);
    }

    private void setUpInputActivityLaunch(){
        Intent intent = new Intent(MainActivity.this, NewItemInputActivity.class);
        userInputActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Handle the result here
                        if (result.getResultCode() == RESULT_OK) {
                            // The result is OK
                            Intent data = result.getData();
                            String text = data.getStringExtra(getString(R.string.new_item_text));
                            int drawableImage = data.getIntExtra(getString(R.string.new_icon_picked),0);
                            models.add(new CardModel(text, drawableImage));
                            adapter.notifyItemInserted(models.size());
                            RecyclerView recycleView = findViewById(R.id.rcView);
                            recycleView.post(new Runnable() {
                                @Override
                                public void run() {
                                    recycleView.smoothScrollToPosition(models.size());
                                }
                            });
                        }
                    }
                }
        );
    }

    private void setUpModel() {
        String[] texts = getResources().getStringArray(R.array.whatever);
        for(int i=0;i<texts.length;i++){
            models.add(new CardModel(texts[i],images[i]));
        }
    }

    @Override
    public void onItemClickListener(int position) {
        Intent intent = new Intent(this, SecondaryActivity.class);
        intent.putExtra(getString(R.string.item_number), position);

        startActivity(intent);

    }
}