package com.example.mwas.dial_a_doctor;

import okhttp3.Callback;
import okhttp3.HttpUrl;

/**
 * Created by mwas on 10/3/17.
 */

public class BetterDocService {

    public static void findDoctor(String searchParam, Callback callback) {
        //construct the url using the necessary classes
        HttpUrl.Builder urlBuider = HttpUrl.parse(Constants.BETTERDOC_BASE_URL).newBuilder();
        urlBuider.addQueryParameter("key", Constants.API_KEY)
                .addQueryParameter(Constants.BETTERDOC_SEARCH_PARAM, searchParam);
        String url = urlBuider.build().toString();
    }
}
