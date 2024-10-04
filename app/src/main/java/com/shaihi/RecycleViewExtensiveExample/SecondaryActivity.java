package com.shaihi.RecycleViewExtensiveExample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_clicked);
        Intent intent = getIntent();
        Integer position = intent.getIntExtra(getString(R.string.item_number), -1);
        position++;
        TextView tv = findViewById(R.id.textView22);
        tv.setText("Item " + position.toString() + " clicked");
    }
}
