package com.diez.andres.mytest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HectorAndres on 12/14/2015.
 */
public class AdapterList extends ArrayAdapter<Row> {

    private int selectedIndex;

    public AdapterList(Context context, ArrayList<Row> row) {

        super(context, 0, row);
        selectedIndex = -1;

    }
    public void setSelectedIndex(int ind)
    {
        selectedIndex = ind;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Row user = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }



        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.textView);
        tvName.setText(user.name);
        ImageView img=(ImageView)convertView.findViewById(R.id.imageView);
        img.setImageBitmap(user.hometown);



        return convertView;
    }
}
