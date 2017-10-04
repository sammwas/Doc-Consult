package com.example.mwas.dial_a_doctor.models;

import java.util.ArrayList;

/**
 * Created by mwas on 10/3/17.
 */

public class Doctor {
    private String mFirstName;
    private String mLastName;
    private String mImageUrl;
    private String mBio;
    private ArrayList<String> mPhones = new ArrayList<>();
    private String mSpeciality;

    public Doctor(String firstName, String lastName, String imageUrl, String bio) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mImageUrl = imageUrl;
        this.mBio = bio;
//        this.mPhones = phones;
//        this.mSpeciality = speciality;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getBio() {
        return mBio;
    }

    public ArrayList<String > getPhones() {
        return mPhones;
    }

//    public String getSpeciality() {
//        return mSpeciality;
//    }
}
