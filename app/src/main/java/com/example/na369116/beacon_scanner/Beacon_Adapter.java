package com.example.na369116.beacon_scanner;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NA369116 on 6/18/2018.
 */

public class Beacon_Adapter extends ArrayAdapter<beacon_data>{

    private Context mContext;
    private List<beacon_data> beacon_list = new ArrayList();


    public Beacon_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<beacon_data> objects) {
        super(context,0, objects);

        this.mContext = context;
        this.beacon_list = objects;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = convertView;

        if (convertView == null)
        {
            listview = LayoutInflater.from(mContext).inflate(R.layout.beacon_layout,parent,false);

            beacon_data bd = beacon_list.get(position);

            TextView beacon_name = (TextView)listview.findViewById(R.id.textView2);
            beacon_name.setText(bd.getBeacon_name());

            TextView beacon_tx = (TextView)listview.findViewById(R.id.textView3);
            beacon_tx.setText(String.valueOf(bd.getBeacon_tx()));

            TextView beacon_distance = (TextView)listview.findViewById(R.id.textView4);
            beacon_distance.setText(String.valueOf(bd.getBeacon_distance()));

        }

        return listview;
    }
}