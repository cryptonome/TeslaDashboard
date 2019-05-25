package com.joey.tesladashboard.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.joey.tesladashboard.R;
import com.joey.tesladashboard.Utils;
import com.joey.tesladashboard.entities.Vehicle;
import com.joey.tesladashboard.fragments.VehicleInfoFragment;

import java.util.List;

public class VehicleAdapter extends ArrayAdapter {
    Activity activity ;
    List<Vehicle> vehicles;
    ViewHolder vHolder = null;
    OnVehicleSelected callback;
    FragmentManager fragmentManager;

    public interface OnVehicleSelected{
        void onVehicleSelected(Vehicle vehicle);
    }

    public VehicleAdapter(Activity activity, List<Vehicle> vehicles, FragmentManager fragmentManager, OnVehicleSelected callback){
        super(activity, R.layout.list_item_vehicle, vehicles);
        this.activity = activity;
        this.vehicles = vehicles;
        this.callback = callback;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return vehicles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return vehicles.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_vehicle, null);
            vHolder = new ViewHolder();
            vHolder.nameTextView = rowView.findViewById(R.id.list_item_vehicle_name_textview);
            vHolder.statusTextView = rowView.findViewById(R.id.list_item_vehicle_status_textview);
            vHolder.timestampTextView = rowView.findViewById(R.id.list_item_order_timestamp_textview);
            vHolder.selectVehicleButton = rowView.findViewById(R.id.list_item_select_vehicle_button);
            vHolder.viewVehicleButton = rowView.findViewById(R.id.list_item_view_vehicle_button);
            vHolder.layout = rowView.findViewById(R.id.vehicle_item_layout);
            rowView.setTag(vHolder);
        }
        else{
            vHolder = (ViewHolder) rowView.getTag();
        }

        final Vehicle item = vehicles.get(position);

        vHolder.nameTextView.setText(""+item.getDisplayName());
        vHolder.statusTextView.setText(""+item.getState());
        vHolder.timestampTextView.setText(""+Utils.getTimeStringDateHoursMinutes(item.getVehicleState().getTimestamp()));

        /*vHolder.statusTextView.setText(""+item.getStatus());
        if(item.getStatus().equals(Constants.PUSHER_ORDER_STATUS_SEARCHING)){
            vHolder.statusTextView.setBackgroundResource(R.drawable.background_round_yellow);
        }else if(item.getStatus().equals(Constants.PUSHER_ORDER_STATUS_PREPARING) || item.getStatus().equals(Constants.PUSHER_ORDER_STATUS_PROCESSING)){
            vHolder.statusTextView.setBackgroundResource(R.drawable.background_round_orange_light);
        }else if(item.getStatus().equals(Constants.PUSHER_ORDER_STATUS_DELIVERING)){
            vHolder.statusTextView.setBackgroundResource(R.drawable.background_round_orange_light);
        }else if(item.getStatus().equals(Constants.PUSHER_ORDER_STATUS_DELIVERED)){
            vHolder.statusTextView.setBackgroundResource(R.drawable.background_round_green);
        }else if(item.getStatus().equals(Constants.PUSHER_ORDER_STATUS_CANCELLED)){
            vHolder.statusTextView.setBackgroundResource(R.drawable.background_round_red);
        }
        vHolder.timestampTextView.setText(Utils.getTimeStringDateHoursMinutes(item.getTimestamp()));*/

        vHolder.selectVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback != null){
                    callback.onVehicleSelected(item);
                }
            }
        });

        vHolder.viewVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction = Utils.setAnimations(fragmentTransaction, Utils.ANIMATION_TYPE_TRANSLATION);
                fragmentTransaction.replace(R.id.fragment_view, VehicleInfoFragment.newInstance(item), "vehicleInfoFragment");
                fragmentTransaction.addToBackStack("vehicleInfoFragment");
                fragmentTransaction.commit();
            }
        });

        vHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction = Utils.setAnimations(fragmentTransaction, Utils.ANIMATION_TYPE_TRANSLATION);
                fragmentTransaction.replace(R.id.fragment_view, VehicleInfoFragment.newInstance(item), "vehicleInfoFragment");
                fragmentTransaction.addToBackStack("vehicleInfoFragment");
                fragmentTransaction.commit();*/
            }
        });

        return rowView;
    }

    public static class ViewHolder{
        TextView nameTextView, statusTextView, timestampTextView;
        Button selectVehicleButton, viewVehicleButton;
        CardView layout;
    }
}
