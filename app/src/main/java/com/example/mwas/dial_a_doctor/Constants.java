package com.example.mwas.dial_a_doctor;

/**
 * Created by mwas on 10/2/17.
 */

public class Constants {
    public static final String USER_KEY = BuildConfig.USER_KEY;//tells the class where to find the api key
    public static final String BETTERDOC_BASE_URL = "https://api.betterdoctor.com/2016-03-01/doctors";
    public static final String BETTERDOC_SEARCH_PARAM = "first_name";
    public static final String FIREBASE_CHILD_DOCTORS = "doctors";
    public static final String PREFERENCE_SEARCH_PARAM = "searchParam";
}
