package com.spookyrobotics.example;

import com.spookyrobotics.defaultproject.lifecycle.wrappers.LifecycleActivity;
import com.spookyrobotics.defaultproject.lifecycle.wrappers.interfaces.ILifecycleActivity;

public class ExampleActivityWrapper extends LifecycleActivity {
    private ExampleActivity mActivity = new ExampleActivity();

    @Override
    protected ILifecycleActivity getLifecycleActivity() {
        return mActivity;
    }
}
