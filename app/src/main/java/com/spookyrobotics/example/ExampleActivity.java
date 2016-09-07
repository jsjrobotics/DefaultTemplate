package com.spookyrobotics.example;


import android.app.Activity;
import android.os.Bundle;

import com.jsjrobotics.defaultTemplate.lifecycle.DefaultLifecycleActivity;
import com.jsjrobotics.defaultTemplate.lifecycle.NavigationManager;
import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.DefaultFragmentWrapper;


public class ExampleActivity extends DefaultLifecycleActivity {
    private static final String EXAMPLE_FRAGMENT_TAG = "example_fragment_tag";

    @Override
    public void onCreateNoView(Activity activity) {
        activity.setContentView(R.layout.activity_main);
        NavigationManager.displayFragment(
                activity,
                R.id.content_frame,
                DefaultFragmentWrapper.instantiante(ExampleFragment.class),
                EXAMPLE_FRAGMENT_TAG,
                null);
    }

    @Override
    public void onCreateViewExists(Activity activity, Bundle savedInstanceState) {
        activity.setContentView(R.layout.activity_main);
    }
}
