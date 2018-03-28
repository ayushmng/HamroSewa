package com.example.ayush.myapplication.Fragmentation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ayush.myapplication.R;

import java.util.List;

/**
 * Created by Ayush on 1/24/2018.
 */

public class RadioAdapter extends ArrayAdapter {

    private Context context;
    private boolean useList = true;

    public RadioAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    /**
     * Holder for the list items.
     */
    private class ViewHolder{
        TextView Text1, Text2, Text3;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        UserInfo3 item = (UserInfo3) getItem(position);
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            if(useList){
                viewToUse = mInflater.inflate(R.layout.item_layout2, null);
            }

            holder = new ViewHolder();
            holder.Text1 = (TextView)viewToUse.findViewById(R.id.Number1);
            holder.Text2 = (TextView)viewToUse.findViewById(R.id.ServiceName1);
            holder.Text3 = (TextView)viewToUse.findViewById(R.id.Amount1);

            viewToUse.setTag(holder);
        } else {

            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        holder.Text1.setText(item.getHospitalName());
        holder.Text2.setText(item.getTotalBed());
        holder.Text3.setText(item.getVaccantBed());

        return viewToUse;
    }
}