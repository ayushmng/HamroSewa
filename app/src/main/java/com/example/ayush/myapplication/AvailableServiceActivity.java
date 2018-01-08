package com.example.ayush.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.ayush.myapplication.ServiceActivity.ServiceMenu.EXTRA_CREATOR;

public class AvailableServiceActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    ProgressBar progressbar;
    RequestQueue requestQueue;

//    String orgListUrl = "https://xelwel.com.np/hamrosewaapp/api/get_organization_list";
//    String fetchurl = "http://10.0.2.2/MyApplication/select.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_service);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Available Services");

        Intent intent = getIntent();

        String name = intent.getStringExtra(EXTRA_CREATOR);

        TextView textView = findViewById(R.id.Textview);

        textView.setText(name);


        progressbar = findViewById(R.id.progressBar);
        progressbar.setVisibility(View.VISIBLE);

        textView1 = findViewById(R.id.Number1);
        textView2 = findViewById(R.id.ServiceName1);
        textView3 = findViewById(R.id.Amount1);

        requestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }


    public void jsonParse() {

//        String fetchurl = getIntent().getStringExtra("fetch_url");
       final String fetchurl = ("https://xelwel.com.np/hamrosewaapp/api/get_organization_list");

        final StringRequest request = new StringRequest(Request.Method.POST, fetchurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("org_list");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject patient = jsonArray.getJSONObject(i);

                        String Id = patient.getString("orga_orgid");
                        textView1.append(Id + "." + "\n\n");

                        String Name = patient.getString("orga_organame");
                        textView2.append(Name + " " + "\n\n");

                        progressbar.setVisibility(View.GONE);

                    }
//                        String Amount = patient.getString("orga_organame");
//                        textView3.append(Amount+" "+"\n\n");

//                        textView.append(firstName+","+String.valueOf(age)+","+mail+"\n\n");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", "123456789");
                return params;            }
        };

        requestQueue.add(request);
    }

    //--------------------------------- For Back Button To Go On Back Process-----------------------------------------//
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
