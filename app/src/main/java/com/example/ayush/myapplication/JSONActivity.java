package com.example.ayush.myapplication;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONActivity extends AppCompatActivity {

    Context context;

    TextView textView1, textView2;
    GridView gridview;
    Button buttonParse;
    AQuery aQuery;

//    String fetchurl = "http://192.168.100.6/MyApplication/select.php";
    String fetchurl = "http://10.0.2.2/MyApplication/select.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        aQuery = new AQuery(this);

        textView1 = findViewById(R.id.id);
        textView2 = findViewById(R.id.id);

        gridview = findViewById(R.id.gridView);
        buttonParse = findViewById(R.id.button);

//        jsonParse(); yaha yo use garepaxi button press nagari data afae aux...

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }

    public void jsonParse() {

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
                        list.add(info);

                        textView1.append(info.id + "," + "\n\n");
                        textView2.append(info.servicename + "," + "\n\n");

                    } catch (JSONException je) {
                        Toast.makeText(aQuery.getContext(), "Error Parsing Data!!", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(aQuery.getContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
                    }
                }

                if (status.getCode() == 500) {
                    Toast.makeText(aQuery.getContext(), "Server is busy, Try Again!", Toast.LENGTH_LONG).show();
                } else if (status.getCode() == 404) {
                    Toast.makeText(aQuery.getContext(), "Resource Not Found!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(aQuery.getContext(), "Unexpected Error Occured !", Toast.LENGTH_LONG).show();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, R.id.id);
                gridview.setAdapter(adapter);
//                gridview.setAdapter(new UserListAdapter(JSONActivity.this,list));
            }
        });
    }
}
