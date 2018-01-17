package com.example.ayush.myapplication.ServiceActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import java.util.Map;

public class ServiceMenu extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    public static final String EXTRA_CREATOR = "creatorName"; // tala ko data ma vayeko url ko object banako ko obj name haleko ho
    public static final String EXTRA_URL = "imageUrl";

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<UserInfo> mExampleList;
    private RequestQueue mRequestQueue;
    private GridLayoutManager gridLayoutManager;

    public SearchView searchView;

    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Services");

        progressbar = findViewById(R.id.progressBar);
        progressbar.setVisibility(View.VISIBLE);

        gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

    public void jsonParse() {

        final String url = ("https://xelwel.com.np/hamrosewaapp/api/get_organization_list");

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("response", url + "response:" +response);

                        try {

                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("org_list");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject hit = jsonArray.getJSONObject(i);

                                String creatorName = hit.getString("orga_organame"); // yo string paxi ko obj ko name mathi extra ma haleko xa
                                String imageUrl = hit.getString("orga_image");

                                mExampleList.add(new UserInfo(imageUrl, creatorName));
                            }

                            mExampleAdapter = new ExampleAdapter(ServiceMenu.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(ServiceMenu.this);

                            progressbar.setVisibility(View.GONE);

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
                Toast.makeText(ServiceMenu.this, "No items available", Toast.LENGTH_LONG).show();
                Toast.makeText(ServiceMenu.this, "Please check your internet connection and try again!", Toast.LENGTH_LONG).show();
//                Toast.makeText(MainActivity.this,error.getMessage() ,Toast.LENGTH_LONG).show();
            }

//                int  statusCode = error.networkResponse.statusCode;
//                NetworkResponse response = error.networkResponse;
//
//                if (response.getCode() == 500) {
//                    Toast.makeText(aQuery.getContext(), "Server is busy, Try Again!", Toast.LENGTH_LONG).show();
//                } else if (status.getCode() == 404) {
//                    Toast.makeText(aQuery.getContext(), "Resource Not Found!", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(aQuery.getContext(), "Unexpected Error Occured !", Toast.LENGTH_LONG).show();
//                }


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
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, AvailableServiceActivity.class);
        UserInfo clickedItem = mExampleList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getCreator());

        startActivity(detailIntent);
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

//------------------------------------------ Searching ---------------------------------------------------------------//

//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.item_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.item_search);
//        searchView = (SearchView) searchItem.getActionView();

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if (!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                searchItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return true;
    }
    private ArrayList<UserInfo> filter(ArrayList<UserInfo> p1, String query){

        query = query.toLowerCase();
        final ArrayList<UserInfo> filterModeList = new ArrayList<>();
        for (UserInfo model: p1){

            final String text = model.getCreator().toLowerCase();
            if (text.startsWith(query)){
                filterModeList.add(model);
            }
        }
        return filterModeList;
    }
}

