package com.example.mwas.dial_a_doctor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mwas.dial_a_doctor.R;
import com.example.mwas.dial_a_doctor.models.Doctor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.mwas.dial_a_doctor.R.drawable.doctor;

/**
 * Created by mwas on 10/3/17.
 */

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> {
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private Context mContext;

    public DoctorListAdapter(Context context, ArrayList<Doctor> doctors) {
        mDoctors = doctors;
        mContext = context;
    }

    @Override
    public DoctorListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.ViewHolder holder, int position) {
        holder.bindDoctor(mDoctors.get(position));
    }

    @Override
    public int getItemCount() {
        return mDoctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.doctorImageView) ImageView mDoctorImageView;
        @Bind(R.id.firstNameTextView) TextView mFirstNameTextView;
        @Bind(R.id.lastNameTextView) TextView mLastNameTextView;
        @Bind(R.id.bioTextView) TextView mBioTextView;

        private Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
        }

        public void bindDoctor(Doctor doctor) {
            Picasso.with(mContext).load(doctor.getImageUrl()).into(mDoctorImageView);
            mFirstNameTextView.setText(doctor.getFirstName());
            mLastNameTextView.setText(doctor.getLastName());
            mBioTextView.setText(doctor.getBio());
        }

    }
}
