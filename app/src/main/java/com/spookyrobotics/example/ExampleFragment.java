package com.spookyrobotics.example;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsjrobotics.defaultTemplate.lifecycle.DefaultLifecycleFragment;


public class ExampleFragment extends DefaultLifecycleFragment {

    @Override
    public View createView(Fragment fragment, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
