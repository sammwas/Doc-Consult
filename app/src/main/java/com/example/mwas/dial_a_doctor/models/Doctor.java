package com.example.mwas.dial_a_doctor.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by mwas on 10/3/17.
 */

@Parcel
public class Doctor {
    String mFirstName;
    String mLastName;
    String mImageUrl;
    String mBio;
    ArrayList<String> mPhones = new ArrayList<>();
    String mSpeciality;

    public Doctor(String firstName, String lastName, String imageUrl, String bio) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mImageUrl = imageUrl;
        this.mBio = bio;
//        this.mPhones = phones;
//        this.mSpeciality = speciality;
    }
    public Doctor() {}

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getImageUrl() { return mImageUrl; }

    public String getBio() {
        return mBio;
    }

//    public ArrayList<String > getPhones() {
//        return mPhones;
//    }

//    public String getSpeciality() {
//        return mSpeciality;
//    }
}
