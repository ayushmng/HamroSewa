package com.example.ayush.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.ayush.myapplication.Fragmentation.FragmentActivity;
import com.example.ayush.myapplication.R;

public class NoInternetConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(NoInternetConnectionActivity.this, FragmentActivity.class);
//        startActivity(intent);
//        finish();
//    }

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
