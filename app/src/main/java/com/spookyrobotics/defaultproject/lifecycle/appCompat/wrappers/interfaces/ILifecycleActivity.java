package com.spookyrobotics.defaultproject.lifecycle.appCompat.wrappers.interfaces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public interface ILifecycleActivity {
    void onCreate(AppCompatActivity activity, Bundle savedInstanceState);
    void onStart(AppCompatActivity activity);
    void onResume(AppCompatActivity activity);
    void onPause(AppCompatActivity activity);
    void onStop(AppCompatActivity activity);
    void onDestroy(AppCompatActivity activity);
}
