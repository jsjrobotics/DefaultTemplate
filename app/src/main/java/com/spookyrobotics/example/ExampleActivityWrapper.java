package com.spookyrobotics.example;


import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleActivity;
import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.ActivityWrapper;

public class ExampleActivityWrapper extends ActivityWrapper {
    private ExampleActivity mActivity = new ExampleActivity();

    @Override
    protected ILifecycleActivity getLifecycleActivity() {
        return mActivity;
    }
}
