package com.spookyrobotics.defaultproject.nonCompat.wrappers;

import android.app.Activity;
import android.os.Bundle;

import com.spookyrobotics.defaultproject.nonCompat.wrappers.interfaces.IActivityLifecycleManager;
import com.spookyrobotics.defaultproject.nonCompat.wrappers.interfaces.ILifecycleActivity;


public abstract class LifecycleActivity extends Activity{
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
