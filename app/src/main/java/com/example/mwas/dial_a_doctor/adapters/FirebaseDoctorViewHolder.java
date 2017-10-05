package com.example.mwas.dial_a_doctor.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mwas.dial_a_doctor.Constants;
import com.example.mwas.dial_a_doctor.R;
import com.example.mwas.dial_a_doctor.models.Doctor;
import com.example.mwas.dial_a_doctor.ui.DoctorDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by mwas on 10/4/17.
 */

public class FirebaseDoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseDoctorViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindDoctor(Doctor doctor) {
        ImageView doctorImageView = (ImageView) mView.findViewById(R.id.doctorImageView);
        TextView firstNameTextView = (TextView) mView.findViewById(R.id.firstNameTextView);
        TextView lastNameTextView = (TextView) mView.findViewById(R.id.lastNameTextView);
        TextView bioTextView = (TextView) mView.findViewById(R.id.bioTextView);

        Picasso.with(mContext).load(doctor.getImageUrl()).into(doctorImageView);
        firstNameTextView.setText(doctor.getFirstName());
        lastNameTextView.setText(doctor.getLastName());
        bioTextView.setText(doctor.getBio());

    }


    @Override
    public void onClick(View view) {
        final ArrayList<Doctor> doctors = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DOCTORS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    doctors.add(snapshot.getValue(Doctor.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, DoctorDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("doctors", Parcels.wrap(doctors));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
