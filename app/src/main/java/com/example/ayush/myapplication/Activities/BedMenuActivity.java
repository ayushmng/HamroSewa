package com.example.ayush.myapplication.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ayush.myapplication.Fragmentation.RadioActivitydeCCU;
import com.example.ayush.myapplication.Fragmentation.RadioActivitydeICU;
import com.example.ayush.myapplication.Fragmentation.RadioActivitydeNICU;
import com.example.ayush.myapplication.R;


public class BedMenuActivity extends AppCompatActivity {

    TextView textView;
    RadioButton radioButton1, radioButton2, radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_bed_menu);

        setTitle("Available Beds");

        radioButton1 = findViewById(R.id.icu);
        radioButton2 = findViewById(R.id.nicu);
        radioButton3 = findViewById(R.id.ccu);

        radioButton1.setOnClickListener(onClickListener);
        radioButton2.setOnClickListener(onClickListener);
        radioButton3.setOnClickListener(onClickListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new RadioActivitydeICU()).commit();
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.icu) {

                getSupportFragmentManager().beginTransaction().replace(R.id.container, new RadioActivitydeICU()).commit();

            } else if (view.getId() == R.id.nicu) {

                getSupportFragmentManager().beginTransaction().replace(R.id.container, new RadioActivitydeNICU()).commit();

            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new RadioActivitydeCCU()).commit();
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
