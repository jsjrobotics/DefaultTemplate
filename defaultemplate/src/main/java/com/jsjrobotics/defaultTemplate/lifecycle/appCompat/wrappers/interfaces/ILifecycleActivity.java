package com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public interface ILifecycleActivity {
    void onCreateNoView(AppCompatActivity activity);
    void onCreateViewExists(AppCompatActivity activity, Bundle savedInstanceState);
    void onStart(AppCompatActivity activity);
    void onResume(AppCompatActivity activity);
    void onPause(AppCompatActivity activity);
    void onStop(AppCompatActivity activity);
    void onDestroy(AppCompatActivity activity);
}
