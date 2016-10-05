package com.spookyrobotics.example;


import android.app.Activity;

import com.jsjrobotics.defaultTemplate.lifecycle.DefaultLifecycleActivity;
import com.jsjrobotics.defaultTemplate.lifecycle.NavigationManager;
import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.FragmentInflater;


public class ExampleActivity extends DefaultLifecycleActivity {
    private static final String EXAMPLE_FRAGMENT_TAG = "example_fragment_tag";

    @Override
    public void onCreateNoView(Activity activity) {
        super.onCreateNoView(activity);
        NavigationManager.displayFragment(
                activity,
                R.id.content_frame,
                FragmentInflater.instantiante(ExampleFragment.class),
                EXAMPLE_FRAGMENT_TAG,
                null);
    }


    @Override
    public int getLayoutXml() {
        return R.layout.activity_main;
    }
}
