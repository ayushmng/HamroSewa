package com.example.ayush.myapplication.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayush.myapplication.R;
import com.squareup.picasso.Picasso;

import static com.example.ayush.myapplication.Fragmentation.FragmentRadioActivity.EXTRA_ADDRESS;
import static com.example.ayush.myapplication.Fragmentation.FragmentRadioActivity.EXTRA_CONTACT;
import static com.example.ayush.myapplication.Fragmentation.FragmentRadioActivity.EXTRA_NAME;
import static com.example.ayush.myapplication.Fragmentation.FragmentRadioActivity.IMAGE_URL;

public class HospitalDetails extends AppCompatActivity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);


//-----------------------------  Calling Intent starts from here  ----------------------------------------------------------//

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(IMAGE_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        final String cntc = intent.getStringExtra(EXTRA_CONTACT);
        String addr = intent.getStringExtra(EXTRA_ADDRESS);

        ImageView imageView = findViewById(R.id.HospitalImage);
        TextView textView = findViewById(R.id.HospitalHead);
        TextView textView2 = findViewById(R.id.Phoneno_);
        TextView textView3 = findViewById(R.id.Address);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textView.setText(name);
        textView2.setText(cntc);
        textView3.setText(addr);


//-----------------------------  Upto here is getting intent from another page  ---------------------------------------------//

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Yo case ma toolbar lae yaha define gareko xa because mathi getSupportActionBar garda yeslae get garne vo but yaha setActionBar vaner define gareko xa. So mathi get garda pahila yaha tala ko set function lae call garna khojda error aux.

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phone = "tel:"+cntc;
                intent.setData(Uri.parse(phone));
                startActivity(intent);
                Snackbar.make(view, "Call in progress...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button = findViewById(R.id.mapbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (ActivityCompat.checkSelfPermission(HospitalDetails.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HospitalDetails.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    ActivityCompat.requestPermissions(HospitalDetails.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
//                    return;
//                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri gmmIntentUri = Uri.parse("geo:<27.686453>,<85.338816>?q=<27.686453>,<85.338816>(Civil Service Hospital of Nepal)");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                }, 1000);
            }
        });
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
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle Starts", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle Resumes", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle Pauses", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle Stops", "onStop");
    }

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
