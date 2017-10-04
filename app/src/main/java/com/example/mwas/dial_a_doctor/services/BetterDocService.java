package com.example.mwas.dial_a_doctor.services;

import com.example.mwas.dial_a_doctor.Constants;
import com.example.mwas.dial_a_doctor.models.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public ArrayList<Doctor> processResults(Response response) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try{
           String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject betterDocJSON = new JSONObject(jsonData);
                JSONArray dataJSON = betterDocJSON.getJSONArray("data");
                for(int i = 0; i<dataJSON.length(); i++) {
                    JSONObject doctorJSON = dataJSON.getJSONObject(i);
                    String firstName = doctorJSON.getJSONObject("profile").getString("first_name");
                    String lastName = doctorJSON.getJSONObject("profile").getString("last_name");
                    String imageUrl = doctorJSON.getJSONObject("profile").getString("image_url");
                    String bio = doctorJSON.getJSONObject("profile").getString("bio");

//                    ArrayList<String> phones = new ArrayList<>();
//                    JSONArray phonesJSON = doctorJSON.getJSONArray("phones");
//
//                    for (int y = 0; y < phonesJSON.length(); y++) {
//                        phones.add(phonesJSON.get(y).toString());
//                    }
//                    String specialty = doctorJSON.getJSONArray("specialties").

                    Doctor doctor = new Doctor(firstName, lastName, imageUrl, bio);
                    doctors.add(doctor);
                }

            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
       return doctors;
    }
}
