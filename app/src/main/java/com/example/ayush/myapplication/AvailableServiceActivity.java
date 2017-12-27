package com.example.ayush.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AvailableServiceActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    ProgressBar progressbar;
    RequestQueue requestQueue;

//    String fetchurl = "http://xelwel.com.np/hamrosewa/select.php";
//    String fetchurl = "http://10.0.2.2/MyApplication/select.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_service);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Available Services");

        progressbar = findViewById(R.id.progressBar);
        progressbar.setVisibility(View.VISIBLE);

        textView1 = findViewById(R.id.Number1);
        textView2 = findViewById(R.id.ServiceName1);
        textView3 = findViewById(R.id.Amount1);

        requestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }


    public void jsonParse() {

        String fetchurl = getIntent().getStringExtra("fetch_url");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, fetchurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("org_list");

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
        }){     // Used for Body Part of Requesting...
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            public byte[] getBody() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("api_key","123456789" );
                return super.getBody();
            }

            //            @Override
//            public Map<String, String> getBody() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("api_key","123456789" );
//                params.put("Content-Type", "application/x-www-form-urlencoded");
//                return params;
//            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("api_key","123456789" );
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
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
