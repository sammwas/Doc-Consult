package com.example.mwas.dial_a_doctor.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.mwas.dial_a_doctor.R;
import com.example.mwas.dial_a_doctor.adapters.DoctorListAdapter;
import com.example.mwas.dial_a_doctor.models.Doctor;
import com.example.mwas.dial_a_doctor.services.BetterDocService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DoctorsActivity extends AppCompatActivity {
    public ArrayList<Doctor> mDoctors = new ArrayList<>();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private DoctorListAdapter mAdapter;

    public ArrayList<Doctor> mRestaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        ButterKnife.bind(this);
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
                mDoctors = betterDocService.processResults(response);
                DoctorsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new DoctorListAdapter(getApplicationContext(), mRestaurants);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(DoctorsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }
        });
    }
}
