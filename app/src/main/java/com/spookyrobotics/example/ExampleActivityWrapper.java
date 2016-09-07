package com.spookyrobotics.example;


import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleActivity;
import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.LifecycleActivity;

public class ExampleActivityWrapper extends LifecycleActivity {
    private ExampleActivity mActivity = new ExampleActivity();

    @Override
    protected ILifecycleActivity getLifecycleActivity() {
        return mActivity;
    }
}
