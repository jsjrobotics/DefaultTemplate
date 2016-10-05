package com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces.ILifecycleActivity;

public abstract class AppCompatActivityWrapper extends AppCompatActivity {
    
    protected abstract ILifecycleActivity getLifecycleActivity();

    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getLifecycleActivity().onCreateNoView(this);
        } else {
            getLifecycleActivity().onCreateViewExists(this, savedInstanceState);
        }
    }

    @Override
    public final void onStart(){
        super.onStart();
        getLifecycleActivity().onStart(this);
    }

    @Override
    public final void onResume(){
        super.onResume();
        getLifecycleActivity().onResume(this);
    }

    @Override
    public final void onPause(){
        super.onPause();
        getLifecycleActivity().onPause(this);
    }

    @Override
    public final void onStop(){
        super.onStop();
        getLifecycleActivity().onStop(this);
    }

    @Override
    public final void onDestroy(){
        super.onDestroy();
        getLifecycleActivity().onDestroy(this);
    }
}
