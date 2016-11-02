package com.spookyrobotics.example;


import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleActivity;
import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.DefaultActivity;

public class ExampleActivityWrapper extends DefaultActivity {
    private ExampleActivity mActivity = new ExampleActivity();

    @Override
    protected ILifecycleActivity buildLifecycleActivity() {
        return mActivity;
    }
}
