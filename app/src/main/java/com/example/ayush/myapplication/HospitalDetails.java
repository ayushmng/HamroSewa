package com.example.ayush.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HospitalDetails extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Yo case ma toolbar lae yaha define gareko xa because mathi getSupportActionBar garda yeslae get garne vo but yaha setActionBar vaner define gareko xa. So mathi get garda pahila yaha tala ko set function lae call garna khojda error aux.

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phone = "tel:"+"9842420134";
                intent.setData(Uri.parse(phone));
                startActivity(intent);
//                Snackbar.make(view, "Call in progress...", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

//        button = findViewById(R.id.mapbutton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Uri gmmIntentUri = Uri.parse("geo:19.076,72.8777");
//                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                        mapIntent.setPackage("com.google.android.apps.maps");
//                        startActivity(mapIntent);
//                    }
//                }, 1000);
//            }
//        });
    }

//    public void process(View view) {
//
//        Intent intent = null;
//
//        if (view.getId() == R.id.mapbutton) {
//
//            intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("geo:19.076,72.8777"));
//            this.startActivity(intent);
//        }
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
