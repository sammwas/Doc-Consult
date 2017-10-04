package com.example.mwas.dial_a_doctor.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mwas.dial_a_doctor.Constants;
import com.example.mwas.dial_a_doctor.R;
import com.example.mwas.dial_a_doctor.models.Doctor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorDetailFrament extends Fragment implements View.OnClickListener {
    @Bind(R.id.doctorImageView) ImageView mDoctorImageView;
    @Bind(R.id.firstNameTextView) TextView mFirstNameTextView;
    @Bind(R.id.lastNameTextView) TextView mLastNameTextView;
    @Bind(R.id.bioTextView) TextView mBioTextView;
    @Bind(R.id.saveDoctorsButton) Button mSaveDoctorsButton;

    private Doctor mDoctor;

    public static DoctorDetailFrament newInstance(Doctor doctor) {
        DoctorDetailFrament doctorDetailFrament = new DoctorDetailFrament();
        Bundle args = new Bundle();
        args.putParcelable("doctor", Parcels.wrap(doctor));
        doctorDetailFrament.setArguments(args);
        return doctorDetailFrament;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDoctor = Parcels.unwrap(getArguments().getParcelable("doctor"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_detail_frament, container, false);
        ButterKnife.bind(this,view);
        Picasso.with(view.getContext()).load(mDoctor.getImageUrl()).into(mDoctorImageView);
        mFirstNameTextView.setText(mDoctor.getFirstName());
        mLastNameTextView.setText(mDoctor.getLastName());
        mBioTextView.setText(mDoctor.getBio());
        mSaveDoctorsButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == mSaveDoctorsButton) {
            DatabaseReference doctorRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_DOCTORS);
            doctorRef.push().setValue(mDoctor);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
