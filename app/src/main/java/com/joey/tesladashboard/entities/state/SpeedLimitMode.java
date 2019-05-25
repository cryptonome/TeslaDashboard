package com.joey.tesladashboard.entities.state;

import com.google.gson.annotations.SerializedName;

public class SpeedLimitMode {
    @SerializedName("active")
    private boolean active;
    @SerializedName("current_limit_mph")
    private double currentLimitMph;
    @SerializedName("max_limit_mph")
    private double maxLimitMph;
    @SerializedName("min_limit_mph")
    private double minLimitMph;
    @SerializedName("pin_code_set")
    private boolean pinCodeSet;

    public SpeedLimitMode(){
        this.active = false;
        this.currentLimitMph = 0;
        this.maxLimitMph = 0;
        this.minLimitMph = 0;
        this.pinCodeSet = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getCurrentLimitMph() {
        return currentLimitMph;
    }

    public void setCurrentLimitMph(double currentLimitMph) {
        this.currentLimitMph = currentLimitMph;
    }

    public double getMaxLimitMph() {
        return maxLimitMph;
    }

    public void setMaxLimitMph(double maxLimitMph) {
        this.maxLimitMph = maxLimitMph;
    }

    public double getMinLimitMph() {
        return minLimitMph;
    }

    public void setMinLimitMph(double minLimitMph) {
        this.minLimitMph = minLimitMph;
    }

    public boolean isPinCodeSet() {
        return pinCodeSet;
    }

    public void setPinCodeSet(boolean pinCodeSet) {
        this.pinCodeSet = pinCodeSet;
    }
}
