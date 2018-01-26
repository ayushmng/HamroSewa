package com.example.ayush.myapplication.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
import com.example.ayush.myapplication.Fragmentation.RadioActivitydeICU;
import com.example.ayush.myapplication.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestinActivity extends AppCompatActivity {

    private List<View> mExampleList = new ArrayList<View>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testin);

        mRequestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

    public void jsonParse() {

        final String url = ("https://xelwel.com.np/hamrosewaapp/api/get_organization_list");

        final LinearLayout viewProductLayout = findViewById(R.id.customOptionLL);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("response", url + "response:" + response);

                        try {

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                            params.topMargin = 3;
                            params.bottomMargin = 3;

                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("org_list");

                            RadioGroup rg = new RadioGroup(TestinActivity.this);
                            rg.setOrientation(RadioGroup.VERTICAL);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                RadioButton rb = new RadioButton(TestinActivity.this);
//                                RadioButton rb = findViewById(R.id.radiobtn);
                                rg.addView(rb,params);

                                if (i == 0)

                                    rb.setChecked(true);
                                rb.setLayoutParams(params);
                                    rb.setTag(jsonArray.getJSONObject(i).getString("orga_organame"));

                                String optionString = jsonArray.getJSONObject(i).getString("orga_organame");
                                rb.setText(optionString);
                                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                                        View radioButton = group.findViewById(checkedId);
                                        String variant_name = radioButton.getTag().toString();
                                        Toast.makeText(getApplicationContext(), variant_name + "", Toast.LENGTH_LONG).show();
                                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new RadioActivitydeICU()).commit();
                                    }
                                });

                                viewProductLayout.addView(rg,params);
                                mExampleList.add(rg);

//                                JSONObject hit = jsonArray.getJSONObject(i);
//                                String creatorName = hit.getString("dept_depname"); // yo string paxi ko obj ko name mathi extra ma haleko xa
//                                mExampleList.add(new UserInfo(creatorName));
                            }

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error Parsing Data", Toast.LENGTH_LONG);
                            e.printStackTrace();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG);
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(TestinActivity.this, "No items available", Toast.LENGTH_LONG).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("api_key", "123456789");
                return params;
            }
        };

        mRequestQueue.add(request);
    }
}

