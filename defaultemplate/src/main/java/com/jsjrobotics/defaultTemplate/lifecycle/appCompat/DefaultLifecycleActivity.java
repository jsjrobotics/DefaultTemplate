package com.jsjrobotics.defaultTemplate.lifecycle.appCompat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces.ILifecycleActivity;

public class DefaultLifecycleActivity implements ILifecycleActivity{
    @Override
    public void onCreateNoView(AppCompatActivity activity){

    }

    @Override
    public void onCreateViewExists(AppCompatActivity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onStart(AppCompatActivity activity) {

    }

    @Override
    public void onResume(AppCompatActivity activity) {

    }

    @Override
    public void onPause(AppCompatActivity activity) {

    }

    @Override
    public void onStop(AppCompatActivity activity) {

    }

    @Override
    public void onDestroy(AppCompatActivity activity) {

    }
}
