package com.example.ayush.myapplication.ServiceActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayush.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayush on 12/31/2017.
 */

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {

    private Context mcontext;
    private List<UserInfo> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(UserInfo userInfo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }

    public ExampleAdapter(Context context, List<UserInfo> list) {

        this.mcontext = context;
        this.mExampleList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mcontext).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        UserInfo info = mExampleList.get(position);

        String image = info.getImageUrl();
        String hsname = info.getCreator();

        holder.textView.setText(hsname);
        Picasso.with(mcontext).load(image).fit().centerInside().into(holder.imageView);
//        Glide.with(mcontext).load(image).into(holder.imageView);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo userInfo = mExampleList.get(position);

                mListener.onItemClick(userInfo);

            }
        });


    }

    @Override
    public int getItemCount() {

        return mExampleList.size();
    }

    void setFilter(List<UserInfo> listitem) {
        mExampleList = new ArrayList<>();
        mExampleList.addAll(listitem);
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final ImageView imageView;
        private final LinearLayout rootView;

        ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view_creator);
            imageView = itemView.findViewById(R.id.image_view);
            rootView = itemView.findViewById(R.id.rootView);
        }
    }
}