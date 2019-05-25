package com.joey.tesladashboard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.joey.tesladashboard.MySettings;
import com.joey.tesladashboard.R;
import com.joey.tesladashboard.Utils;
import com.joey.tesladashboard.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    private static final String TAG = DashboardFragment.class.getSimpleName();

    Button selectVehicleButton;

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

        selectVehicleButton = view.findViewById(R.id.select_vehicle_button);

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

        //TODO connect to Tesla Stream API to get live vehicle info

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
        //TODO update UI from the Stream API

        if(MySettings.getCurrentVehicle() == null){
            selectVehicleButton.setVisibility(View.VISIBLE);
        }else{
            selectVehicleButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
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
