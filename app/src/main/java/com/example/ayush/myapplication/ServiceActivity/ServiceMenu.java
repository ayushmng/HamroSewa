package com.example.ayush.myapplication.ServiceActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ayush.myapplication.Activities.BedMenuActivity;
import com.example.ayush.myapplication.Activities.MainActivity;
import com.example.ayush.myapplication.Activities.NoInternetConnectionActivity;
import com.example.ayush.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceMenu extends AppCompatActivity implements ExampleAdapter.OnItemClickListener, SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String EXTRA_CREATOR = "creatorName"; // tala ko data ma vayeko url ko object banako ko obj name haleko ho
    public static final String EXTRA_URL = "imageUrl";

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private RequestQueue mRequestQueue;
    private GridLayoutManager gridLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

//    private ArrayList<DepartInfo> mExampleList;

    private List<UserInfo> mExampleList = new ArrayList<>();
//    ProgressBar progressbar;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Services");

        if (!isConnected(ServiceMenu.this)) {
            Intent intent = new Intent(ServiceMenu.this, NoInternetConnectionActivity.class);
            startActivity(intent);
            finish(); // Yo finish garepaxi yo activity kill vayera NoInternetConnection page khulx n it is done so that when we enter in that page and when we press back this ServiceMenu Activity gets load again.
        }

//        progressbar = findViewById(R.id.progressBar);
//        progressbar.setVisibility(View.VISIBLE);

        progressDialog = new ProgressDialog(ServiceMenu.this,R.style.ProgressDialogStyle);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Please Wait"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
//        progressDialog.setCancelable(false);// Lets to cancel or give no acces to click outer-surface

        gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);

//  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));  // It changes the dataitem into list view than that of grid view when we remove gridview 2lines from above ....


//        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

//--------------------------------- For No Internet Connection show page -----------------------------------------------//

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return true;
        } else
            return false;
    }

// ------------------------------------------ No Internet Connection Ends here --------------------------------------------//

    public void jsonParse() {

        final String url = ("https://xelwel.com.np/hamrosewaapp/api/get_organization_list");

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.i("response", url + "response:" + response);

                        try {

                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("org_list");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                final JSONObject hit = jsonArray.getJSONObject(i);

                                Collections.sort(mExampleList, new Comparator<UserInfo>() {

                                            @Override
                                            public int compare(final UserInfo userInfo1, final UserInfo userInfo2) {

//                                                int order=-1;
//
//                                                if(userInfo1.getCreator().compareTo(userInfo2.getCreator()) == 0){
//                                                    return 0;
//                                                }else if(userInfo1.getCreator().compareTo(userInfo2.getCreator()) < 0){
//                                                    return order;
//                                                }else
//                                                    return (-1*order);

                                                return userInfo1.getCreator().compareTo(userInfo2.getCreator());
//                                                    String creatorName = userInfo1.getCreator(); // yo string paxi ko obj ko name mathi extra ma haleko xa
//                                                    String imageUrl = userInfo2.getImageUrl();
//                                                    return creatorName.compareTo(imageUrl);
                                            }
                                        });

                                String creatorName = hit.getString("orga_organame"); // yo string paxi ko obj ko name mathi extra ma haleko xa
                                String imageUrl = hit.getString("orga_image");

                                mExampleList.add(new UserInfo(imageUrl, creatorName));
                            }

                            mExampleAdapter = new ExampleAdapter(ServiceMenu.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(ServiceMenu.this);

//                            progressbar.setVisibility(View.GONE);
                            progressDialog.dismiss();

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
                Toast.makeText(ServiceMenu.this, "No items available", Toast.LENGTH_SHORT).show();
//                Toast.makeText(ServiceMenu.this,error.getMessage() ,Toast.LENGTH_LONG).show();
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
    public void onItemClick(UserInfo userInfo) {

        Intent detailIntent = new Intent(this, AvailableServiceActivity.class);

//        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
//        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getCreator());

        detailIntent.putExtra("userInfo", userInfo);

        startActivity(detailIntent);
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
    public boolean onOptionsItemSelected(MenuItem item) {  // For Top back arrow button
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//---------------------------------------------- Searching System ----------------------------------------------------------//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

//---------------------------------------  For Refreshing through menu item  ----------------------------------------------//
        final MenuItem refresh = menu.findItem(R.id.menu_refresh);

        refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if (mExampleList.isEmpty()) {
                    jsonParse();
                }
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
                return true;
            }
        });

//--------------------------------------------  Refresh Menu ends here  --------------------------------------------------//

        final MenuItem searchItem = menu.findItem(R.id.item_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {

                        //--------  Do something when collapsed  -----------//

                        mExampleAdapter.setFilter(mExampleList);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) { // yo vaneko search icon ma click garda true value return garne..

                        //--------  Do something when expanded  --------------//

                        return true; // Return true to expand action view
                    }
                });

        return true; // Returning true to onCreate Option Menu
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<UserInfo> filteredModelList = filter(mExampleList, newText);

        mExampleAdapter.setFilter(filteredModelList); // This setFilter is called from DepartAdapter Class
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<UserInfo> filter(List<UserInfo> models, String query) {
        query = query.toLowerCase();

        final List<UserInfo> filteredModelList = new ArrayList<>();
        for (UserInfo model : models) {
            final String text = model.getCreator().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onRefresh() {
        if (mExampleList.isEmpty()) {
            jsonParse();
        }
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        },3000);
    }
}
//---------------------------  Search stops from here  ----------------------------------------//
