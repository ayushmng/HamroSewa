package com.example.ayush.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Ayush on 7/9/2017.
 */

public class UserListAdapter extends ArrayAdapter<UserInfo> {

    Context context; // To get information of call the class in any part of the program we actually use context class.

    public UserListAdapter(Context context, ArrayList<UserInfo> list) {
        super(context, 0, list);
        this.context = context; // "this" context lekhirakhnu naparos vanera use gareko.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_dropdown_item_1line, null);

        TextView textView1, textView2, textView3;
//        textView1 = view.findViewById(android.R.id.id);
//        textView2 = view.findViewById(android.R.id.);
        textView3 = view.findViewById(android.R.id.text1);

        final UserInfo info = getItem(position);
//        textView1.setText(info.getId());
//        textView2.setText(info.getName());


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JSONActivity.class);
                intent.putExtra("Id", info.id);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
