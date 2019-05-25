package com.joey.tesladashboard.entities.state;

import com.google.gson.annotations.SerializedName;

public class DriveState {
    @SerializedName("gps_as_of")
    private long gpsTimestamp;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("heading")
    private double heading;

    @SerializedName("native_location_supported")
    private double nativeLocationSupported;
    @SerializedName("native_latitude")
    private double nativeLatitude;
    @SerializedName("native_longitude")
    private double nativeLongitude;

    @SerializedName("native_type")
    private String nativeType;

    @SerializedName("power")
    private double power;

    @SerializedName("NULL_shift_state")
    private double shiftState;

    @SerializedName("NULL_speed")
    private double speed;
    @SerializedName("timestamp")
    private long timestamp;

    public DriveState(){
        this.gpsTimestamp = 0;
        this.latitude = 0;
        this.longitude = 0;
        this.heading = 0;
        this.nativeLocationSupported = 0;
        this.nativeLatitude = 0;
        this.nativeLongitude = 0;
        this.nativeType = "";
        this.power = 0;
        this.shiftState = 0;
        this.speed = 0;
        this.timestamp = 0;
    }

    public long getGpsTimestamp() {
        return gpsTimestamp;
    }

    public void setGpsTimestamp(long gpsTimestamp) {
        this.gpsTimestamp = gpsTimestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getNativeLocationSupported() {
        return nativeLocationSupported;
    }

    public void setNativeLocationSupported(double nativeLocationSupported) {
        this.nativeLocationSupported = nativeLocationSupported;
    }

    public double getNativeLatitude() {
        return nativeLatitude;
    }

    public void setNativeLatitude(double nativeLatitude) {
        this.nativeLatitude = nativeLatitude;
    }

    public double getNativeLongitude() {
        return nativeLongitude;
    }

    public void setNativeLongitude(double nativeLongitude) {
        this.nativeLongitude = nativeLongitude;
    }

    public String getNativeType() {
        return nativeType;
    }

    public void setNativeType(String nativeType) {
        this.nativeType = nativeType;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getShiftState() {
        return shiftState;
    }

    public void setShiftState(double shiftState) {
        this.shiftState = shiftState;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
