package com.am.app.bouqeh.data.models;

public class LocationLatLong {
    private double lat;
    private double longitude;

    public LocationLatLong(double lat, double longitude) {

        this.lat = lat;
        this.longitude = longitude;
    }

    public double getLat() {
        return lat;
    }

    public double getLongitude() {
        return longitude;
    }
}
