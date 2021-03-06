package com.example.mwas.dial_a_doctor.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.mwas.dial_a_doctor.Constants;
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

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentEntry;

    public ArrayList<Doctor> mDoctors = new ArrayList<>();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private DoctorListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String searchParam = intent.getStringExtra("searchParam");
        getDoctors(searchParam);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentEntry = mSharedPreferences.getString(Constants.PREFERENCE_SEARCH_PARAM, null);

        if (mRecentEntry != null) {
            getDoctors(mRecentEntry);
        }
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
                        mAdapter = new DoctorListAdapter(getApplicationContext(), mDoctors);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(DoctorsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for(Doctor doctor:mDoctors){
                            Log.v("FirstName",doctor.getFirstName());
                            Log.v("LastName",doctor.getLastName());
                            Log.v("ImageUrl",doctor.getImageUrl());
                            Log.v("Bio",doctor.getBio());
                        }

                    }
                });
            }
        });
    }

    private void addToSharedPreferences(String searchParam) {
        mEditor.putString(Constants.PREFERENCE_SEARCH_PARAM, searchParam).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getDoctors(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
