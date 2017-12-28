package com.example.ayush.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TestinActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private  TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testin);

        txtview = findViewById(R.id.showdata);
        Button buttonParse = findViewById(R.id.showbtn);

        requestQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }
    public void jsonParse(){

        final String fetchurl = "http://xelwel.com.np/hamrosewaapp/api/get_organization_list";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, fetchurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("org_list");

                    for (int i=0; i < jsonArray.length(); i++){

                        JSONObject patient = jsonArray.getJSONObject(i);

                        String firstName = patient.getString("orga_organame");
                        txtview.append(firstName+","+"\n\n");
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
        });

        requestQueue.add(request);
    }
}
