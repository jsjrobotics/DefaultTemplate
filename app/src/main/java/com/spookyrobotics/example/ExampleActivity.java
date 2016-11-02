package com.spookyrobotics.example;


import android.app.Activity;

import com.jsjrobotics.defaultTemplate.lifecycle.ActivityLifecycle;
import com.jsjrobotics.defaultTemplate.lifecycle.NavigationManager;


public class ExampleActivity extends ActivityLifecycle {
    private static final String EXAMPLE_FRAGMENT_TAG = "example_fragment_tag";

    @Override
    public void onCreateNoView(Activity activity) {
        super.onCreateNoView(activity);
        NavigationManager.displayFragment(
                activity,
                R.id.content_frame,
                new ExampleFragment(),
                EXAMPLE_FRAGMENT_TAG,
                null);
    }


    @Override
    public int getLayoutXml() {
        return R.layout.activity_main;
    }
}
