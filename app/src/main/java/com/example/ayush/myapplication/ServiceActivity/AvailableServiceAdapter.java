package com.example.ayush.myapplication.ServiceActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.Dataset;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ayush.myapplication.MainActivity;
import com.example.ayush.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayush on 1/10/2018.
 */

public class AvailableServiceAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<UserInfo2> DataList;

    private RequestQueue queue;

    public AvailableServiceAdapter(Activity activity, List<UserInfo2> dataitem) {

        this.activity = activity;
        this.DataList = dataitem;
    }

    @Override
    public int getCount() {
        return DataList.size();
    }

    @Override
    public Object getItem(int location) {
        return DataList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_layout, null);

        TextView id = convertView.findViewById(R.id.Number1);
        TextView name = convertView.findViewById(R.id.ServiceName1);

        UserInfo2 m = DataList.get(position);

//        id.setText(m.getId());
//        name.setText(m.getName());

        id.setText(String.valueOf(m.getId()));
        name.setText(String.valueOf(m.getName()));

        return convertView;
    }
}
