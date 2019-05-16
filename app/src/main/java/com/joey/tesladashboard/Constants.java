package com.joey.tesladashboard;

public class Constants {
    public static final String PACKAGE_NAME = "com.joey.tesladashboard";

    //url constants
    private static final String DOMAIN_URl = "https://apps.bsocial-eg.com/mongez_laravel/public/api";
    public static final String LOGIN_URL = DOMAIN_URl + "/login";


    //general parameters
    public static final String PARAMETER_LANGUAGE = "lang";
    public static final String PARAMETER_HEADER_USER_ID = "userId";
    public static final String PARAMETER_APP_VERSION = "app_version";

    public static final String PARAMETER_API_TOKEN = "jwt";

    //user parameters
    public static final String PARAMETER_USER_ID = "id";
    public static final String PARAMETER_USER_NAME = "name";
    public static final String PARAMETER_USER_USERNAME = "username";
    public static final String PARAMETER_USER_EMAIL = "email";
    public static final String PARAMETER_USER_MOBILE = "phone";
    public static final String PARAMETER_USER_PASSWORD = "password";


    public static final String STATUS_ERROR_AUTH_REQUIRED = "unValidToken";
    public static final String STATUS_SUCCESS = "success";

    //notification channel constant, only for api26+
    public static final String CHANNEL_ID = "44";
}
