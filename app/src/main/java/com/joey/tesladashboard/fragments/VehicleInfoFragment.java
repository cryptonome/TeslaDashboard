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
import com.joey.tesladashboard.entities.Vehicle;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VehicleInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehicleInfoFragment extends Fragment {
    private static final String TAG = VehicleInfoFragment.class.getSimpleName();


    private Vehicle vehicle;

    TextView dummyTextView;

    public VehicleInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment VehicleInfoFragment.
     */
    public static VehicleInfoFragment newInstance(Vehicle vehicle) {
        VehicleInfoFragment fragment = new VehicleInfoFragment();
        fragment.setVehicle(vehicle);
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
        View view = inflater.inflate(R.layout.fragment_vehicle_info, container, false);
        if(vehicle != null){
            MainActivity.setActionBarTitle(vehicle.getDisplayName(), getResources().getColor(R.color.whiteColor));
        }else {
            MainActivity.setActionBarTitle(Utils.getString(getActivity(), R.string.select_vehicle), getResources().getColor(R.color.whiteColor));
        }
        MainActivity.setActionBarIcon(R.drawable.ic_arrow_back_white_36dp);
        setHasOptionsMenu(true);

        dummyTextView = view.findViewById(R.id.dummy_textview);

        //connect to Tesla API to get detailed vehicle info
        getVehicleInfo();

        ImageView actionButton = MainActivity.getToolbar().findViewById(R.id.toolbar_icon);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void updateUI(){
        dummyTextView.setText(""+vehicle.toString());
    }

    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    private void getVehicleInfo(){
        String url = String.format(Constants.GET_VEHICLE_INFO_URL, vehicle.getId());

        dummyTextView.setText("loading: " + url + "\n\n");

        Utils.showLoading(getActivity());

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
                        vehicle = gson.fromJson(jsonObject.toString(), type);

                        //update UI
                        updateUI();
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
                            getVehicleInfo();
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
