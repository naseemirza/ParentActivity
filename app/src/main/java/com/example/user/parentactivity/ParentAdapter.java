package com.example.user.parentactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 3/29/2018.
 */

public class ParentAdapter extends ArrayAdapter<Parent> {

    private List<Parent> parentList;
    private Context mCtx;

    public ParentAdapter(List<Parent> parentList, Context mCtx) {
        super(mCtx, R.layout.list_items, parentList);
        this.parentList = parentList;
        this.mCtx = mCtx;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        ImageView imageView = (ImageView)listViewItem.findViewById(R.id.imageViewName);
        TextView textViewname = (TextView)listViewItem.findViewById(R.id.textViewname);
        Parent hero = parentList.get(position);

        Picasso.with(mCtx).load(hero.getImageUrl()).into(imageView);
        textViewname.setText(hero.getName());

        //returning the listitem
        return listViewItem;
    }
}
