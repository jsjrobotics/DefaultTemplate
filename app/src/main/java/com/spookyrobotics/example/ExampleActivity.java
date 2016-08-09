package com.spookyrobotics.example;


import android.app.Activity;
import android.os.Bundle;

import com.spookyrobotics.defaultproject.lifecycle.DefaultLifecycleActivity;
import com.spookyrobotics.defaultproject.lifecycle.NavigationManager;
import com.spookyrobotics.defaultproject.lifecycle.wrappers.DefaultFragmentWrapper;

public class ExampleActivity extends DefaultLifecycleActivity {
    private static final String EXAMPLE_FRAGMENT_TAG = "example_fragment_tag";

    @Override
    public void onCreate(Activity activity, Bundle savedInstanceState) {
        activity.setContentView(R.layout.activity_main);
        NavigationManager.displayFragment(
                activity,
                R.id.content_frame,
                DefaultFragmentWrapper.instantiante(ExampleFragment.class),
                EXAMPLE_FRAGMENT_TAG,
                null);
    }
}
