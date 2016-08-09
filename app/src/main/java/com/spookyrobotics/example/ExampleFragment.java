package com.spookyrobotics.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spookyrobotics.defaultproject.lifecycle.DefaultLifecycleFragment;

public class ExampleFragment extends DefaultLifecycleFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
