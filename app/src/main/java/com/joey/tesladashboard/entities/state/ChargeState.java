package com.joey.tesladashboard.entities.state;

import com.google.gson.annotations.SerializedName;

public class ChargeState {
    @SerializedName("battery_heater_on")
    private boolean batteryHeaterOn;
    @SerializedName("battery_level")
    private double batteryLevel;
    @SerializedName("battery_range")
    private double batteryRange;
    @SerializedName("charge_current_request")
    private double chargeCurrentRequest;
    @SerializedName("charge_current_request_max")
    private double chargeMaxCurrentRequest;
    @SerializedName("charge_enable_request")
    private boolean chargeEnableRequest;
    @SerializedName("charge_energy_added")
    private double chargeEnergyAdded;
    @SerializedName("charge_limit_soc")
    private double chargeLimitSoc;
    @SerializedName("charge_limit_soc_max")
    private double maxChargeLimitSoc;
    @SerializedName("charge_limit_soc_min")
    private double minChargeLimitSoc;
    @SerializedName("charge_limit_soc_std")
    private double chargeLimitSocStd;
    @SerializedName("charge_miles_added_ideal")
    private double chargeMilesAddedIdeal;
    @SerializedName("charge_miles_added_rated")
    private double chargeMilesAddedRated;
    @SerializedName("charge_port_cold_weather_mode")
    private boolean chargePortColdWeatherMode;
    @SerializedName("charge_port_door_open")
    private boolean chargePortDoorOpen;
    @SerializedName("charge_port_latch")
    private String chargeModeLatch;
    @SerializedName("charge_rate")
    private double chargeRate;
    @SerializedName("charge_to_max_range")
    private boolean chargeToMaxRange;
    @SerializedName("charger_actual_current")
    private double chargeActualCurrent;
    @SerializedName("NULL_charger_phases")
    private String chargerPhases;
    @SerializedName("charger_pilot_current")
    private double chargerPilotCurrent;
    @SerializedName("charger_power")
    private double chargerPower;
    @SerializedName("charger_voltage")
    private double chargerVoltage;
    @SerializedName("charging_state")
    private String chargerState;
    @SerializedName("conn_charge_cable")
    private String connectedChargeCable;
    @SerializedName("est_battery_range")
    private double estimatedBatteryRange;
    @SerializedName("fast_charger_brand")
    private String fastChargerBrand;
    @SerializedName("fast_charger_present")
    private boolean fastChargerPresent;
    @SerializedName("fast_charger_type")
    private String fastChargerType;
    @SerializedName("ideal_battery_range")
    private double idealBatteryRange;
    @SerializedName("managed_charging_active")
    private boolean managedChargingActive;
    @SerializedName("NULL_managed_charging_start_time")
    private String managedChargingStartTime;
    @SerializedName("managed_charging_user_canceled")
    private boolean managedChargingUserCancelled;
    @SerializedName("max_range_charge_counter")
    private double maxRangeChargeCounter;
    @SerializedName("not_enough_power_to_heat")
    private boolean notEnoughPowerToHeat;
    @SerializedName("scheduled_charging_pending")
    private boolean scheduledChargingPending;
    @SerializedName("NULL_scheduled_charging_start_time")
    private String scheduledChargingStartTime;
    @SerializedName("time_to_full_charge")
    private double timeToFullCharge;
    @SerializedName("trip_charging")
    private boolean tripCharging;
    @SerializedName("usable_battery_level")
    private double usableBatteryLevel;
    @SerializedName("NULL_user_charge_enable_request")
    private String userChargeEnableRequest;
    @SerializedName("timestamp")
    private long timestamp;

    public ChargeState(){
        this.batteryHeaterOn = false;
        this.batteryLevel = 0;
        this.batteryRange = 0;
        this.chargeCurrentRequest = 0;
        this.chargeMaxCurrentRequest = 0;
        this.chargeEnableRequest = false;
        this.chargeEnergyAdded = 0;
        this.chargeLimitSoc = 0;
        this.maxChargeLimitSoc  = 0;
        this.minChargeLimitSoc = 0;
        this.chargeLimitSocStd = 0;
        this.chargeMilesAddedIdeal = 0;
        this.chargeMilesAddedRated = 0;
        this.chargePortColdWeatherMode = false;
        this.chargePortDoorOpen = false;
        this.chargeModeLatch = "";
        this.chargeRate = 0;
        this.chargeToMaxRange = false;
        this.chargeActualCurrent = 0;
        this.chargerPhases = "";
        this.chargerPilotCurrent = 0;
        this.chargerPower = 0;
        this.chargerVoltage = 0;
        this.chargerState = "";
        this.connectedChargeCable = "";
        this.estimatedBatteryRange = 0;
        this.fastChargerBrand = "";
        this.fastChargerPresent = false;
        this.fastChargerType = "";
        this.idealBatteryRange = 0;
        this.managedChargingActive = false;
        this.managedChargingStartTime = "";
        this.managedChargingUserCancelled = false;
        this.maxRangeChargeCounter = 0;
        this.notEnoughPowerToHeat = false;
        this.scheduledChargingPending = false;
        this.scheduledChargingStartTime = "";
        this.timeToFullCharge = 0;
        this.tripCharging = false;
        this.usableBatteryLevel = 0;
        this.userChargeEnableRequest = "";
        this.timestamp = 0;
    }

    public boolean isBatteryHeaterOn() {
        return batteryHeaterOn;
    }

    public void setBatteryHeaterOn(boolean batteryHeaterOn) {
        this.batteryHeaterOn = batteryHeaterOn;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public double getBatteryRange() {
        return batteryRange;
    }

    public void setBatteryRange(double batteryRange) {
        this.batteryRange = batteryRange;
    }

    public double getChargeCurrentRequest() {
        return chargeCurrentRequest;
    }

    public void setChargeCurrentRequest(double chargeCurrentRequest) {
        this.chargeCurrentRequest = chargeCurrentRequest;
    }

    public double getChargeMaxCurrentRequest() {
        return chargeMaxCurrentRequest;
    }

    public void setChargeMaxCurrentRequest(double chargeMaxCurrentRequest) {
        this.chargeMaxCurrentRequest = chargeMaxCurrentRequest;
    }

    public boolean isChargeEnableRequest() {
        return chargeEnableRequest;
    }

    public void setChargeEnableRequest(boolean chargeEnableRequest) {
        this.chargeEnableRequest = chargeEnableRequest;
    }

    public double getChargeEnergyAdded() {
        return chargeEnergyAdded;
    }

    public void setChargeEnergyAdded(double chargeEnergyAdded) {
        this.chargeEnergyAdded = chargeEnergyAdded;
    }

    public double getChargeLimitSoc() {
        return chargeLimitSoc;
    }

    public void setChargeLimitSoc(double chargeLimitSoc) {
        this.chargeLimitSoc = chargeLimitSoc;
    }

    public double getMaxChargeLimitSoc() {
        return maxChargeLimitSoc;
    }

    public void setMaxChargeLimitSoc(double maxChargeLimitSoc) {
        this.maxChargeLimitSoc = maxChargeLimitSoc;
    }

    public double getMinChargeLimitSoc() {
        return minChargeLimitSoc;
    }

    public void setMinChargeLimitSoc(double minChargeLimitSoc) {
        this.minChargeLimitSoc = minChargeLimitSoc;
    }

    public double getChargeLimitSocStd() {
        return chargeLimitSocStd;
    }

    public void setChargeLimitSocStd(double chargeLimitSocStd) {
        this.chargeLimitSocStd = chargeLimitSocStd;
    }

    public double getChargeMilesAddedIdeal() {
        return chargeMilesAddedIdeal;
    }

    public void setChargeMilesAddedIdeal(double chargeMilesAddedIdeal) {
        this.chargeMilesAddedIdeal = chargeMilesAddedIdeal;
    }

    public double getChargeMilesAddedRated() {
        return chargeMilesAddedRated;
    }

    public void setChargeMilesAddedRated(double chargeMilesAddedRated) {
        this.chargeMilesAddedRated = chargeMilesAddedRated;
    }

    public boolean isChargePortColdWeatherMode() {
        return chargePortColdWeatherMode;
    }

    public void setChargePortColdWeatherMode(boolean chargePortColdWeatherMode) {
        this.chargePortColdWeatherMode = chargePortColdWeatherMode;
    }

    public boolean isChargePortDoorOpen() {
        return chargePortDoorOpen;
    }

    public void setChargePortDoorOpen(boolean chargePortDoorOpen) {
        this.chargePortDoorOpen = chargePortDoorOpen;
    }

    public String getChargeModeLatch() {
        return chargeModeLatch;
    }

    public void setChargeModeLatch(String chargeModeLatch) {
        this.chargeModeLatch = chargeModeLatch;
    }

    public double getChargeRate() {
        return chargeRate;
    }

    public void setChargeRate(double chargeRate) {
        this.chargeRate = chargeRate;
    }

    public boolean isChargeToMaxRange() {
        return chargeToMaxRange;
    }

    public void setChargeToMaxRange(boolean chargeToMaxRange) {
        this.chargeToMaxRange = chargeToMaxRange;
    }

    public double getChargeActualCurrent() {
        return chargeActualCurrent;
    }

    public void setChargeActualCurrent(double chargeActualCurrent) {
        this.chargeActualCurrent = chargeActualCurrent;
    }

    public String getChargerPhases() {
        return chargerPhases;
    }

    public void setChargerPhases(String chargerPhases) {
        this.chargerPhases = chargerPhases;
    }

    public double getChargerPilotCurrent() {
        return chargerPilotCurrent;
    }

    public void setChargerPilotCurrent(double chargerPilotCurrent) {
        this.chargerPilotCurrent = chargerPilotCurrent;
    }

    public double getChargerPower() {
        return chargerPower;
    }

    public void setChargerPower(double chargerPower) {
        this.chargerPower = chargerPower;
    }

    public double getChargerVoltage() {
        return chargerVoltage;
    }

    public void setChargerVoltage(double chargerVoltage) {
        this.chargerVoltage = chargerVoltage;
    }

    public String getChargerState() {
        return chargerState;
    }

    public void setChargerState(String chargerState) {
        this.chargerState = chargerState;
    }

    public String getConnectedChargeCable() {
        return connectedChargeCable;
    }

    public void setConnectedChargeCable(String connectedChargeCable) {
        this.connectedChargeCable = connectedChargeCable;
    }

    public double getEstimatedBatteryRange() {
        return estimatedBatteryRange;
    }

    public void setEstimatedBatteryRange(double estimatedBatteryRange) {
        this.estimatedBatteryRange = estimatedBatteryRange;
    }

    public String getFastChargerBrand() {
        return fastChargerBrand;
    }

    public void setFastChargerBrand(String fastChargerBrand) {
        this.fastChargerBrand = fastChargerBrand;
    }

    public boolean isFastChargerPresent() {
        return fastChargerPresent;
    }

    public void setFastChargerPresent(boolean fastChargerPresent) {
        this.fastChargerPresent = fastChargerPresent;
    }

    public String getFastChargerType() {
        return fastChargerType;
    }

    public void setFastChargerType(String fastChargerType) {
        this.fastChargerType = fastChargerType;
    }

    public double getIdealBatteryRange() {
        return idealBatteryRange;
    }

    public void setIdealBatteryRange(double idealBatteryRange) {
        this.idealBatteryRange = idealBatteryRange;
    }

    public boolean isManagedChargingActive() {
        return managedChargingActive;
    }

    public void setManagedChargingActive(boolean managedChargingActive) {
        this.managedChargingActive = managedChargingActive;
    }

    public String getManagedChargingStartTime() {
        return managedChargingStartTime;
    }

    public void setManagedChargingStartTime(String managedChargingStartTime) {
        this.managedChargingStartTime = managedChargingStartTime;
    }

    public boolean isManagedChargingUserCancelled() {
        return managedChargingUserCancelled;
    }

    public void setManagedChargingUserCancelled(boolean managedChargingUserCancelled) {
        this.managedChargingUserCancelled = managedChargingUserCancelled;
    }

    public double getMaxRangeChargeCounter() {
        return maxRangeChargeCounter;
    }

    public void setMaxRangeChargeCounter(double maxRangeChargeCounter) {
        this.maxRangeChargeCounter = maxRangeChargeCounter;
    }

    public boolean isNotEnoughPowerToHeat() {
        return notEnoughPowerToHeat;
    }

    public void setNotEnoughPowerToHeat(boolean notEnoughPowerToHeat) {
        this.notEnoughPowerToHeat = notEnoughPowerToHeat;
    }

    public boolean isScheduledChargingPending() {
        return scheduledChargingPending;
    }

    public void setScheduledChargingPending(boolean scheduledChargingPending) {
        this.scheduledChargingPending = scheduledChargingPending;
    }

    public String getScheduledChargingStartTime() {
        return scheduledChargingStartTime;
    }

    public void setScheduledChargingStartTime(String scheduledChargingStartTime) {
        this.scheduledChargingStartTime = scheduledChargingStartTime;
    }

    public double getTimeToFullCharge() {
        return timeToFullCharge;
    }

    public void setTimeToFullCharge(double timeToFullCharge) {
        this.timeToFullCharge = timeToFullCharge;
    }

    public boolean isTripCharging() {
        return tripCharging;
    }

    public void setTripCharging(boolean tripCharging) {
        this.tripCharging = tripCharging;
    }

    public double getUsableBatteryLevel() {
        return usableBatteryLevel;
    }

    public void setUsableBatteryLevel(double usableBatteryLevel) {
        this.usableBatteryLevel = usableBatteryLevel;
    }

    public String getUserChargeEnableRequest() {
        return userChargeEnableRequest;
    }

    public void setUserChargeEnableRequest(String userChargeEnableRequest) {
        this.userChargeEnableRequest = userChargeEnableRequest;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
