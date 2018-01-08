package com.example.ayush.myapplication.Fragmentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.ayush.myapplication.R;
import com.example.ayush.myapplication.ServiceActivity.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RadioActivitydeICU extends Fragment {

    TextView textView1, textView2;
    AQuery aQuery;
    ProgressBar progressbar;

    String fetchurl = "http://xelwel.com.np/hamrosewa/select.php";
//    String fetchurl = "http://10.0.2.2/MyApplication/select.php";
    //    String fetchurl = "http://172.16.16.33/MyApplication/select.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_icuradio, null);

        progressbar = view.findViewById(R.id.progressBar);
        progressbar.setVisibility(View.VISIBLE);

        aQuery = new AQuery(getContext()); // try using getActivity inside bracket.

        textView1 = view.findViewById(R.id.Totalbed);
        textView2 = view.findViewById(R.id.Vaccantbed);

//        jsonParse();

        return view;
    }


//    public void jsonParse() {
//
//        aQuery.ajax(fetchurl, JSONArray.class, new AjaxCallback<JSONArray>() {
//            @Override
//            public void callback(String url, JSONArray array, AjaxStatus status) {
//                super.callback(url, array, status);
//                Log.i("response", url + "response:" + array);
//
//                ArrayList<UserInfo> list = new ArrayList<>();
//
//                for (int i = 0; i < array.length(); i++) {
//
//                    try {
//                        JSONObject object = array.getJSONObject(i);
//                        UserInfo info = new UserInfo();
//                        info.total = object.getString("Total1");
//                        info.vaccant = object.getString("Vaccant1");
//                        list.add(info);
//
//                        textView1.append(info.total + " " + "\n\n");
//                        textView2.append(info.vaccant + " " + "\n\n");
//
//                        progressbar.setVisibility(View.GONE);
//
//                    } catch (JSONException je) {
//                        Toast.makeText(aQuery.getContext(), "Error Parsing Data!", Toast.LENGTH_LONG).show();
//                    } catch (Exception e) {
//                        Toast.makeText(aQuery.getContext(), "Something went wrong...", Toast.LENGTH_LONG).show();
//                    }
//
//                }
//                if (status.getCode() == 500) {
//                    Toast.makeText(aQuery.getContext(), "Server is busy, Try Again!", Toast.LENGTH_LONG).show();
//                } else if (status.getCode() == 404) {
//                    Toast.makeText(aQuery.getContext(), "Resource Not Found!", Toast.LENGTH_LONG).show();
//                } else if (status.getCode() == 200) {
//
//                } else {
//                    Toast.makeText(aQuery.getContext(), "Unexpected Error Occured !", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
}
