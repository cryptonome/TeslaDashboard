package com.joey.tesladashboard.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joey.tesladashboard.R;
import com.joey.tesladashboard.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private static Toolbar toolbar;
    private static TextView mTitle;
    private static ImageView mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.app_name));
        mTitle.setTextColor(getResources().getColor(R.color.whiteColor));
        mIcon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction = Utils.setAnimations(fragmentTransaction, Utils.ANIMATION_TYPE_TRANSLATION);
        fragmentTransaction.replace(R.id.fragment_view, LoginFragment.newInstance(), "loginFragment");
        fragmentTransaction.commit();
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
}
