package com.spookyrobotics.defaultproject.wrappers;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spookyrobotics.defaultproject.wrappers.interfaces.IFragmentLifecycleManager;
import com.spookyrobotics.defaultproject.wrappers.interfaces.ILifecycleFragment;

public abstract class LifecycleFragment extends Fragment{
    protected FragmentLifecycleManager mLifecycleManager = new FragmentLifecycleManager();

    public final IFragmentLifecycleManager getLifecycle(){
        return mLifecycleManager;
    }

    protected abstract ILifecycleFragment getLifecycleFragment();
    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLifecycleFragment().onCreate(this, savedInstanceState);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return createView(inflater,container,savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mLifecycleManager.notifyOnActivityCreated(this, savedInstanceState);
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
