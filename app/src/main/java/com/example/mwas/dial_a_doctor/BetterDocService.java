package com.example.mwas.dial_a_doctor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by mwas on 10/3/17.
 */

public class BetterDocService {

    public static void findDoctor(String searchParam, Callback callback) {
        //construct the url using the necessary classes
        HttpUrl.Builder urlBuider = HttpUrl.parse(Constants.BETTERDOC_BASE_URL).newBuilder();
        urlBuider.addQueryParameter("user_key", Constants.USER_KEY)
                .addQueryParameter(Constants.BETTERDOC_SEARCH_PARAM, searchParam);
        String url = urlBuider.build().toString();

        //create a client
        OkHttpClient client = new OkHttpClient();

        //make a request
        Request request = new Request.Builder()
                          .url(url)
                          .build();
        //calling a request asynchronously
        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
