package com.example.ayush.myapplication;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.util.Constants;
import com.example.ayush.myapplication.R;

import static java.lang.System.in;

public class ServiceMenuActivity extends AppCompatActivity {

    public static final String Url = "http://192.168.0.3/hamrosewaapp/api/get_organization_list/2";
    public static final String Url1 = "http://xelwel.com.np/";

    LinearLayout linearLayout_A, linearLayout_B, linearLayout_C, linearLayout_D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Services");

        linearLayout_A = findViewById(R.id.linearlayout1);
        linearLayout_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceMenuActivity.this, AvailableServiceActivity.class);
                intent.putExtra("fetch_url", "https://xelwel.com.np/hamrosewaapp/api/get_organization_list/1");
                startActivity(intent);
            }
        });

        linearLayout_B = findViewById(R.id.linearlayout2);
        linearLayout_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceMenuActivity.this, AvailableServiceActivity.class);
                intent.putExtra("fetch_url", "https://xelwel.com.np/hamrosewaapp/api/get_organization_list/2");
//                intent.putExtra("fetch_url", "http://192.168.0.3/hamrosewaapp/api/get_organization_list/2");
                startActivity(intent);
            }
        });

        linearLayout_C = findViewById(R.id.linearlayout3);
        linearLayout_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceMenuActivity.this, AvailableServiceActivity.class);
                intent.putExtra("fetch_url", "http://xelwel.com.np/hamrosewa/select.php");
                startActivity(intent);
            }
        });

        linearLayout_D = findViewById(R.id.linearlayout4);
        linearLayout_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceMenuActivity.this, AvailableServiceActivity.class);
                intent.putExtra("fetch_url", "http://xelwel.com.np/hamrosewa/select.php");
                startActivity(intent);
            }
        });

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
