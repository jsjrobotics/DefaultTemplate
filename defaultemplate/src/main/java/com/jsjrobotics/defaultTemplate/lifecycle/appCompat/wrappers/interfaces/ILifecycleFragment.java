package com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface ILifecycleFragment {
    void onCreateNoView(Fragment fragment);
    void onCreateViewExists(Fragment fragment, Bundle savedInstanceState);

    View createView(Fragment fragment, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void onStart(Fragment fragment);

    void onResume(Fragment fragment);

    void onPause(Fragment fragment);

    void onStop(Fragment fragment);

    void onDestroy(Fragment fragment);

    void onActivityCreated(Fragment fragment, Bundle savedInstanceState);
}
