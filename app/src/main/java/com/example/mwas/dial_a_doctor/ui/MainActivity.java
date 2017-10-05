package com.example.mwas.dial_a_doctor.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mwas.dial_a_doctor.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.inputField) EditText mInputField;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.savedDoctorsButton) Button mSavedDoctorsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);
        mSavedDoctorsButton.setOnClickListener(this);
    }

    //override the onlick method that is usually contained in the view.onClickListener interface
    @Override
    public void onClick(View v){
        if(v == mSearchButton) {
            String searchParam = mInputField.getText().toString();
            Intent intent = new Intent(MainActivity.this,DoctorsActivity.class);
            intent.putExtra("searchParam", searchParam);
            startActivity(intent);
        }
        if(v == mSavedDoctorsButton) {
            Intent intent = new Intent(MainActivity.this, BookedDoctorListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
