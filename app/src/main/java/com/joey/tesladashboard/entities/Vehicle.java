package com.joey.tesladashboard.entities;

import com.google.gson.annotations.SerializedName;
import com.joey.tesladashboard.entities.state.ChargeState;
import com.joey.tesladashboard.entities.state.ClimateState;
import com.joey.tesladashboard.entities.state.DriveState;
import com.joey.tesladashboard.entities.state.GuiSettings;
import com.joey.tesladashboard.entities.state.VehicleState;
import com.joey.tesladashboard.entities.state.VehicleConfig;

public class Vehicle {
    @SerializedName("id")
    private long id;
    @SerializedName("vehicle_id")
    private long vehicleID;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("state")
    private String state;

    @SerializedName("drive_state")
    private DriveState driveState;
    @SerializedName("climate_state")
    private ClimateState climateState;
    @SerializedName("charge_state")
    private ChargeState chargeState;
    @SerializedName("vehicle_state")
    private VehicleState vehicleState;
    @SerializedName("gui_settings")
    private GuiSettings guiSettingsState;
    @SerializedName("vehicle_config")
    private VehicleConfig vehicleConfig;

    public Vehicle() {
        this.id = -1;
        this.vehicleID = -1;
        this.displayName = "";
        this.state = "";
        this.driveState = new DriveState();
        this.climateState = new ClimateState();
        this.chargeState = new ChargeState();
        this.vehicleState = new VehicleState();
        this.guiSettingsState = new GuiSettings();
        this.vehicleConfig = new VehicleConfig();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public DriveState getDriveState() {
        return driveState;
    }

    public void setDriveState(DriveState driveState) {
        this.driveState = driveState;
    }

    public ClimateState getClimateState() {
        return climateState;
    }

    public void setClimateState(ClimateState climateState) {
        this.climateState = climateState;
    }

    public ChargeState getChargeState() {
        return chargeState;
    }

    public void setChargeState(ChargeState chargeState) {
        this.chargeState = chargeState;
    }

    public VehicleState getVehicleState() {
        return vehicleState;
    }

    public void setVehicleState(VehicleState vehicleState) {
        this.vehicleState = vehicleState;
    }

    public GuiSettings getGuiSettingsState() {
        return guiSettingsState;
    }

    public void setGuiSettingsState(GuiSettings guiSettingsState) {
        this.guiSettingsState = guiSettingsState;
    }

    public VehicleConfig getVehicleConfig() {
        return vehicleConfig;
    }

    public void setVehicleConfig(VehicleConfig vehicleConfig) {
        this.vehicleConfig = vehicleConfig;
    }
}
