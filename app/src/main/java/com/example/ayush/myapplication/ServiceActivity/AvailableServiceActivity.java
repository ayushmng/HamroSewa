package com.example.ayush.myapplication.ServiceActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ayush.myapplication.ServiceActivity.ServiceMenu.EXTRA_CREATOR;

public class AvailableServiceActivity extends AppCompatActivity {

    public static final String TAG_ID = "orga_orgid";
    public static final String TAG_NAME = "orga_organame";

    TextView textView1, textView2, textView3;
    ProgressBar progressbar;
    RequestQueue requestQueue;

//    ListView list;
//    public CustomGridAdapter adapter;
//    GridView grid;

//    String orgListUrl = "https://xelwel.com.np/hamrosewaapp/api/get_organization_list";
//    String fetchurl = "http://10.0.2.2/MyApplication/select.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_service);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Available Services");

// ------------------------------------------  For searching item ---------------------------------------------------------//

//        list = findViewById(R.id.list_item);
//        adapter = new CustomGridAdapter(this);
//        list.setAdapter(adapter);

//        list.setTextFilterEnabled(true);
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String text = listView.getItemAtPosition(position).toString();
//                Toast.makeText(AvailableServiceActivity.this,"" +text, Toast.LENGTH_LONG);
//            }
//        });
// ----------------------------------------  Searching view ends here ----------------------------------------------------//

        Intent intent = getIntent();

        String name = intent.getStringExtra(EXTRA_CREATOR);

        TextView textView = findViewById(R.id.Textview);

        textView.setText(name);


        progressbar = findViewById(R.id.progressBar);
        progressbar.setVisibility(View.VISIBLE);

        textView1 = findViewById(R.id.Number1);
        textView2 = findViewById(R.id.ServiceName1);
        textView3 = findViewById(R.id.Amount1);

//        grid = findViewById(R.id.grid_view);

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


//                        UserInfo adapter = new UserInfo(getActivity(), Id, Name);
//                        grid.setAdapter(new UserInfo(this));

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

}