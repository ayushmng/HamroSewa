package com.example.ayush.myapplication.Activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ayush.myapplication.Fragmentation.FragmentRadioActivity;
import com.example.ayush.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class BedMenuActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_bed_menu);

        setTitle("Available Beds");

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentRadioActivity()).commit();

        mRequestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

    public void jsonParse() {

        final String url = ("https://xelwel.com.np/hamrosewaapp/api/get_bed_info_department");

        final RelativeLayout viewProductLayout = findViewById(R.id.customOptionLL);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("response", url + "response:" + response);

                        try {

                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("department_list");

                            for(int j=0; j<1; j++){

                                RadioGroup rg = findViewById(R.id.radiogroup); // Yo radiogroup
                                rg.setOrientation(RadioGroup.HORIZONTAL);

                                for (int i = 0; i < jsonArray.length(); i++) {

//                                RadioGroup rg = new RadioGroup(MainActivity.this);
//                                rg.setOrientation(RadioGroup.VERTICAL);

                                    RadioButton rb = new RadioButton(BedMenuActivity.this);
//-------------------------------------------- To change colour of radio button --------------------------------------------//
                                    ColorStateList colorStateList = new ColorStateList(
                                            new int[][]{
                                                    new int[]{-android.R.attr.state_enabled}, //disabled
                                                    new int[]{android.R.attr.state_enabled} //enabled
                                            },
                                            new int[] {

                                                    Color.rgb(152,153,152) //disabled
                                                    , Color.rgb (36,76,112)//enabled

//                                                    Color.BLACK //disabled
//                                                    ,Color.BLUE //enabled
                                            }
                                    );
                                    rb.setButtonTintList(colorStateList);//set the color tint list
                                    rb.invalidate();
//------------------------------------------------ Tint Colour ends here ---------------------------------------------------//
                                    rb.setText(jsonArray.getJSONObject(i).getString("dept_depname"));
                                    rb.setPadding(20,5,20,5);
                                    rb.setTextSize(20);
                                    rg.addView(rb);

                                    if (i == 0)
                                        rb.setChecked(true);
                                    rb.setTag(jsonArray.getJSONObject(i).getString("dept_depname"));

                                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(RadioGroup group, int checkedId) {

                                            View radioButton = group.findViewById(checkedId);
                                            String variant_name = radioButton.getTag().toString();
                                            Toast.makeText(getApplicationContext(), variant_name + "", Toast.LENGTH_SHORT).show();
                                            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentRadioActivity()).commit();
                                        }
                                    });
                                }
                                viewProductLayout.addView(rg);
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
                Toast.makeText(BedMenuActivity.this, "No items available", Toast.LENGTH_LONG).show();
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
