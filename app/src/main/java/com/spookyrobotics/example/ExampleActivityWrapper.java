package com.spookyrobotics.example;


import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.ActivityWrapper;
import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleActivity;

public class ExampleActivityWrapper extends ActivityWrapper {
    private ExampleActivity mActivity = new ExampleActivity();

    @Override
    protected ILifecycleActivity getLifecycleActivity() {
        return mActivity;
    }
}
