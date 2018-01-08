package com.example.ayush.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestinActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private TextView txtview;


    final String fetchurl = "http://xelwel.com.np/hamrosewaapp/api/get_organization_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testin);

        txtview = findViewById(R.id.Textview);
//        imageView = findViewById(R.id.Hospital1);

//        ImageRequest imageRequest = new ImageRequest(imageurl, new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//
//                ImageView imageView = (ImageView) findViewById(R.id.Hospital1);
//                imageView.setImageBitmap(response);
//            }
//        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(TestinActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                error.printStackTrace();
//            }
//        });
//        //add request to queue
//        VolleySingleton.getInstance(TestinActivity.this).addToRequestQueue(imageRequest);

        requestQueue = Volley.newRequestQueue(this);

        jsonParse();

    }

    public void jsonParse() {


        final StringRequest request = new StringRequest(Request.Method.POST, fetchurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("org_list");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject obj = jsonArray.getJSONObject(i);

                        String Hname = obj.getString("orga_organame");
                        txtview.append(Hname + " "+"\n\n");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", "123456789");
                return params;
            }
        };

        requestQueue.add(request);
    }

    //--------------------------------- For Back Button To Go On Back Process-----------------------------------------//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // API 5+ solution
//                onBackPressed();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
