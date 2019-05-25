package com.joey.tesladashboard.entities.state;

import com.google.gson.annotations.SerializedName;

public class SoftwareUpdate {
    @SerializedName("expected_duration_sec")
    private double expectedDurationSec;
    @SerializedName("status")
    private String status;

    public SoftwareUpdate(){
        this.expectedDurationSec = 0;
        this.status = "";
    }

    public double getExpectedDurationSec() {
        return expectedDurationSec;
    }

    public void setExpectedDurationSec(double expectedDurationSec) {
        this.expectedDurationSec = expectedDurationSec;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
