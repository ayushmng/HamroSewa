package com.example.ayush.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NobelJSONActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    ProgressBar progressbar;
    AQuery aQuery;

//    String fetchurl = "http://192.168.0.107/MyApplication/select.php";
    //    String fetchurl = "http://172.16.16.33/MyApplication/select.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nobel_json);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Available Services");

        progressbar = findViewById(R.id.progressBar);
        progressbar.setVisibility(View.VISIBLE);

        aQuery = new AQuery(this);

        textView1 = findViewById(R.id.Number1);
        textView2 = findViewById(R.id.ServiceName1);
        textView3 = findViewById(R.id.Amount1);

        jsonParse();
    }

    public void jsonParse() {

        String fetchurl = getIntent().getStringExtra("fetch_url");

        aQuery.ajax(fetchurl, JSONArray.class, new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray array, AjaxStatus status) {
                super.callback(url, array, status);
                Log.i("response", url + "response:" + array);

                ArrayList<UserInfo> list = new ArrayList<>();

                for (int i = 0; i < array.length(); i++) {

                    try {
                        JSONObject object = array.getJSONObject(i);
                        UserInfo info = new UserInfo();
                        info.id = object.getString("Id");
                        info.servicename = object.getString("name");
                        info.amount = object.getString("amount");
                        list.add(info);

                        textView1.append(info.id + "." + "\n\n");
                        textView2.append(info.servicename + " " + "\n\n");
                        textView3.append(info.amount + " " + "\n\n");

                        progressbar.setVisibility(View.GONE);

                    } catch (JSONException je) {
                        Toast.makeText(aQuery.getContext(), "Error Parsing Data!", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(aQuery.getContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
                    }

                }
                if (status.getCode() == 500) {
                    Toast.makeText(aQuery.getContext(), "Server is busy, Try Again!", Toast.LENGTH_LONG).show();
                } else if (status.getCode() == 404) {
                    Toast.makeText(aQuery.getContext(), "Resource Not Found!", Toast.LENGTH_LONG).show();
                }else if (status.getCode() == 200) {

                } else {
                    Toast.makeText(aQuery.getContext(), "Unexpected Error Occured !", Toast.LENGTH_LONG).show();
                }
            }
        });

//        progressbar.setVisibility(View.GONE);

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

