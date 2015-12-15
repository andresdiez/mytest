package com.diez.andres.mytest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Screen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getdata());
        setSupportActionBar(toolbar);


    }
    public String getdata(){
        String sear;
        Bundle extras = getIntent().getExtras();
        sear= extras.getString("string");
        return sear;
    }
    public java.lang.String getData() {
        String sear;
        Bundle extras = getIntent().getExtras();
        sear= extras.getString("string");
        return sear;
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            getSupportActionBar().setTitle(getData());
        } else {
            super.onBackPressed();
        }
    }

}
