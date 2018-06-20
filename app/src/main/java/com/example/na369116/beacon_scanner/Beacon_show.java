package com.example.na369116.beacon_scanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Beacon_show extends AppCompatActivity {

    private Beacon_Adapter badapter;
    private ArrayList<beacon_data> bd = new ArrayList<>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_show);

    lv = (ListView)findViewById(R.id.beacon_list);


        badapter = new Beacon_Adapter(this,0,bd);

        lv.setAdapter(badapter);

    }
}
