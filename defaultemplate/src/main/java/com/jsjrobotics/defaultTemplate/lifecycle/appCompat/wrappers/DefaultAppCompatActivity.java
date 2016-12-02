package com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces.ILifecycleActivity;

public abstract class DefaultAppCompatActivity extends AppCompatActivity {

    private ILifecycleActivity mLifecycleActivity;

    protected abstract ILifecycleActivity buildLifecycleActivity();

    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mLifecycleActivity = buildLifecycleActivity();
        setContentView(mLifecycleActivity.getLayoutXml());
        if (savedInstanceState == null){
            mLifecycleActivity.onCreateNoView(this);
        } else {
            mLifecycleActivity.onCreateViewExists(this, savedInstanceState);
        }
    }

    @Override
    public final void onStart(){
        super.onStart();
        mLifecycleActivity.onStart(this);
    }

    @Override
    public final void onResume(){
        super.onResume();
        mLifecycleActivity.onResume(this);
    }

    @Override
    public final void onPause(){
        super.onPause();
        mLifecycleActivity.onPause(this);
    }

    @Override
    public final void onStop(){
        super.onStop();
        mLifecycleActivity.onStop(this);
    }

    @Override
    public final void onDestroy(){
        super.onDestroy();
        mLifecycleActivity.onDestroy(this);
    }
}
