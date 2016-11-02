package com.spookyrobotics.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsjrobotics.defaultTemplate.lifecycle.FragmentLifecycle;
import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.DefaultFragment;
import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleFragment;


public class ExampleFragment extends DefaultFragment {

    @Override
    protected ILifecycleFragment buildLifecycleFragment() {
        return new FragmentLifecycle() {
            @Override
            public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                return inflater.inflate(R.layout.fragment_main, container, false);
            }
        };
    }
}
