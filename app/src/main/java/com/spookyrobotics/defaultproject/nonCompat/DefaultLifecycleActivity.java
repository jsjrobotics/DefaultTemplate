package com.spookyrobotics.defaultproject.nonCompat;

import com.spookyrobotics.defaultproject.nonCompat.MyActivity;
import com.spookyrobotics.defaultproject.nonCompat.wrappers.LifecycleActivity;
import com.spookyrobotics.defaultproject.nonCompat.wrappers.interfaces.ILifecycleActivity;

public class DefaultLifecycleActivity extends LifecycleActivity {
    private ILifecycleActivity mActivity = new MyActivity();

    @Override
    protected ILifecycleActivity getLifecycleActivity() {
        return mActivity;
    }
}

