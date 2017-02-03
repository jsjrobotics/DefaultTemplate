package com.spookyrobotics.example;


import android.app.Activity;

import com.jsjrobotics.defaultTemplate.lifecycle.DefaultLifecycleActivity;


public class ExampleActivity extends DefaultLifecycleActivity {
    private static final String EXAMPLE_FRAGMENT_TAG = "example_fragment_tag";

    @Override
    public void onCreateNoView(Activity activity) {
        super.onCreateNoView(activity);
        activity.getFragmentManager()
                .beginTransaction()
                .add(R.id.content_frame, new ExampleFragment(), EXAMPLE_FRAGMENT_TAG)
                .commit();
    }


    @Override
    public int getLayoutXml() {
        return R.layout.activity_main;
    }
}
