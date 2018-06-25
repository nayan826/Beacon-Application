package com.example.na369116.beacon_scanner;

/**
 * Created by NA369116 on 6/18/2018.
 */

public class beacon_data {

    private  String beacon_name ;
    private String beacon_tx;
    private String beacon_distance;


    public String getBeacon_name() {
        return beacon_name;
    }

    public void setBeacon_name(String beacon_name) {
        this.beacon_name = beacon_name;
    }

    public void setBeacon_tx(String beacon_tx) {
        this.beacon_tx = beacon_tx;
    }

    public String getBeacon_tx() {
        return beacon_tx;
    }

    public void setBeacon_distance(String beacon_distance) {
        this.beacon_distance = beacon_distance;
    }

    public String getBeacon_distance() {
        return beacon_distance;
    }


    public beacon_data(String beacon_name,String beacon_distance,String beacon_tx){

        this.beacon_name = beacon_name;
        this.beacon_distance = beacon_distance;
        this.beacon_tx = beacon_tx;

    }

    public beacon_data() {
    }
}
