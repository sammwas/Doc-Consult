package com.example.mwas.dial_a_doctor.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mwas.dial_a_doctor.models.Doctor;
import com.example.mwas.dial_a_doctor.ui.DoctorDetailFrament;

import java.util.ArrayList;

/**
 * Created by mwas on 10/4/17.
 */

public class DoctorPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Doctor> mDoctors;

    public DoctorPagerAdapter(FragmentManager fm, ArrayList<Doctor> doctors) {
        super(fm);
        mDoctors = doctors;
    }

    @Override
    public Fragment getItem(int position) {
        return DoctorDetailFrament.newInstance(mDoctors.get(position));
    }

    @Override
    public int getCount() {
        return mDoctors.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDoctors.get(position).getFirstName();
    }
}
