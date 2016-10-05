package com.jsjrobotics.defaultTemplate.lifecycle.wrappers;

import android.app.Activity;
import android.os.Bundle;

import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleActivity;


public abstract class ActivityWrapper extends Activity{


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
