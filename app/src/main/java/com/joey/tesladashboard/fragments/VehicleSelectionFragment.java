package com.joey.tesladashboard.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joey.tesladashboard.Constants;
import com.joey.tesladashboard.HttpConnector;
import com.joey.tesladashboard.MySettings;
import com.joey.tesladashboard.R;
import com.joey.tesladashboard.Utils;
import com.joey.tesladashboard.activities.MainActivity;
import com.joey.tesladashboard.adapters.VehicleAdapter;
import com.joey.tesladashboard.entities.Vehicle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VehicleSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehicleSelectionFragment extends Fragment {
   private static final String TAG = VehicleSelectionFragment.class.getSimpleName();


    ListView vehiclesListView;
    VehicleAdapter adapter;
    List<Vehicle> vehicles;
    TextView noVehiclesTextView;

    public VehicleSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment VehicleSelectionFragment.
     */
    public static VehicleSelectionFragment newInstance() {
        VehicleSelectionFragment fragment = new VehicleSelectionFragment();
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
        View view = inflater.inflate(R.layout.fragment_vehicle_selection, container, false);
        MainActivity.setActionBarTitle(Utils.getString(getActivity(), R.string.select_vehicle), getResources().getColor(R.color.whiteColor));
        MainActivity.setActionBarIcon(R.drawable.ic_arrow_back_white_36dp);
        setHasOptionsMenu(true);

        noVehiclesTextView = view.findViewById(R.id.no_vehicles_available);
        vehicles = new ArrayList<>();
        vehiclesListView = view.findViewById(R.id.vehicles_listview);
        adapter = new VehicleAdapter(getActivity(), vehicles, getFragmentManager(), new VehicleAdapter.OnVehicleSelected() {
            @Override
            public void onVehicleSelected(Vehicle vehicle) {
                MySettings.setCurrentVehicle(vehicle);
                //Utils.showToast(getActivity(), Utils.getString(getActivity(), R.string.vehicle_selected), true);
                getFragmentManager().popBackStack();
            }
        });
        vehiclesListView.setAdapter(adapter);

        //connect to Tesla API to get list of owned vehicles
        getVehicles();

        ImageView actionButton = MainActivity.getToolbar().findViewById(R.id.toolbar_icon);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void getVehicles(){
        String url = Constants.GET_VEHICLES_URL;

        Utils.showLoading(getActivity());

        Log.d(TAG, "getVehicles URL: " + url);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "getVehicles response: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject != null) {
                        if(jsonObject.has(Constants.PARAMETER_COUNT)){
                            int count = jsonObject.getInt(Constants.PARAMETER_COUNT);
                            if(count >= 1){
                                noVehiclesTextView.setVisibility(View.GONE);

                                if(jsonObject.has(Constants.PARAMETER_RESPONSE)){
                                    JSONArray data = jsonObject.getJSONArray(Constants.PARAMETER_RESPONSE);
                                    int numberOfVehicles = data.length();
                                    Gson gson = Utils.getGson();
                                    Type type = new TypeToken<Vehicle>() {}.getType();
                                    Vehicle vehicle;
                                    for (int x = 0; x < numberOfVehicles; x++) {
                                        JSONObject vehicleObject = data.getJSONObject(x);
                                        vehicle = gson.fromJson(vehicleObject.toString(), type);

                                        if (!vehicles.contains(vehicle)) {
                                            vehicles.add(vehicle);
                                        }
                                    }

                                    adapter.notifyDataSetChanged();
                                }
                            }else{
                                noVehiclesTextView.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    Utils.dismissLoading();
                } catch (JSONException e) {
                    Log.d(TAG, "Json Exception: " + e.getMessage());
                } catch (IllegalStateException e) {
                    Log.d(TAG, "Error parsing GSON response.");
                    Utils.showToast(getActivity(), getResources().getString(R.string.server_connection_error), true);
                }

                Utils.dismissLoading();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.dismissLoading();
                Log.d(TAG, "Volley Error: " + error.getMessage());
                if(error.networkResponse != null && error.networkResponse.statusCode == 401){
                    Utils.refreshToken(getActivity(), new Utils.OnTokenRefreshed() {
                        @Override
                        public void onTokenRefreshed() {
                            getVehicles();
                        }
                    });
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

}
