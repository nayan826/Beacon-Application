package com.example.na369116.beacon_scanner;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toolbar;

import org.altbeacon.beacon.*;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Collection;


public class MainActivity extends AppCompatActivity implements BeaconConsumer, View.OnClickListener {

    private final String TAG = "Starting App";
    private BeaconManager beaconManager;
    private Button Start_B,Stop_C,Send_Data,View_Data;
    private Beacon_Adapter b_adapter;
    private String b_name;
    private String tx,distance;

    private ArrayList<beacon_data> beacon_list = new ArrayList();;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beaconManager = BeaconManager.getInstanceForApplication(this);


        VerifyBluetooth();

        Start_B =(Button) findViewById(R.id.button1);
        Start_B.setOnClickListener(this);
        Stop_C = (Button) findViewById(R.id.button2);
        Stop_C.setOnClickListener(this);
        Send_Data = (Button) findViewById(R.id.button);
        Send_Data.setOnClickListener(this);
        View_Data = (Button) findViewById(R.id.button3);
        View_Data.setOnClickListener(this);



        beaconManager.bind(this);

// Bluetooth service

        Intent p = new Intent(BLUETOOTH_SERVICE);
        startActivity(p);

// Location Access

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                final AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Location Access");
                alert.setMessage("Please provide location access");
                alert.setPositiveButton(android.R.string.ok,null);
                alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

                    }
                });

                alert.show();


          }

        }

    }

    public void VerifyBluetooth() {
        try {
            if (!BeaconManager.getInstanceForApplication(this).checkAvailability()) {

                AlertDialog.Builder ADB = new AlertDialog.Builder(this);
                ADB.setTitle("Bluetooth Access");
                ADB.setMessage("Please Give Bluetooth Access");
                ADB.setPositiveButton(android.R.string.ok, null);
                ADB.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                        System.exit(0);

                    }


                });

                ADB.show();

            }


        } catch (RuntimeException e) {
            AlertDialog.Builder NB = new AlertDialog.Builder(this);
            NB.setTitle("BLE not supported");
            NB.setMessage("Sorry, this device does not support Bluetooth LE.");
            NB.setPositiveButton(android.R.string.ok, null);
            NB.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                    System.exit(0);

                }
            });

            NB.show();

        }
    }
    @Override
    public void onBeaconServiceConnect() {

        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {

                for (Beacon beacon:collection) {

                    b_name = String.valueOf(beacon.getId1());
                    tx = String.valueOf(beacon.getId2());
                    distance = String.valueOf(beacon.getDistance());


                    beacon_list.add(new beacon_data(b_name,tx,distance));


                }
            }
        });



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button1: {

                try {
                    beaconManager.startRangingBeaconsInRegion(new Region("my Region", null, null, null));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;

            }

            case R.id.button2:
            {

                try {

                    beaconManager.stopRangingBeaconsInRegion(new Region("my Reagion",null,null,null));
                }

                catch (RemoteException e) {
                    e.printStackTrace();

                }

                break;
            }

            case R.id.button:
            {

                Intent i = new Intent(this,Beacon_show.class);
            /*    Bundle bi = new Bundle();
                bi.putString("Name",b_name);
                bi.putString("tx",tx);
                bi.putString("distance",distance);

                i.putExtras(i); */


                startActivity(i);

                break;
            }

            case R.id.button3:
            {

                break;
            }

        }


    }
}


