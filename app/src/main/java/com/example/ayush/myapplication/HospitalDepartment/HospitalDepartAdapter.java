package com.example.ayush.myapplication.HospitalDepartment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.ayush.myapplication.R;
import com.example.ayush.myapplication.ServiceActivity.UserInfo2;

import java.util.List;

/**
 * Created by Ayush on 1/10/2018.
 */

public class HospitalDepartAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<UserInfo2> DataList;

    private RequestQueue queue;

    public HospitalDepartAdapter(Activity activity, List<UserInfo2> dataitem) {

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
