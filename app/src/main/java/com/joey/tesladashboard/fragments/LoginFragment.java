package com.joey.tesladashboard.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.joey.tesladashboard.Constants;
import com.joey.tesladashboard.HttpConnector;
import com.joey.tesladashboard.MySettings;
import com.joey.tesladashboard.R;
import com.joey.tesladashboard.Utils;
import com.joey.tesladashboard.activities.LoginActivity;
import com.joey.tesladashboard.activities.MainActivity;
import com.joey.tesladashboard.entities.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private static final String TAG = LoginFragment.class.getSimpleName();


    Button loginButton;
    EditText emailEditText, passwordEditText;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        LoginActivity.setActionBarTitle(Utils.getString(getActivity(), R.string.login), getResources().getColor(R.color.whiteColor));
        LoginActivity.setActionBarIcon(0);
        setHasOptionsMenu(true);

        emailEditText = view.findViewById(R.id.email_edittext);
        passwordEditText = view.findViewById(R.id.password_edittext);
        loginButton = view.findViewById(R.id.login_button);

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(validateInputs()) {
                    User user = new User();
                    user.setEmail(emailEditText.getText().toString());
                    //user.setPassword(Utils.getMD5(Constants.PASSWORD_SALT + passwordEditText.getText().toString()));
                    user.setPassword(passwordEditText.getText().toString());

                    login(user);
                    return true;
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInputs()) {
                    User user = new User();
                    user.setEmail(emailEditText.getText().toString());
                    //user.setPassword(Utils.getMD5(Constants.PASSWORD_SALT + passwordEditText.getText().toString()));
                    user.setPassword(passwordEditText.getText().toString());

                    login(user);
                }
            }
        });


        return view;
    }

    private boolean validateInputs(){
        boolean inputsValid = true;

        if(emailEditText.getText().toString() == null || !Utils.isEmailValid(emailEditText.getText().toString())){
            inputsValid = false;
            emailEditText.setError(Utils.getString(getActivity(), R.string.email_error));
        }

        if(passwordEditText.getText().toString() == null || passwordEditText.getText().toString().length() < 4){
            inputsValid = false;
            passwordEditText.setError(Utils.getString(getActivity(), R.string.password_error));
        }

        return inputsValid;
    }

    private void login(final User user){
        String url = Constants.LOGIN_URL;

        Utils.showLoading(getActivity());

        Log.d(TAG, "login URL: " + url);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "login response: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject != null) {
                        if(jsonObject.has(Constants.PARAMETER_ACCESS_TOKEN)){
                            String accessToken = jsonObject.getString(Constants.PARAMETER_ACCESS_TOKEN);
                            String refreshToken = jsonObject.getString(Constants.PARAMETER_REFRESH_TOKEN);

                            user.setAccessToken(accessToken);
                            user.setRefreshToken(refreshToken);

                            MySettings.setActiveUser(user);

                            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                            startActivity(mainIntent);
                            getActivity().finish();
                        }else{
                            Utils.showToast(getActivity(), Utils.getString(getActivity(), R.string.email_or_password_error), true);
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
                    Utils.showToast(getActivity(), Utils.getString(getActivity(), R.string.email_or_password_error), true);
                }else{
                    Utils.showToast(getActivity(), getResources().getString(R.string.server_connection_error), true);
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.PARAMETER_GRANT_TYPE, Constants.PARAMETER_GRANT_TYPE_PASSWORD);
                params.put(Constants.PARAMETER_CLIENT_ID, Constants.TESLA_CLIENT_ID);
                params.put(Constants.PARAMETER_CLIENT_SECRET, Constants.TESLA_CLIENT_SECRET);
                params.put(Constants.PARAMETER_USER_EMAIL, user.getEmail());
                params.put(Constants.PARAMETER_USER_PASSWORD, user.getPassword());

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.PARAMETER_HEADER_USER_AGENT, Constants.PARAMETER_HEADER_USER_AGENT_VALUE);
                params.put(Constants.PARAMETER_HEADER_TESLA_USER_AGENT, Constants.PARAMETER_HEADER_TESLA_USER_AGENT_VALUE);

                return params;
            }
        };
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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
