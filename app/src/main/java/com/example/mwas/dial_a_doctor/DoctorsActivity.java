package com.example.mwas.dial_a_doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DoctorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        Intent intent = getIntent();
        String searchParam = intent.getStringExtra("searchParam");
        getDoctors(searchParam);
    }

    //method that receives the response from the api call
    private void getDoctors(String searchParam) {
        final BetterDocService betterDocService = new BetterDocService();
        betterDocService.findDoctor(searchParam, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.v("error","something went wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String jsonData = response.body().string();
                    Log.v("URL", jsonData);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
