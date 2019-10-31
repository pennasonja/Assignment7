package com.example.assignment7;


import android.app.Activity;
import android.content.Context;
import android.graphics.Picture;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import com.squareup.picasso.Picasso;


public class mAdapter extends ArrayAdapter<Picture> {


    private Context context;
    private int layoutResourceId;
    private ArrayList<Picture> list;


    public mAdapter (Context context, int layoutResourceId, ArrayList<Picture> itemList){
        super(context, layoutResourceId, itemList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = list;
    }

    private class ViewHolder{
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.image, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image = convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        System.out.println(position);
        // en saanut picassoa pelittämään
        //Picasso.get().load(list.get(position).getUrl()).into(viewHolder.image);
        return convertView;

    }
}
