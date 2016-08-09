package com.spookyrobotics.defaultproject.lifecycle.appCompat;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.spookyrobotics.defaultproject.lifecycle.appCompat.wrappers.interfaces.ILifecycleFragment;

public abstract class DefaultLifecycleFragment implements ILifecycleFragment {
    @Override
    public void onCreate(Fragment fragment, Bundle savedInstanceState) {

    }

    @Override
    public void onStart(Fragment fragment) {

    }

    @Override
    public void onResume(Fragment fragment) {

    }

    @Override
    public void onPause(Fragment fragment) {

    }

    @Override
    public void onStop(Fragment fragment) {

    }

    @Override
    public void onDestroy(Fragment fragment) {

    }
}
