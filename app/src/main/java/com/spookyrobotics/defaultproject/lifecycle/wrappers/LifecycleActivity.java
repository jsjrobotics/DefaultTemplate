package com.spookyrobotics.defaultproject.lifecycle.wrappers;

import android.app.Activity;
import android.os.Bundle;

import com.spookyrobotics.defaultproject.lifecycle.wrappers.interfaces.ILifecycleActivity;


public abstract class LifecycleActivity extends Activity{


    protected abstract ILifecycleActivity getLifecycleActivity();

    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLifecycleActivity().onCreate(this, savedInstanceState);
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
