package com.joey.tesladashboard.entities.state;

import com.google.gson.annotations.SerializedName;

public class ClimateState {
    @SerializedName("battery_heater")
    private boolean batteryHeater;
    @SerializedName("battery_heater_no_power")
    private boolean batteryHeaterNoPower;
    @SerializedName("climate_keeper_mode")
    private String climateKeeperMode;
    @SerializedName("driver_temp_setting")
    private double driverTemperatureSetting;
    @SerializedName("fan_status")
    private double fanStatus;
    @SerializedName("NULL_is_auto_conditioning_on")
    private boolean autoAirConditioning;
    @SerializedName("is_climate_on")
    private boolean climateOn;
    @SerializedName("is_front_defroster_on")
    private boolean frontDefrosterOn;
    @SerializedName("is_rear_defroster_on")
    private boolean rearDefrosterOn;
    @SerializedName("is_preconditioning")
    private boolean preConditioning;
    @SerializedName("min_avail_temp")
    private double minAvailableTemperature;
    @SerializedName("max_avail_temp")
    private double maxAvailableTemperature;
    @SerializedName("NULL_inside_temp")
    private double insideTemp;
    @SerializedName("NULL_outside_temp")
    private double outsideTemperature;
    @SerializedName("passenger_temp_setting")
    private double passengerTemperatureSettings;
    @SerializedName("remote_heater_control_enabled")
    private boolean remoteHeaterControl;
    @SerializedName("NULL_left_temp_direction")
    private String leftTempDirection;
    @SerializedName("NULL_right_temp_direction")
    private String rightTempDirection;
    @SerializedName("seat_heater_left")
    private double seatHeaterLeft;
    @SerializedName("seat_heater_right")
    private double seatHeaterRight;
    @SerializedName("seat_heater_rear_center")
    private double seatHeaterRearCenter;
    @SerializedName("seat_heater_rear_left")
    private double seatHeaterRearLeft;
    @SerializedName("seat_heater_rear_left_back")
    private double seatHeaterRearLeftBack;
    @SerializedName("seat_heater_rear_right")
    private double seatHeaterRearRight;
    @SerializedName("seat_heater_rear_right_back")
    private double seatHeaterRearRightBack;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("side_mirror_heaters")
    private boolean sideMirrorHeaters;
    @SerializedName("smart_preconditioning")
    private boolean smartPreconditioning;
    @SerializedName("steering_wheel_heater")
    private boolean steeringWheelHeater;
    @SerializedName("wiper_blade_heater")
    private boolean wiperBladeHeater;

    public ClimateState(){
        this.batteryHeater = false;
        this.batteryHeaterNoPower = false;
        this.climateKeeperMode = "";
        this.driverTemperatureSetting = 0;
        this.fanStatus = 0;
        this.autoAirConditioning = false;
        this.climateOn = false;
        this.frontDefrosterOn = false;
        this.rearDefrosterOn = false;
        this.preConditioning = false;
        this.minAvailableTemperature = 0;
        this.maxAvailableTemperature = 0;
        this.insideTemp = 0;
        this.outsideTemperature = 0;
        this.passengerTemperatureSettings = 0;
        this.remoteHeaterControl = false;
        this.leftTempDirection = "";
        this.rightTempDirection = "";
        this.seatHeaterLeft = 0;
        this.seatHeaterRight = 0;
        this.seatHeaterRearCenter = 0;
        this.seatHeaterRearLeft = 0;
        this.seatHeaterRearLeftBack = 0;
        this.seatHeaterRearRight = 0;
        this.seatHeaterRearRightBack = 0;
        this.timestamp = 0;
        this.sideMirrorHeaters = false;
        this.smartPreconditioning = false;
        this.steeringWheelHeater = false;
        this.wiperBladeHeater = false;
    }

    public boolean isBatteryHeater() {
        return batteryHeater;
    }

    public void setBatteryHeater(boolean batteryHeater) {
        this.batteryHeater = batteryHeater;
    }

    public boolean isBatteryHeaterNoPower() {
        return batteryHeaterNoPower;
    }

    public void setBatteryHeaterNoPower(boolean batteryHeaterNoPower) {
        this.batteryHeaterNoPower = batteryHeaterNoPower;
    }

    public String getClimateKeeperMode() {
        return climateKeeperMode;
    }

    public void setClimateKeeperMode(String climateKeeperMode) {
        this.climateKeeperMode = climateKeeperMode;
    }

    public double getDriverTemperatureSetting() {
        return driverTemperatureSetting;
    }

    public void setDriverTemperatureSetting(double driverTemperatureSetting) {
        this.driverTemperatureSetting = driverTemperatureSetting;
    }

    public double getFanStatus() {
        return fanStatus;
    }

    public void setFanStatus(double fanStatus) {
        this.fanStatus = fanStatus;
    }

    public boolean isAutoAirConditioning() {
        return autoAirConditioning;
    }

    public void setAutoAirConditioning(boolean autoAirConditioning) {
        this.autoAirConditioning = autoAirConditioning;
    }

    public boolean isClimateOn() {
        return climateOn;
    }

    public void setClimateOn(boolean climateOn) {
        this.climateOn = climateOn;
    }

    public boolean isFrontDefrosterOn() {
        return frontDefrosterOn;
    }

    public void setFrontDefrosterOn(boolean frontDefrosterOn) {
        this.frontDefrosterOn = frontDefrosterOn;
    }

    public boolean isRearDefrosterOn() {
        return rearDefrosterOn;
    }

    public void setRearDefrosterOn(boolean rearDefrosterOn) {
        this.rearDefrosterOn = rearDefrosterOn;
    }

    public boolean isPreConditioning() {
        return preConditioning;
    }

    public void setPreConditioning(boolean preConditioning) {
        this.preConditioning = preConditioning;
    }

    public double getMinAvailableTemperature() {
        return minAvailableTemperature;
    }

    public void setMinAvailableTemperature(double minAvailableTemperature) {
        this.minAvailableTemperature = minAvailableTemperature;
    }

    public double getMaxAvailableTemperature() {
        return maxAvailableTemperature;
    }

    public void setMaxAvailableTemperature(double maxAvailableTemperature) {
        this.maxAvailableTemperature = maxAvailableTemperature;
    }

    public double getInsideTemp() {
        return insideTemp;
    }

    public void setInsideTemp(double insideTemp) {
        this.insideTemp = insideTemp;
    }

    public double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public double getPassengerTemperatureSettings() {
        return passengerTemperatureSettings;
    }

    public void setPassengerTemperatureSettings(double passengerTemperatureSettings) {
        this.passengerTemperatureSettings = passengerTemperatureSettings;
    }

    public boolean isRemoteHeaterControl() {
        return remoteHeaterControl;
    }

    public void setRemoteHeaterControl(boolean remoteHeaterControl) {
        this.remoteHeaterControl = remoteHeaterControl;
    }

    public String getLeftTempDirection() {
        return leftTempDirection;
    }

    public void setLeftTempDirection(String leftTempDirection) {
        this.leftTempDirection = leftTempDirection;
    }

    public String getRightTempDirection() {
        return rightTempDirection;
    }

    public void setRightTempDirection(String rightTempDirection) {
        this.rightTempDirection = rightTempDirection;
    }

    public double getSeatHeaterLeft() {
        return seatHeaterLeft;
    }

    public void setSeatHeaterLeft(double seatHeaterLeft) {
        this.seatHeaterLeft = seatHeaterLeft;
    }

    public double getSeatHeaterRight() {
        return seatHeaterRight;
    }

    public void setSeatHeaterRight(double seatHeaterRight) {
        this.seatHeaterRight = seatHeaterRight;
    }

    public double getSeatHeaterRearCenter() {
        return seatHeaterRearCenter;
    }

    public void setSeatHeaterRearCenter(double seatHeaterRearCenter) {
        this.seatHeaterRearCenter = seatHeaterRearCenter;
    }

    public double getSeatHeaterRearLeft() {
        return seatHeaterRearLeft;
    }

    public void setSeatHeaterRearLeft(double seatHeaterRearLeft) {
        this.seatHeaterRearLeft = seatHeaterRearLeft;
    }

    public double getSeatHeaterRearLeftBack() {
        return seatHeaterRearLeftBack;
    }

    public void setSeatHeaterRearLeftBack(double seatHeaterRearLeftBack) {
        this.seatHeaterRearLeftBack = seatHeaterRearLeftBack;
    }

    public double getSeatHeaterRearRight() {
        return seatHeaterRearRight;
    }

    public void setSeatHeaterRearRight(double seatHeaterRearRight) {
        this.seatHeaterRearRight = seatHeaterRearRight;
    }

    public double getSeatHeaterRearRightBack() {
        return seatHeaterRearRightBack;
    }

    public void setSeatHeaterRearRightBack(double seatHeaterRearRightBack) {
        this.seatHeaterRearRightBack = seatHeaterRearRightBack;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSideMirrorHeaters() {
        return sideMirrorHeaters;
    }

    public void setSideMirrorHeaters(boolean sideMirrorHeaters) {
        this.sideMirrorHeaters = sideMirrorHeaters;
    }

    public boolean isSmartPreconditioning() {
        return smartPreconditioning;
    }

    public void setSmartPreconditioning(boolean smartPreconditioning) {
        this.smartPreconditioning = smartPreconditioning;
    }

    public boolean isSteeringWheelHeater() {
        return steeringWheelHeater;
    }

    public void setSteeringWheelHeater(boolean steeringWheelHeater) {
        this.steeringWheelHeater = steeringWheelHeater;
    }

    public boolean isWiperBladeHeater() {
        return wiperBladeHeater;
    }

    public void setWiperBladeHeater(boolean wiperBladeHeater) {
        this.wiperBladeHeater = wiperBladeHeater;
    }
}
