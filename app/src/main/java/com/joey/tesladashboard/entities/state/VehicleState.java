package com.joey.tesladashboard.entities.state;

import com.google.gson.annotations.SerializedName;

public class VehicleState {
    @SerializedName("api_version")
    private double apiVersion;
    @SerializedName("autopark_state_v2")
    private String autoparkStateV2;
    @SerializedName("autopark_style")
    private String autoParkStyle;
    @SerializedName("calendar_supported")
    private boolean calendarSupported;
    @SerializedName("car_version")
    private String carVersion;
    @SerializedName("center_display_state")
    private double centerDisplayState;
    @SerializedName("homelink_nearby")
    private boolean homeLinkNearby;
    @SerializedName("is_user_present")
    private boolean userPresent;
    @SerializedName("last_autopark_error")
    private String lastAutoparkError;
    @SerializedName("locked")
    private boolean locked;
    @SerializedName("notifications_supported")
    private boolean notificationsSupported;
    @SerializedName("odometer")
    private  double odometer;
    @SerializedName("parsed_calendar_supported")
    private boolean parsedCalendarSupported;
    @SerializedName("df")
    private double df;
    @SerializedName("dr")
    private double dr;
    @SerializedName("ft")
    private double ft;
    @SerializedName("pf")
    private double pf;
    @SerializedName("pr")
    private double pr;
    @SerializedName("rt")
    private double rt;
    @SerializedName("remote_start")
    private boolean remoteStart;
    @SerializedName("remote_start_enabled")
    private boolean remoteStartEnabled;
    @SerializedName("remote_start_supported")
    private boolean remoteStartSupported;
    @SerializedName("sentry_mode")
    private boolean sentryMode;
    @SerializedName("sun_roof_percent_open")
    private double sunRoofPercentOpen;
    @SerializedName("sun_roof_state")
    private String sunRoofState;
    @SerializedName("valet_mode")
    private boolean valetMode;
    @SerializedName("valet_pin_needed")
    private boolean valetPinNeeded;
    @SerializedName("vehicle_name")
    private String vehicleName;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("media_state")
    private MediaState mediaState;
    @SerializedName("software_update")
    private SoftwareUpdate softwareUpdate;
    @SerializedName("speed_limit_mode")
    private SpeedLimitMode speedLimitMode;

    public VehicleState(){
        this.apiVersion = 0;
        this.autoparkStateV2 = "";
        this.autoParkStyle = "";
        this.calendarSupported = false;
        this.carVersion = "";
        this.centerDisplayState = 0;
        this.homeLinkNearby = false;
        this.userPresent = false;
        this.lastAutoparkError = "";
        this.locked = false;
        this.notificationsSupported = false;
        this.odometer = 0;
        this.parsedCalendarSupported = false;
        this.df = 0;
        this.dr = 0;
        this.ft = 0;
        this.pf = 0;
        this.pr = 0;
        this.rt = 0;
        this.remoteStart = false;
        this.remoteStartEnabled = false;
        this.remoteStartSupported = false;
        this.sentryMode = false;
        this.sunRoofPercentOpen = 0;
        this.sunRoofState = "";
        this.valetMode = false;
        this.valetPinNeeded = false;
        this.vehicleName = "";
        this.timestamp = 0;
        this.mediaState = new MediaState();
        this.softwareUpdate = new SoftwareUpdate();
        this.speedLimitMode = new SpeedLimitMode();
    }

    public double getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(double apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getAutoparkStateV2() {
        return autoparkStateV2;
    }

    public void setAutoparkStateV2(String autoparkStateV2) {
        this.autoparkStateV2 = autoparkStateV2;
    }

    public String getAutoParkStyle() {
        return autoParkStyle;
    }

    public void setAutoParkStyle(String autoParkStyle) {
        this.autoParkStyle = autoParkStyle;
    }

    public boolean isCalendarSupported() {
        return calendarSupported;
    }

    public void setCalendarSupported(boolean calendarSupported) {
        this.calendarSupported = calendarSupported;
    }

    public String getCarVersion() {
        return carVersion;
    }

    public void setCarVersion(String carVersion) {
        this.carVersion = carVersion;
    }

    public double getCenterDisplayState() {
        return centerDisplayState;
    }

    public void setCenterDisplayState(double centerDisplayState) {
        this.centerDisplayState = centerDisplayState;
    }

    public boolean isHomeLinkNearby() {
        return homeLinkNearby;
    }

    public void setHomeLinkNearby(boolean homeLinkNearby) {
        this.homeLinkNearby = homeLinkNearby;
    }

    public boolean isUserPresent() {
        return userPresent;
    }

    public void setUserPresent(boolean userPresent) {
        this.userPresent = userPresent;
    }

    public String getLastAutoparkError() {
        return lastAutoparkError;
    }

    public void setLastAutoparkError(String lastAutoparkError) {
        this.lastAutoparkError = lastAutoparkError;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isNotificationsSupported() {
        return notificationsSupported;
    }

    public void setNotificationsSupported(boolean notificationsSupported) {
        this.notificationsSupported = notificationsSupported;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public boolean isParsedCalendarSupported() {
        return parsedCalendarSupported;
    }

    public void setParsedCalendarSupported(boolean parsedCalendarSupported) {
        this.parsedCalendarSupported = parsedCalendarSupported;
    }

    public double getDf() {
        return df;
    }

    public void setDf(double df) {
        this.df = df;
    }

    public double getDr() {
        return dr;
    }

    public void setDr(double dr) {
        this.dr = dr;
    }

    public double getFt() {
        return ft;
    }

    public void setFt(double ft) {
        this.ft = ft;
    }

    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

    public double getPr() {
        return pr;
    }

    public void setPr(double pr) {
        this.pr = pr;
    }

    public double getRt() {
        return rt;
    }

    public void setRt(double rt) {
        this.rt = rt;
    }

    public boolean isRemoteStart() {
        return remoteStart;
    }

    public void setRemoteStart(boolean remoteStart) {
        this.remoteStart = remoteStart;
    }

    public boolean isRemoteStartEnabled() {
        return remoteStartEnabled;
    }

    public void setRemoteStartEnabled(boolean remoteStartEnabled) {
        this.remoteStartEnabled = remoteStartEnabled;
    }

    public boolean isRemoteStartSupported() {
        return remoteStartSupported;
    }

    public void setRemoteStartSupported(boolean remoteStartSupported) {
        this.remoteStartSupported = remoteStartSupported;
    }

    public boolean isSentryMode() {
        return sentryMode;
    }

    public void setSentryMode(boolean sentryMode) {
        this.sentryMode = sentryMode;
    }

    public double getSunRoofPercentOpen() {
        return sunRoofPercentOpen;
    }

    public void setSunRoofPercentOpen(double sunRoofPercentOpen) {
        this.sunRoofPercentOpen = sunRoofPercentOpen;
    }

    public String getSunRoofState() {
        return sunRoofState;
    }

    public void setSunRoofState(String sunRoofState) {
        this.sunRoofState = sunRoofState;
    }

    public boolean isValetMode() {
        return valetMode;
    }

    public void setValetMode(boolean valetMode) {
        this.valetMode = valetMode;
    }

    public boolean isValetPinNeeded() {
        return valetPinNeeded;
    }

    public void setValetPinNeeded(boolean valetPinNeeded) {
        this.valetPinNeeded = valetPinNeeded;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public MediaState getMediaState() {
        return mediaState;
    }

    public void setMediaState(MediaState mediaState) {
        this.mediaState = mediaState;
    }

    public SoftwareUpdate getSoftwareUpdate() {
        return softwareUpdate;
    }

    public void setSoftwareUpdate(SoftwareUpdate softwareUpdate) {
        this.softwareUpdate = softwareUpdate;
    }

    public SpeedLimitMode getSpeedLimitMode() {
        return speedLimitMode;
    }

    public void setSpeedLimitMode(SpeedLimitMode speedLimitMode) {
        this.speedLimitMode = speedLimitMode;
    }
}