package com.example.ayush.myapplication.Fragmentation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.ayush.myapplication.Activities.BedMenuActivity;
import com.example.ayush.myapplication.Activities.HospitalDetails;
import com.example.ayush.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class FragmentRadioActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
public class FragmentRadioActivity extends Fragment {

    public static final String EXTRA_NAME = "Name";
    public static final String IMAGE_URL = "ImageUrl";
    public static final String EXTRA_CONTACT = "Phone";
    public static final String EXTRA_ADDRESS = "Address";

    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    private List<UserInfo3> list = new ArrayList<UserInfo3>();
    private ListView listView;
    private RadioAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_icuradio, null);

        listView = view.findViewById(R.id.list_view);

//        progressbar = view.findViewById(R.id.progressBar);
//        progressbar.setVisibility(View.VISIBLE);

        progressDialog = new ProgressDialog(getActivity(),R.style.ProgressDialogStyle);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Please Wait"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
//        progressDialog.setCancelable(false);


//        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW, Color.GREEN);

        requestQueue = Volley.newRequestQueue(getContext());

        jsonParse();

        return view;
    }


    public void jsonParse() {

        final String fetchurl = ("https://xelwel.com.np/hamrosewaapp/api/get_bed_info_by_orgid/1");

        final StringRequest request = new StringRequest(Request.Method.POST, fetchurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("bed_service");

//                    UserInfo info = new UserInfo();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject patient = jsonArray.getJSONObject(i);

//                        info.setId(patient.getString("orga_orgid"));
//                        info.setName(patient.getString("orga_organame"));
//                        list.add(info);

                        String Hsname = patient.getString("orga_organame");
                        String Totbed = patient.getString("hobi_totalbed");
                        String Vacbed = patient.getString("hobi_vacantbed");
                        String ImageUrl = patient.getString("orga_image");
                        String Phone = patient.getString("orga_contactno");
                        String Address = patient.getString("orga_orgaddress1");

                        adapter = new RadioAdapter(getActivity(), list); // fragment ma '.this' accept nagarera getActivity use gareko instead of RadioActivityICU.this
                        list.add(new UserInfo3(Hsname, Totbed, Vacbed, ImageUrl, Phone, Address));
                        adapter.notifyDataSetChanged();

                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                UserInfo3 clickItem = list.get(i);

                                Intent intent = new Intent(getActivity(), HospitalDetails.class);
                                intent.putExtra(EXTRA_NAME, clickItem.getHospitalName());
                                intent.putExtra(IMAGE_URL, clickItem.getImage());
                                intent.putExtra(EXTRA_CONTACT, clickItem.getContact());
                                intent.putExtra(EXTRA_ADDRESS, clickItem.getAddress());

                                startActivity(intent);
                            }
                        });

                      //  progressbar.setVisibility(View.GONE);

                        progressDialog.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

              //  adapter.notifyDataSetChanged();
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

}
