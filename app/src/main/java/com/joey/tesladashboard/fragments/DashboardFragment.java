package com.joey.tesladashboard.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cardiomood.android.controls.gauge.BatteryIndicatorGauge;
import com.github.anastr.speedviewlib.PointerSpeedometer;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joey.tesladashboard.Constants;
import com.joey.tesladashboard.HttpConnector;
import com.joey.tesladashboard.MySettings;
import com.joey.tesladashboard.R;
import com.joey.tesladashboard.Utils;
import com.joey.tesladashboard.activities.MainActivity;
import com.joey.tesladashboard.entities.Vehicle;
import com.sdsmdg.harjot.crollerTest.Croller;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    private static final String TAG = DashboardFragment.class.getSimpleName();

    private static final int RC_PERMISSION_LOCATION = 1004;
    private static final int RC_ACTIVITY_LOCATION_TURN_ON = 1008;
    private static final int RC_ACTIVITY_PERMISSION_TURN_ON = 1009;
    private static final int REQUEST_CHECK_SETTINGS = 1001;

    private FusedLocationProviderClient fusedLocationClient;
    LocationCallback locationCallback;


    TextView speedTextView;
    Button selectVehicleButton;
    Croller speedCroller;
    //SpeedometerGauge speedometer;
    PointerSpeedometer pointerSpeedometer;

    CardView vehicleLayout;
    TextView vehicleNameTextView, vehicleStatusTextView, vehicleTimestampTextView, vehicleBatteryStaticTextView, vehicleBatteryPercentageTextView;
    BatteryIndicatorGauge batteryIndicator;

    double currentSpeed;

    Timer timer;
    TimerTask doAsynchronousTask;
    Handler handler;

    Vehicle vehicle;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment DashboardFragment.
     */
    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        MainActivity.setActionBarTitle(Utils.getString(getActivity(), R.string.dashboard), getResources().getColor(R.color.whiteColor));
        MainActivity.setActionBarIcon(R.drawable.ic_menu_white_36dp);
        setHasOptionsMenu(true);

        speedTextView = view.findViewById(R.id.speed_textview);
        selectVehicleButton = view.findViewById(R.id.select_vehicle_button);
        speedCroller = view.findViewById(R.id.speed_croller);
        //speedometer = view.findViewById(R.id.speedometer);
        pointerSpeedometer = view.findViewById(R.id.pointer_speedometer);

        vehicleNameTextView = view.findViewById(R.id.vehicle_name_textview);
        vehicleStatusTextView = view.findViewById(R.id.vehicle_status_textview);
        vehicleTimestampTextView = view.findViewById(R.id.vehicle_timestamp_textview);
        vehicleBatteryStaticTextView = view.findViewById(R.id.vehicle_battery_percentage_static_textview);
        vehicleBatteryPercentageTextView = view.findViewById(R.id.vehicle_battery_percentage_textview);
        vehicleLayout = view.findViewById(R.id.vehicle_layout);
        batteryIndicator = view.findViewById(R.id.battery_indicator);

        currentSpeed = 0;
        //speedCroller.setMax(200);

        /*// configure value range and ticks
        speedometer.setMaxSpeed(200);
        speedometer.setMajorTickStep(30);
        speedometer.setMinorTicks(2);

        // Configure value range colors
        speedometer.addColoredRange(30, 120, Color.GREEN);
        speedometer.addColoredRange(120, 160, Color.YELLOW);
        speedometer.addColoredRange(160, 200, Color.RED);

        // Add label converter
        speedometer.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });*/

        pointerSpeedometer.setMinSpeed(0);
        pointerSpeedometer.setMaxSpeed(200); //TODO get from API
        pointerSpeedometer.setUnit("m/h");
        pointerSpeedometer.setWithTremble(false);
        pointerSpeedometer.setTrembleDegree(2);


        batteryIndicator.setMin(0);
        batteryIndicator.setMax(100);


        vehicle = MySettings.getCurrentVehicle();
        if(vehicle != null){
            //connect to Tesla API to get vehicle info
            startTimer();//call wakeVehicle every 5 seconds
        }

        updateUI();

        selectVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction = Utils.setAnimations(fragmentTransaction, Utils.ANIMATION_TYPE_TRANSLATION);
                fragmentTransaction.replace(R.id.fragment_view, VehicleSelectionFragment.newInstance(), "vehicleSelectionFragment");
                fragmentTransaction.addToBackStack("vehicleSelectionFragment");
                fragmentTransaction.commit();
            }
        });

        ImageView actionButton = MainActivity.getToolbar().findViewById(R.id.toolbar_icon);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.openDrawer();
            }
        });

        return view;
    }

    private void updateUI(){
        //update UI
        if(vehicle == null){
            selectVehicleButton.setVisibility(View.VISIBLE);

            vehicleLayout.setVisibility(View.GONE);
            //speedTextView.setVisibility(View.VISIBLE);
            //speedCroller.setVisibility(View.GONE);
            //speedometer.setVisibility(View.VISIBLE);
            pointerSpeedometer.setVisibility(View.GONE);
            //vehicleBatteryStaticTextView.setVisibility(View.VISIBLE);
            vehicleBatteryPercentageTextView.setVisibility(View.GONE);
            batteryIndicator.setVisibility(View.GONE);
        }else{
            selectVehicleButton.setVisibility(View.GONE);

            vehicleLayout.setVisibility(View.VISIBLE);
            //speedTextView.setVisibility(View.VISIBLE);
            //speedCroller.setVisibility(View.VISIBLE);
            //speedometer.setVisibility(View.VISIBLE);
            pointerSpeedometer.setVisibility(View.VISIBLE);
            //vehicleBatteryStaticTextView.setVisibility(View.VISIBLE);
            vehicleBatteryPercentageTextView.setVisibility(View.VISIBLE);
            batteryIndicator.setVisibility(View.VISIBLE);

            vehicleNameTextView.setText(""+vehicle.getDisplayName());
            vehicleStatusTextView.setText(""+vehicle.getState());
            vehicleTimestampTextView.setText(""+Utils.getTimeStringDateHoursMinutes(vehicle.getVehicleState().getTimestamp()));
            //float batteryPercentage = (float)(vehicle.getChargeState().getBatteryLevel() / vehicle.getChargeState().getBatteryRange());
            float batteryPercentage = (float)vehicle.getChargeState().getBatteryLevel();
            vehicleBatteryPercentageTextView.setText("" + batteryPercentage);
            batteryIndicator.setValue(batteryPercentage);
        }
    }

    private void updateLocationUI(){
        //speedTextView.setText(Math.round(currentSpeed) + " m/h");
        //speedCroller.setProgress(new Double(currentSpeed).intValue());
        //speedometer.setSpeed(100, true);
        pointerSpeedometer.speedTo((float)currentSpeed);
    }

    private void startTimer(){
        if(timer == null){
            timer = new Timer();
            handler = new Handler();
            doAsynchronousTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            wakeVehicle();
                        }
                    });
                }
            };
            timer.schedule(doAsynchronousTask, 0, Constants.REFRESH_RATE); //execute in every REFRESH_RATE_MS
        }
    }

    private void stopTimer(){
        if(doAsynchronousTask != null) {
            doAsynchronousTask.cancel();
        }
        if(timer != null) {
            timer.cancel();
            timer.purge();
        }
        timer = null;
    }

    private void wakeVehicle(){
        String url = String.format(Constants.WAKE_VEHICLE_URL, vehicle.getId());


        Log.d(TAG, "wakeVehicle URL: " + url);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "wakeVehicle response: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject != null) {
                        Gson gson = Utils.getGson();
                        Type type = new TypeToken<Vehicle>() {}.getType();
                        JSONObject vehicleJsonObject = jsonObject.getJSONObject(Constants.PARAMETER_RESPONSE);
                        vehicle = gson.fromJson(vehicleJsonObject.toString(), type);

                        MySettings.setCurrentVehicle(vehicle);

                        getVehicleInfo();
                    }

                } catch (JSONException e) {
                    Log.d(TAG, "Json Exception: " + e.getMessage());
                } catch (IllegalStateException e) {
                    Log.d(TAG, "Error parsing GSON response.");
                    Utils.showToast(getActivity(), getResources().getString(R.string.server_connection_error), true);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Volley Error: " + error.getMessage());
                if(error.networkResponse != null && error.networkResponse.statusCode == 401){
                    Utils.refreshToken(getActivity(), new Utils.OnTokenRefreshed() {
                        @Override
                        public void onTokenRefreshed() {
                            wakeVehicle();
                        }
                    });
                }else if(error.networkResponse != null && error.networkResponse.statusCode == 408){
                    Utils.showToast(getActivity(), getResources().getString(R.string.vehicle_sleeping), true);
                }else {
                    Utils.showToast(getActivity(), getResources().getString(R.string.server_connection_error), true);
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.PARAMETER_HEADER_AUTH, String.format(Constants.PARAMETER_HEADER_AUTH_VALUE, MySettings.getActiveUser().getAccessToken()));


                return params;
            }
        };
        request.setShouldCache(false);
        HttpConnector.getInstance(getActivity()).addToRequestQueue(request);
    }

    private void getVehicleInfo(){
        String url = String.format(Constants.GET_VEHICLE_INFO_URL, vehicle.getId());


        Log.d(TAG, "getVehicleInfo URL: " + url);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "getVehicleInfo response: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject != null) {
                        Gson gson = Utils.getGson();
                        Type type = new TypeToken<Vehicle>() {}.getType();
                        JSONObject vehicleJsonObject = jsonObject.getJSONObject(Constants.PARAMETER_RESPONSE);
                        vehicle = gson.fromJson(vehicleJsonObject.toString(), type);

                        MySettings.setCurrentVehicle(vehicle);

                        //update UI
                        updateUI();
                    }

                } catch (JSONException e) {
                    Log.d(TAG, "Json Exception: " + e.getMessage());
                } catch (IllegalStateException e) {
                    Log.d(TAG, "Error parsing GSON response.");
                    Utils.showToast(getActivity(), getResources().getString(R.string.server_connection_error), true);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Volley Error: " + error.getMessage());
                if(error.networkResponse != null && error.networkResponse.statusCode == 401){
                    Utils.refreshToken(getActivity(), new Utils.OnTokenRefreshed() {
                        @Override
                        public void onTokenRefreshed() {
                            getVehicleInfo();
                        }
                    });
                }else if(error.networkResponse != null && error.networkResponse.statusCode == 408){
                    Utils.showToast(getActivity(), getResources().getString(R.string.vehicle_sleeping), true);
                }else {
                    Utils.showToast(getActivity(), getResources().getString(R.string.server_connection_error), true);
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.PARAMETER_HEADER_AUTH, String.format(Constants.PARAMETER_HEADER_AUTH_VALUE, MySettings.getActiveUser().getAccessToken()));


                return params;
            }
        };
        request.setShouldCache(false);
        HttpConnector.getInstance(getActivity()).addToRequestQueue(request);
    }

    private void checkLocationPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                if(checkLocationServices()){
                    getUserLocation();
                }
            }else{
                requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, RC_PERMISSION_LOCATION);
            }
        }else{
            //no need to show runtime permission stuff
            if(checkLocationServices()){
                getUserLocation();
            }
        }
    }

    private boolean checkLocationServices(){
        boolean enabled = true;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(getActivity() != null && getActivity().getSystemService(Context.LOCATION_SERVICE) != null){
                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                boolean isGpsProviderEnabled, isNetworkProviderEnabled;
                isGpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkProviderEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if(!isGpsProviderEnabled && !isNetworkProviderEnabled) {
                    enabled = false;
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(Utils.getString(getActivity(), R.string.location_required_title));
                    builder.setMessage(Utils.getString(getActivity(), R.string.location_required_message));
                    builder.setPositiveButton(Utils.getString(getActivity(), R.string.go_to_location_settings), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, RC_ACTIVITY_LOCATION_TURN_ON);
                        }
                    });
                    builder.setNegativeButton(Utils.getString(getActivity(), R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
            }
        }
        return enabled;
    }

    private void getUserLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if(location != null){
                            currentSpeed = location.getSpeed() * 2.23694/*3.6*/;
                            updateLocationUI();
                        }
                    }
                });

        final LocationRequest locationRequest = Utils.getRealTimeLocationRequest();

        LocationSettingsRequest.Builder locationBuilder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);


        SettingsClient client = LocationServices.getSettingsClient(getActivity());
        Task<LocationSettingsResponse> task = client.checkLocationSettings(locationBuilder.build()).addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...
                locationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        if (locationResult == null) {
                            return;
                        }
                        for (Location location : locationResult.getLocations()) {
                            if(location != null){
                                currentSpeed = location.getSpeed() * 2.23694/*3.6*/;
                                updateLocationUI();
                            }
                        }
                    };
                };

                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            }
        }).addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });

        /*MyLocation myLocation = new MyLocation();
        myLocation.getLocation(getActivity(), new MyLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                // Move the map's camera to the user location.
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 14));
            }
        });*/
    }

    @Override
    public void onResume(){
        super.onResume();
        if(vehicle != null) {
            checkLocationPermissions();
        }
        updateUI();
        startTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(fusedLocationClient != null && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
        stopTimer();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        //inflater.inflate(R.menu.menu_login, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if(id == R.id.action_add) {

        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_ACTIVITY_LOCATION_TURN_ON){
            if(checkLocationServices()){
                getUserLocation();
            }
        }else if(requestCode == RC_ACTIVITY_PERMISSION_TURN_ON){
            checkLocationPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode){
            case RC_PERMISSION_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if(grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //allowed
                    if(checkLocationServices()){
                        getUserLocation();
                    }
                }
                else{
                    //denied
                    //Utils.showToast(getActivity(), "You need to enable location permission", true);
                    // Should we show an explanation?
                    if (shouldShowRequestPermissionRationale("android.permission.ACCESS_FINE_LOCATION")) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle(getActivity().getResources().getString(R.string.location_permission_required_title))
                                .setMessage(getActivity().getResources().getString(R.string.location_permission_required_message))
                                .setPositiveButton(getActivity().getResources().getString(R.string.location_permission_allow), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, RC_PERMISSION_LOCATION);
                                    }
                                })
                                .show();
                    }else{
                        //Toast.makeText(getActivity(), "asked before and user denied", Toast.LENGTH_SHORT).show();
                        //Go to settings to enable permission
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(Utils.getString(getActivity(), R.string.location_permission_required_title));
                        builder.setMessage(Utils.getString(getActivity(), R.string.location_permission_required_message));
                        builder.setPositiveButton(Utils.getString(getActivity(), R.string.go_to_app_settings), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + Constants.PACKAGE_NAME));
                                //intent.addCategory(Intent.CATEGORY_DEFAULT);
                                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivityForResult(intent, RC_ACTIVITY_PERMISSION_TURN_ON);
                            }
                        });
                        builder.setNegativeButton(Utils.getString(getActivity(), R.string.exit), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getActivity().finish();
                            }
                        });
                        builder.show();
                    }
                }
            }
        }
    }
}
