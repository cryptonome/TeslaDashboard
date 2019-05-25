package com.joey.tesladashboard.entities.state;

import com.google.gson.annotations.SerializedName;

public class GuiSettings {
    @SerializedName("gui_24_hour_time")
    private boolean time24Hour;
    @SerializedName("gui_charge_rate_units")
    private String chargeRateUnits;
    @SerializedName("gui_distance_units")
    private String distanceUnits;
    @SerializedName("gui_range_display")
    private String rangeDisplay;
    @SerializedName("gui_temperature_units")
    private String temperatureUnits;
    @SerializedName("timestamp")
    private long timestamp;

    public GuiSettings(){
        this.time24Hour = false;
        this.chargeRateUnits = "";
        this.distanceUnits = "";
        this.rangeDisplay = "";
        this.temperatureUnits = "";
        this.timestamp = 0;
    }

    public boolean isTime24Hour() {
        return time24Hour;
    }

    public void setTime24Hour(boolean time24Hour) {
        this.time24Hour = time24Hour;
    }

    public String getChargeRateUnits() {
        return chargeRateUnits;
    }

    public void setChargeRateUnits(String chargeRateUnits) {
        this.chargeRateUnits = chargeRateUnits;
    }

    public String getDistanceUnits() {
        return distanceUnits;
    }

    public void setDistanceUnits(String distanceUnits) {
        this.distanceUnits = distanceUnits;
    }

    public String getRangeDisplay() {
        return rangeDisplay;
    }

    public void setRangeDisplay(String rangeDisplay) {
        this.rangeDisplay = rangeDisplay;
    }

    public String getTemperatureUnits() {
        return temperatureUnits;
    }

    public void setTemperatureUnits(String temperatureUnits) {
        this.temperatureUnits = temperatureUnits;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
