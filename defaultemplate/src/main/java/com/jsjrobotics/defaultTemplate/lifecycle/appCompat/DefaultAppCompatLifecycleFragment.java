package com.jsjrobotics.defaultTemplate.lifecycle.appCompat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces.ILifecycleFragment;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.Optional;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.Receiver;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.Supplier;

public abstract class DefaultAppCompatLifecycleFragment extends Fragment implements ILifecycleFragment {


    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            onCreateNoView(this);
        } else {
            onCreate(savedInstanceState);
        }
    }

    @Override
    public final View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        return createView(this, inflater, container,savedInstanceState);
    }

    @Override
    public final void onStart(){
        super.onStart();
        onStart(this);
    }

    @Override
    public final void onResume(){
        super.onResume();
        onResume(this);
    }

    @Override
    public final void onPause(){
        super.onPause();
        onPause(this);
    }

    @Override
    public final void onStop(){
        super.onStop();
        onStop(this);
    }

    @Override
    public final void onDestroy(){
        super.onDestroy();
        onDestroy(this);
    }

    @Override
    public void onActivityCreated(Fragment fragment, Bundle bundle){

    }

    @Override
    public void onCreateNoView(Fragment fragment) {

    }

    @Override
    public void onCreateViewExists(Fragment fragment, Bundle savedInstanceState) {

    }

    @Override
    public void onStart(Fragment fragment) {

    }

    @Override
    public void onResume(Fragment fragment) {

    }

    @Override
    public void onPause(Fragment fragment) {

    }

    @Override
    public void onStop(Fragment fragment) {

    }

    @Override
    public void onDestroy(Fragment fragment) {

    }

    public static void ifAttached(Fragment fragment, Receiver<FragmentActivity> receiver){
        Optional.ofNullable(fragment.getActivity()).ifPresent(receiver);
    }

    public static void runOnUiThread(Fragment fragment, final Runnable runnable){
        ifAttached(fragment, new Receiver<FragmentActivity>() {
            @Override
            public void accept(FragmentActivity activity) {
                activity.runOnUiThread(runnable);
            }
        });
    }

    public Supplier<Fragment> buildFragmentSupplier(final Fragment fragment){
        return new Supplier<Fragment>() {
            @Override
            public Fragment get() {
                return fragment;
            }
        };
    }
}
