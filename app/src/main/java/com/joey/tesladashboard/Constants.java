package com.joey.tesladashboard;

public class Constants {
    public static final String PACKAGE_NAME = "com.joey.tesladashboard";

    //url constants
    private static final String DOMAIN_URl = "https://owner-api.teslamotors.com";
    public static final String LOGIN_URL = DOMAIN_URl + "/oauth/token";
    public static final String GET_VEHICLES_URL = DOMAIN_URl + "/api/1/vehicles";
    public static final String GET_VEHICLE_INFO_URL = DOMAIN_URl + "/api/1/vehicles/%d/vehicle_data";


    //general parameters
    public static final String PARAMETER_HEADER_AUTH = "Authorization";
    public static final String PARAMETER_HEADER_AUTH_VALUE = "Bearer %s";
    public static final String PARAMETER_GRANT_TYPE = "grant_type";
    public static final String PARAMETER_GRANT_TYPE_PASSWORD = "password";
    public static final String PARAMETER_GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    public static final String PARAMETER_HEADER_USER_AGENT = "User-Agent";
    public static final String PARAMETER_HEADER_USER_AGENT_VALUE = "Mozilla/5.0 (Linux; Android 9.0.0; VS985 4G Build/LRX21Y; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/58.0.3029.83 Mobile Safari/537.36";
    public static final String PARAMETER_HEADER_TESLA_USER_AGENT = "X-Tesla-User-Agent";
    public static final String PARAMETER_HEADER_TESLA_USER_AGENT_VALUE = "TeslaApp/3.4.4-350/fad4a582e/android/9.0.0";

    public static final String PARAMETER_CLIENT_ID = "client_id";
    public static final String PARAMETER_CLIENT_SECRET = "client_secret";
    public static final String TESLA_CLIENT_ID = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384";
    public static final String TESLA_CLIENT_SECRET = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3";


    public static final String PARAMETER_ACCESS_TOKEN = "access_token";
    public static final String PARAMETER_REFRESH_TOKEN = "refresh_token";


    public static final String PARAMETER_LANGUAGE = "lang";
    public static final String PARAMETER_APP_VERSION = "app_version";
    public static final String APP_VERSION = "1.0.0";
    public static final String PARAMETER_OS = "os";
    public static final String PARAMETER_OS_IOS = "ios";
    public static final String PARAMETER_OS_ANDROID = "android";
    public static final String PARAMETER_MODEL = "model";
    public static final String MODEL = "Emulator";


    public static final String PARAMETER_COUNT = "count";
    public static final String PARAMETER_RESPONSE = "response";


    //user parameters
    public static final String PARAMETER_USER_ID = "id";
    public static final String PARAMETER_USER_NAME = "name";
    public static final String PARAMETER_USER_USERNAME = "username";
    public static final String PARAMETER_USER_EMAIL = "email";
    public static final String PARAMETER_USER_MOBILE = "phone";
    public static final String PARAMETER_USER_PASSWORD = "password";



    public static final String PARAMETER_STATUS = "status";
    public static final String PARAMETER_DATA = "data";

    public static final String STATUS_ERROR_AUTH_REQUIRED = "unValidToken";
    public static final String STATUS_SUCCESS = "success";

    //notification channel constant, only for api26+
    public static final String CHANNEL_ID = "44";
}
