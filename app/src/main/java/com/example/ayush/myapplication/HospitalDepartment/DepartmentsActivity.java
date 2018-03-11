
package com.example.ayush.myapplication.HospitalDepartment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ayush.myapplication.R;
import com.example.ayush.myapplication.ServiceActivity.UserInfo2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DepartmentsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    TextView textView1, textView2, textView3;
    ProgressBar progressbar;
    RequestQueue requestQueue;

    private List<UserInfo2> list = new ArrayList<UserInfo2>();
    private ListView listView;
    private HospitalDepartAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

  //  @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Available Services");


//----------------------  For making top Hospital Name dynamic ---------------------//

        DepartInfo userInfo = (DepartInfo) getIntent().getSerializableExtra("userInfo");

//        String name = intent.getStringExtra(EXTRA_CREATOR);

        TextView textView = findViewById(R.id.Textview);
        textView.setText(userInfo.getCreator());

//------------------------------  Ends here -------------------------------------------//

        listView = findViewById(R.id.list_view);

        progressbar = findViewById(R.id.progressBar);
        progressbar.setVisibility(View.VISIBLE);

        textView1 = findViewById(R.id.Number1);
        textView2 = findViewById(R.id.ServiceName1);
        textView3 = findViewById(R.id.Amount1);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW, Color.GREEN);

        requestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

    public void jsonParse() {

        //        String fetchurl = getIntent().getStringExtra("fetch_url");

        final String fetchurl = ("https://xelwel.com.np/hamrosewaapp/api/get_bed_info_department");

        final StringRequest request = new StringRequest(Request.Method.POST, fetchurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("department_list");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject patient = jsonArray.getJSONObject(i);

//                       UserInfo2 info = new UserInfo2();

//                        info.setId(patient.getString("orga_orgid"));
//                        info.setName(patient.getString("orga_organame"));
//                        list.add(info);

                        String Id = patient.getString("dept_depid");
                        String Name = patient.getString("dept_depname");
                        // String Amount = patient.getString("");
                        list.add(new UserInfo2(Id, Name));

                        adapter = new HospitalDepartAdapter(DepartmentsActivity.this, list);
                        listView.setAdapter(adapter);

//                        textView1.append(Id + "." + "\n\n");
//                        textView2.append(Name + " " + "\n\n");

                        progressbar.setVisibility(View.GONE);
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

    //--------------------------------- For Back Button To Go On Back Process -----------------------------------------//
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
    }//--------------------------------- Back Button Process Ends Here -----------------------------------------//

    @Override
    public void onRefresh() {
        if (list.isEmpty()) {
            jsonParse();
        }
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);

//                int min = 65;
//                int max = 50;
//
//                Random random = new Random();
        }
    },3000);
}
}