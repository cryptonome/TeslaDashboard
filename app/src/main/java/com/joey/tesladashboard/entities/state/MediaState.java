package com.joey.tesladashboard.entities.state;

import com.google.gson.annotations.SerializedName;

public class MediaState {
    @SerializedName("remote_control_enabled")
    private boolean remoteControlEnabled;

    public MediaState(){
        this.remoteControlEnabled = false;
    }

    public boolean isRemoteControlEnabled() {
        return remoteControlEnabled;
    }

    public void setRemoteControlEnabled(boolean remoteControlEnabled) {
        this.remoteControlEnabled = remoteControlEnabled;
    }
}
