package com.spookyrobotics.defaultproject.appCompat;

import com.spookyrobotics.defaultproject.appCompat.MyActivity;
import com.spookyrobotics.defaultproject.appCompat.wrappers.LifecycleActivity;
import com.spookyrobotics.defaultproject.appCompat.wrappers.interfaces.ILifecycleActivity;

public class DefaultLifecycleActivity extends LifecycleActivity {

    ILifecycleActivity mActivity = new MyActivity();

    @Override
    protected ILifecycleActivity getLifecycleActivity() {
        return mActivity;
    }
}
