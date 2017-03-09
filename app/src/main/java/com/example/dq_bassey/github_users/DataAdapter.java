package com.example.dq_bassey.github_users;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<User> users;
    private Context context;

    public DataAdapter(Context context,ArrayList<User> users) {
        this.context = context;
        this.users = users;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.textView.setText(users.get(i).getUserName());

        int imageWidth = 150;
        int imageHeight = 150;

        Common.loadImageIntoView(users.get(i), viewHolder.imageView, context, imageWidth, imageHeight);


        //Picasso.with(context).load(users.get(i).getPhotoUrl()).resize(120, 60).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public ViewHolder(View view) {
            super(view);

            textView = (TextView)view.findViewById(R.id.textView);
            imageView = (ImageView)view.findViewById(R.id.imageView);
        }
    }
}