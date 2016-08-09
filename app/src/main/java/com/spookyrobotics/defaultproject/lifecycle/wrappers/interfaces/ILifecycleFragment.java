package com.spookyrobotics.defaultproject.lifecycle.wrappers.interfaces;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public interface ILifecycleFragment {
    void onCreate(Fragment fragment, Bundle savedInstanceState);
    void onStart(Fragment fragment);
    void onResume(Fragment fragment);
    void onPause(Fragment fragment);
    void onStop(Fragment fragment);
    void onDestroy(Fragment fragment);
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
