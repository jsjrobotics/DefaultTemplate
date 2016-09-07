package com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.LifecycleFragment;


public interface ILifecycleFragment {
    void onCreateNoView(Fragment fragment);
    void onCreateViewExists(Fragment fragment, Bundle savedInstanceState);
    void onStart(Fragment fragment);
    void onResume(Fragment fragment);
    void onPause(Fragment fragment);
    void onStop(Fragment fragment);
    void onDestroy(Fragment fragment);
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void onActivityCreated(Fragment fragment, Bundle savedInstanceState);
}
