package com.jsjrobotics.defaultTemplate.lifecycle;

import android.app.Activity;
import android.os.Bundle;

import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleActivity;

public abstract class DefaultLifecycleActivity implements ILifecycleActivity {

    @Override
    public void onCreateNoView(Activity activity){
        activity.setContentView(getLayoutXml());
    }

    @Override
    public void onCreateViewExists(Activity activity, Bundle savedInstanceState) {
        activity.setContentView(getLayoutXml());
    }

    @Override
    public void onStart(Activity activity) {

    }

    @Override
    public void onResume(Activity activity) {

    }

    @Override
    public void onPause(Activity activity) {

    }

    @Override
    public void onStop(Activity activity) {

    }

    @Override
    public void onDestroy(Activity activity) {

    }
}
