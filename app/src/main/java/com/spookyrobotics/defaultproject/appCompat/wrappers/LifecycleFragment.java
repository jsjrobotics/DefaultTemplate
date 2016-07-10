package com.spookyrobotics.defaultproject.appCompat.wrappers;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.spookyrobotics.defaultproject.appCompat.wrappers.interfaces.IFragmentLifecycleManager;
import com.spookyrobotics.defaultproject.appCompat.wrappers.interfaces.ILifecycleFragment;

public abstract class LifecycleFragment extends Fragment {
    protected FragmentLifecycleManager mLifecycleManager = new FragmentLifecycleManager();

    public final IFragmentLifecycleManager getLifecycle(){
        return mLifecycleManager;
    }
    protected abstract ILifecycleFragment getLifecycleFragment();

    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLifecycleFragment().onCreate(this, savedInstanceState);
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
