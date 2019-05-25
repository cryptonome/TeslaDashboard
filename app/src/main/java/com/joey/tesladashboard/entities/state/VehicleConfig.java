package com.joey.tesladashboard.entities.state;

import com.google.gson.annotations.SerializedName;

public class VehicleConfig {
    @SerializedName("can_accept_navigation_requests")
    private boolean canAcceptNavigationRequests;
    @SerializedName("can_actuate_trunks")
    private boolean canActuateDrinks;
    @SerializedName("car_special_type")
    private String carSpecialType;
    @SerializedName("car_type")
    private String carType;
    @SerializedName("charge_port_type")
    private String chargePortType;
    @SerializedName("exterior_color")
    private String exteriorColor;
    @SerializedName("eu_vehicle")
    private boolean euVehicle;
    @SerializedName("has_air_suspension")
    private boolean hasAirSuspension;
    @SerializedName("has_ludicrous_mode")
    private boolean hasLudicrousMode;
    @SerializedName("key_version")
    private double keyVersion;
    @SerializedName("motorized_charge_port")
    private boolean motorizedChargePort;
    @SerializedName("perf_config")
    private String perfConfig;
    @SerializedName("plg")
    private boolean plg;
    @SerializedName("rear_seat_heaters")
    private double rearSeatHeaters;
    @SerializedName("rear_seat_type")
    private double rearSeatType;
    @SerializedName("rhd")
    private boolean rhd;
    @SerializedName("roof_color")
    private String roofColor;
    @SerializedName("seat_type")
    private double seatType;
    @SerializedName("spoiler_type")
    private String spoilerType;
    @SerializedName("sun_roof_installed")
    private double sunRoofInstalled;
    @SerializedName("third_row_seats")
    private String thirdRowSeets;
    @SerializedName("trim_badging")
    private String trimBadging;
    @SerializedName("wheel_type")
    private String wheelType;
    @SerializedName("timestamp")
    private long timestamp;

    public VehicleConfig(){
        this.canAcceptNavigationRequests = false;
        this.canActuateDrinks = false;
        this.carSpecialType = "";
        this.carType = "";
        this.chargePortType = "";
        this.exteriorColor = "";
        this.euVehicle = false;
        this.hasAirSuspension = false;
        this.hasLudicrousMode = false;
        this.keyVersion = 0;
        this.motorizedChargePort = false;
        this.perfConfig = "";
        this.plg = false;
        this.rearSeatHeaters = 0;
        this.rearSeatType = 0;
        this.rhd = false;
        this.roofColor = "";
        this.seatType = 0;
        this.spoilerType = "";
        this.sunRoofInstalled = 0;
        this.thirdRowSeets = "";
        this.trimBadging = "";
        this.wheelType = "";
        this.timestamp = 0;
    }

    public boolean isCanAcceptNavigationRequests() {
        return canAcceptNavigationRequests;
    }

    public void setCanAcceptNavigationRequests(boolean canAcceptNavigationRequests) {
        this.canAcceptNavigationRequests = canAcceptNavigationRequests;
    }

    public boolean isCanActuateDrinks() {
        return canActuateDrinks;
    }

    public void setCanActuateDrinks(boolean canActuateDrinks) {
        this.canActuateDrinks = canActuateDrinks;
    }

    public String getCarSpecialType() {
        return carSpecialType;
    }

    public void setCarSpecialType(String carSpecialType) {
        this.carSpecialType = carSpecialType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getChargePortType() {
        return chargePortType;
    }

    public void setChargePortType(String chargePortType) {
        this.chargePortType = chargePortType;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public boolean isEuVehicle() {
        return euVehicle;
    }

    public void setEuVehicle(boolean euVehicle) {
        this.euVehicle = euVehicle;
    }

    public boolean isHasAirSuspension() {
        return hasAirSuspension;
    }

    public void setHasAirSuspension(boolean hasAirSuspension) {
        this.hasAirSuspension = hasAirSuspension;
    }

    public boolean isHasLudicrousMode() {
        return hasLudicrousMode;
    }

    public void setHasLudicrousMode(boolean hasLudicrousMode) {
        this.hasLudicrousMode = hasLudicrousMode;
    }

    public double getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(double keyVersion) {
        this.keyVersion = keyVersion;
    }

    public boolean isMotorizedChargePort() {
        return motorizedChargePort;
    }

    public void setMotorizedChargePort(boolean motorizedChargePort) {
        this.motorizedChargePort = motorizedChargePort;
    }

    public String getPerfConfig() {
        return perfConfig;
    }

    public void setPerfConfig(String perfConfig) {
        this.perfConfig = perfConfig;
    }

    public boolean isPlg() {
        return plg;
    }

    public void setPlg(boolean plg) {
        this.plg = plg;
    }

    public double getRearSeatHeaters() {
        return rearSeatHeaters;
    }

    public void setRearSeatHeaters(double rearSeatHeaters) {
        this.rearSeatHeaters = rearSeatHeaters;
    }

    public double getRearSeatType() {
        return rearSeatType;
    }

    public void setRearSeatType(double rearSeatType) {
        this.rearSeatType = rearSeatType;
    }

    public boolean isRhd() {
        return rhd;
    }

    public void setRhd(boolean rhd) {
        this.rhd = rhd;
    }

    public String getRoofColor() {
        return roofColor;
    }

    public void setRoofColor(String roofColor) {
        this.roofColor = roofColor;
    }

    public double getSeatType() {
        return seatType;
    }

    public void setSeatType(double seatType) {
        this.seatType = seatType;
    }

    public String getSpoilerType() {
        return spoilerType;
    }

    public void setSpoilerType(String spoilerType) {
        this.spoilerType = spoilerType;
    }

    public double getSunRoofInstalled() {
        return sunRoofInstalled;
    }

    public void setSunRoofInstalled(double sunRoofInstalled) {
        this.sunRoofInstalled = sunRoofInstalled;
    }

    public String getThirdRowSeets() {
        return thirdRowSeets;
    }

    public void setThirdRowSeets(String thirdRowSeets) {
        this.thirdRowSeets = thirdRowSeets;
    }

    public String getTrimBadging() {
        return trimBadging;
    }

    public void setTrimBadging(String trimBadging) {
        this.trimBadging = trimBadging;
    }

    public String getWheelType() {
        return wheelType;
    }

    public void setWheelType(String wheelType) {
        this.wheelType = wheelType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
