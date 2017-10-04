package com.example.mwas.dial_a_doctor.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by mwas on 10/3/17.
 */

@Parcel
public class Doctor {
    String firstName;
    String lastName;
    String imageUrl;
    String bio;
    ArrayList<String> mPhones = new ArrayList<>();
    String mSpeciality;

    public Doctor(String firstName, String lastName, String imageUrl, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.bio = bio;
//        thimPhones = phones;
//        this.mSpeciality = speciality;
    }
    public Doctor() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getImageUrl() { return imageUrl; }

    public String getBio() {
        return bio;
    }

//    public ArrayList<String > getPhones() {
//        return mPhones;
//    }

//    public String getSpeciality() {
//        return mSpeciality;
//    }
}
