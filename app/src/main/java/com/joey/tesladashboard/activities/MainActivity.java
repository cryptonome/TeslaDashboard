package com.joey.tesladashboard.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;
import com.google.android.material.navigation.NavigationView;
import com.joey.tesladashboard.MySettings;
import com.joey.tesladashboard.R;
import com.joey.tesladashboard.Utils;
import com.joey.tesladashboard.fragments.DashboardFragment;
import com.joey.tesladashboard.fragments.PrivacyPolicyFragment;
import com.joey.tesladashboard.fragments.VehicleSelectionFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static MainActivity mInstance;

    private static Toolbar toolbar;
    private static TextView mTitle;
    private static ImageView mIcon;

    private static DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout headerLayout;
    RelativeLayout changeVehicleHeaderLayout, privacyPolicyHeaderLayout, logoutHeaderLayout;
    CircleImageView profileImageView;
    TextView userNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInstance = this;

        drawerLayout = findViewById(R.id.drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_drawer_icon_white);

        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.app_name));
        mTitle.setTextColor(getResources().getColor(R.color.whiteColor));
        mIcon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);

        if(MySettings.getActiveUser() == null){
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction = Utils.setAnimations(fragmentTransaction, Utils.ANIMATION_TYPE_TRANSLATION);
        fragmentTransaction.replace(R.id.fragment_view, DashboardFragment.newInstance(), "dashboardFragment");
        fragmentTransaction.commit();

        navigationView = findViewById(R.id.nav_view);
        headerLayout = (LinearLayout) navigationView.getHeaderView(0);

        profileImageView = headerLayout.findViewById(R.id.profile_picture_imageview);
        userNameTextView = headerLayout.findViewById(R.id.username_textview);

        if(MySettings.getActiveUser() != null){
            userNameTextView.setText(""+MySettings.getActiveUser().getEmail());
        }

        changeVehicleHeaderLayout = headerLayout.findViewById(R.id.change_vehicle_layout);
        changeVehicleHeaderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                FragmentManager fragmentManager = getMainFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction = Utils.setAnimations(fragmentTransaction, Utils.ANIMATION_TYPE_TRANSLATION);
                fragmentTransaction.replace(R.id.fragment_view, VehicleSelectionFragment.newInstance(), "vehicleSelectionFragment");
                fragmentTransaction.addToBackStack("vehicleSelectionFragment");
                fragmentTransaction.commit();
            }
        });

        privacyPolicyHeaderLayout = headerLayout.findViewById(R.id.privacy_policy_layout);
        privacyPolicyHeaderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                FragmentManager fragmentManager = getMainFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction = Utils.setAnimations(fragmentTransaction, Utils.ANIMATION_TYPE_TRANSLATION);
                fragmentTransaction.replace(R.id.fragment_view, PrivacyPolicyFragment.newInstance(), "privacyPolicyFragment");
                fragmentTransaction.addToBackStack("privacyPolicyFragment");
                fragmentTransaction.commit();
            }
        });

        logoutHeaderLayout = headerLayout.findViewById(R.id.logout_layout);
        logoutHeaderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(mInstance)
                        .setTitle(getResources().getString(R.string.logout))
                        .setMessage(getResources().getString(R.string.logout_message))
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MySettings.setActiveUser(null);
                                MySettings.setAppFirstStart(true);

                                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(loginIntent);
                                finish();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

        Tovuti.from(this).monitor(new Monitor.ConnectivityListener(){
            @Override
            public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast){
                // Handle the connection...
                if(isConnected){
                    // Do something you haz internet!


                    //hide NoInternetDialogFragment if shown
                    if(getSupportFragmentManager().findFragmentByTag("noInternetDialogFragment") != null){
                        /*NoInternetDialogFragment noInternetDialogFragment = (NoInternetDialogFragment) getSupportFragmentManager().findFragmentByTag("noInternetDialogFragment");
                        noInternetDialogFragment.dismiss();*/
                    }

                    //refresh socket in HomeFragment
                    if(getSupportFragmentManager().findFragmentByTag("dashboardFragment") != null){
                        /*DashboardFragment dashboardFragment = (DashboardFragment) getSupportFragmentManager().findFragmentByTag("dashboardFragment");
                        dashboardFragment.initSocket();*/
                    }
                }else{
                    // Do something you no haz internet!

                    /*Intent noInternetIntent = new Intent(MainActivity.this, NoInternetActivity.class);
                    startActivity(noInternetIntent);
                    finish();*/

                    // DialogFragment.show() will take care of adding the fragment
                    // in a transaction.  We also want to remove any currently showing
                    // dialog, so make our own transaction and take care of that here.
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment prev = getSupportFragmentManager().findFragmentByTag("noInternetDialogFragment");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);

                    // Create and show the dialog.
                    /*NoInternetDialogFragment fragment = NoInternetDialogFragment.newInstance();
                    fragment.setCancelable(false);
                    fragment.show(ft, "noInternetDialogFragment");*/
                }
            }
        });
    }

    public static void setActionBarTitle(String title, int colorID){
        mTitle.setText(title);
        mTitle.setTextColor(colorID);
    }

    public static void setActionBarIcon(int resourceID){
        if(resourceID == 0){
            mIcon.setVisibility(View.INVISIBLE);
            boolean backEnabled = false;
            mIcon.setTag(backEnabled);
        }else{
            mIcon.setVisibility(View.VISIBLE);
            mIcon.setImageResource(resourceID);

            /*if(resourceID == R.drawable.arrow_left_icon_white){
                boolean backEnabled = true;
                mIcon.setTag(backEnabled);
            }else{
                boolean backEnabled = false;
                mIcon.setTag(backEnabled);
            }*/
        }
    }

    public static Toolbar getToolbar(){
        return toolbar;
    }

    public static FragmentManager getMainFragmentManager(){
        return mInstance.getSupportFragmentManager();
    }

    public static void openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static MainActivity getInstance(){
        return mInstance;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
