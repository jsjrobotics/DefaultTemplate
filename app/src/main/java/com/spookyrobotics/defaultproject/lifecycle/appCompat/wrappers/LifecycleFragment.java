package com.spookyrobotics.defaultproject.lifecycle.appCompat.wrappers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spookyrobotics.defaultproject.lifecycle.appCompat.wrappers.interfaces.ILifecycleFragment;

public abstract class LifecycleFragment extends Fragment {

    protected abstract ILifecycleFragment getLifecycleFragment();

    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLifecycleFragment().onCreate(this, savedInstanceState);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return getLifecycleFragment().createView(this, inflater,container,savedInstanceState);
    }


    @Override
    public final void onStart(){
        super.onStart();
        getLifecycleFragment().onStart(this);
    }

    @Override
    public final void onResume(){
        super.onResume();
        getLifecycleFragment().onResume(this);
    }

    @Override
    public final void onPause(){
        super.onPause();
        getLifecycleFragment().onPause(this);
    }

    @Override
    public final void onStop(){
        super.onStop();
        getLifecycleFragment().onStop(this);
    }

    @Override
    public final void onDestroy(){
        super.onDestroy();
        getLifecycleFragment().onDestroy(this);
    }
}
