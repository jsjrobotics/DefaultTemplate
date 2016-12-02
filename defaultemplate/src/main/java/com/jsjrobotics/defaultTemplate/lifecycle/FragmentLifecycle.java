package com.jsjrobotics.defaultTemplate.lifecycle;

import android.app.Fragment;
import android.os.Bundle;

import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleFragment;

public abstract class FragmentLifecycle extends Fragment implements ILifecycleFragment {


    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            onCreateNoView(this);
        } else {
            onCreate(savedInstanceState);
        }
    }

    @Override
    public final void onStart() {
        super.onStart();
        onStart(this);
    }

    @Override
    public final void onResume() {
        super.onResume();
        onResume(this);
    }

    @Override
    public final void onPause() {
        super.onPause();
        onPause(this);
    }

    @Override
    public final void onStop() {
        super.onStop();
        onStop(this);
    }

    @Override
    public final void onDestroy() {
        super.onDestroy();
        onDestroy(this);
    }
}