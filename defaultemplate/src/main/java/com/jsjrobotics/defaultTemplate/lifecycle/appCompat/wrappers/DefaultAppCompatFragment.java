package com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces.ILifecycleFragment;

public abstract class DefaultAppCompatFragment extends Fragment {

    private ILifecycleFragment mLifecycleFragment;

    protected abstract ILifecycleFragment buildLifecycleFragment();

    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mLifecycleFragment = buildLifecycleFragment();
        if (savedInstanceState == null){
            mLifecycleFragment.onCreateNoView(this);
        } else {
            mLifecycleFragment.onCreateViewExists(this, savedInstanceState);
        }
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return mLifecycleFragment.createView(this, inflater,container,savedInstanceState);
    }

    @Override
    public final void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mLifecycleFragment.onActivityCreated(this, savedInstanceState);
    }

    @Override
    public final void onStart(){
        super.onStart();
        mLifecycleFragment.onStart(this);
    }

    @Override
    public final void onResume(){
        super.onResume();
        mLifecycleFragment.onResume(this);
    }

    @Override
    public final void onPause(){
        super.onPause();
        mLifecycleFragment.onPause(this);
    }

    @Override
    public final void onStop(){
        super.onStop();
        mLifecycleFragment.onStop(this);
    }

    @Override
    public final void onDestroy(){
        super.onDestroy();
        mLifecycleFragment.onDestroy(this);
    }
}
