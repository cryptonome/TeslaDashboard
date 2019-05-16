package com.joey.tesladashboard.entities;

import com.google.gson.annotations.SerializedName;
import com.joey.tesladashboard.Constants;

public class User {
    @SerializedName(Constants.PARAMETER_USER_ID)
    private long id;
    @SerializedName(Constants.PARAMETER_USER_NAME)
    private String name;
    @SerializedName(Constants.PARAMETER_USER_EMAIL)
    private String email;
    @SerializedName(Constants.PARAMETER_USER_MOBILE)
    private String phoneNumber;
    @SerializedName(Constants.PARAMETER_API_TOKEN)
    private String apiToken;
    @SerializedName(Constants.PARAMETER_USER_PASSWORD)
    private String password;
    @SerializedName(Constants.PARAMETER_USER_USERNAME)
    private String userName;

    public User(){
        this.id = 0;
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
        this.apiToken = "";
        this.password = "";
        this.userName = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
