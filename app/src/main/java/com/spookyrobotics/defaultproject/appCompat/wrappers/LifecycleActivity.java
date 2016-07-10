package com.spookyrobotics.defaultproject.appCompat.wrappers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.spookyrobotics.defaultproject.appCompat.wrappers.interfaces.IActivityLifecycleManager;
import com.spookyrobotics.defaultproject.appCompat.wrappers.interfaces.ILifecycleActivity;

public abstract class LifecycleActivity extends AppCompatActivity {
    protected ActivityLifecycleManager mLifecycleManager = new ActivityLifecycleManager();

    public final IActivityLifecycleManager getLifecycle(){
        return mLifecycleManager;
    }

    protected abstract ILifecycleActivity getLifecycleActivity();

    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLifecycleActivity().onCreate(this, savedInstanceState);
    }

    @Override
    public final void onStart(){
        super.onStart();
        mLifecycleManager.notifyOnStart(this);
    }

    @Override
    public final void onResume(){
        super.onResume();
        mLifecycleManager.notifyOnResume(this);
    }

    @Override
    public final void onPause(){
        super.onPause();
        mLifecycleManager.notifyOnPause(this);
    }

    @Override
    public final void onStop(){
        super.onStop();
        mLifecycleManager.notifyOnStop(this);
    }

    @Override
    public final void onDestroy(){
        super.onDestroy();
        mLifecycleManager.notifyOnDestroy(this);
    }
}
