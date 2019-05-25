package com.joey.tesladashboard;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.joey.tesladashboard.entities.User;
import com.joey.tesladashboard.entities.Vehicle;

public class MySettings {
    private static final String TAG = MySettings.class.getSimpleName();

    private static final String PREF_ACTIVE_USER = "pref_current_user";
    private static final String PREF_ACTIVE_USER_EMAIL = "pref_current_user_email";
    private static final String PREF_CURRENT_VEHICLE = "pref_current_vehicle";
    private static final String PREF_APP_FIRST_START = "app_first_start";

    private static SharedPreferences sharedPref;
    private static boolean appFirstStart;
    private static String loggedInUserEmail;
    private static User activeUser;
    private static Vehicle currentVehicle;


    private static Gson gson;

    private MySettings(){

    }

    public static void setAppFirstStart(boolean state) {
        MySettings.appFirstStart = state;

        SharedPreferences.Editor editor = getSettings().edit();
        editor.putBoolean(PREF_APP_FIRST_START, appFirstStart);
        editor.apply();
    }
    public static boolean isAppFirstStart() {
        SharedPreferences prefs = getSettings();
        appFirstStart = prefs.getBoolean(PREF_APP_FIRST_START, true);
        return appFirstStart;
    }

    private static void setCurrentUserEmail(String email) {
        MySettings.loggedInUserEmail = email;

        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(PREF_ACTIVE_USER_EMAIL, loggedInUserEmail);
        editor.apply();
    }
    private static String getCurrentUserEmail() {
        if (loggedInUserEmail != null && loggedInUserEmail.length() >= 1) {
            return loggedInUserEmail;
        } else {
            SharedPreferences prefs = getSettings();
            loggedInUserEmail = prefs.getString(PREF_ACTIVE_USER_EMAIL, "");
            return loggedInUserEmail;
        }
    }

    public static void setActiveUser(User user) {
        MySettings.activeUser = user;

        if(gson == null){
            gson = Utils.getGson();
        }

        String json = gson.toJson(MySettings.activeUser);
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(PREF_ACTIVE_USER, json);
        editor.apply();
    }
    public static User getActiveUser() {
        if(MySettings.activeUser != null){
            return MySettings.activeUser;
        }else{
            if(gson == null){
                gson = Utils.getGson();
            }
            SharedPreferences prefs = getSettings();
            String json = prefs.getString(PREF_ACTIVE_USER, "");
            if(json != null && json.length() >= 1){
                MySettings.activeUser = gson.fromJson(json, User.class);
                return MySettings.activeUser;
            }else{
                return null;
            }
        }
    }

    public static void setCurrentVehicle(Vehicle vehicle) {
        MySettings.currentVehicle = vehicle;

        if(gson == null){
            gson = Utils.getGson();
        }

        String json = gson.toJson(MySettings.currentVehicle);
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putString(PREF_CURRENT_VEHICLE, json);
        editor.apply();
    }
    public static Vehicle getCurrentVehicle() {
        if(MySettings.currentVehicle != null){
            return MySettings.currentVehicle;
        }else{
            if(gson == null){
                gson = Utils.getGson();
            }
            SharedPreferences prefs = getSettings();
            String json = prefs.getString(PREF_CURRENT_VEHICLE, "");
            if(json != null && json.length() >= 1){
                MySettings.currentVehicle = gson.fromJson(json, Vehicle.class);
                return MySettings.currentVehicle;
            }else{
                return null;
            }
        }
    }

    public static SharedPreferences getSettings() {
        if(sharedPref == null){
            sharedPref = MyApp.getShardPrefs();
        }

        return sharedPref;
    }
}
