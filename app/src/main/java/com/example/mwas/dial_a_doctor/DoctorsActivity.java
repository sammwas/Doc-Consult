package com.example.mwas.dial_a_doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    @Bind(R.id.listViewDoctor) ListView mListViewDoctor;

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
                       String[] doctorsNames = new String[mDoctors.size()];
                        for(int i = 0; i < doctorsNames.length; i++) {
                            doctorsNames[i] = mDoctors.get(i).getFirstName();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(DoctorsActivity.this, android.R.layout.simple_list_item_1, doctorsNames);
                        mListViewDoctor.setAdapter(adapter);
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
}
