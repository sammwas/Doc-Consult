package com.example.mwas.dial_a_doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.inputField) EditText mInputField;
    @Bind(R.id.searchButton) Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);
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
    }
}
