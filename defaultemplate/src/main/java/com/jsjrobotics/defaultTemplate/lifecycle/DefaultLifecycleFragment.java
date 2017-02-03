package com.jsjrobotics.defaultTemplate.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsjrobotics.defaultTemplate.lifecycle.functional.Optional;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.Receiver;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.WeakReferenceSupplier;
import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleFragment;

public abstract class DefaultLifecycleFragment extends Fragment implements ILifecycleFragment {


    private boolean mCalledParent = false;

    @Override
    public final void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            onCreateNoView(this);
        } else {
            onCreateViewExists(this, savedInstanceState);
        }
    }

    @Override
    public final void onStart(){
        super.onStart();
        resetCalledParent();
        onStart(this);
        checkCalledParent();
    }

    @Override
    public final void onResume(){
        super.onResume();
        resetCalledParent();
        onResume(this);
        checkCalledParent();
    }

    @Override
    public final void onPause(){
        super.onPause();
        resetCalledParent();
        onPause(this);
        checkCalledParent();
    }

    @Override
    public final void onStop(){
        super.onStop();
        resetCalledParent();
        onStop(this);
        checkCalledParent();
    }

    @Override
    public final void onDestroy(){
        super.onDestroy();
        resetCalledParent();
        onDestroy(this);
        checkCalledParent();
    }

    @Override
    public void onActivityCreated(Fragment fragment, Bundle savedInstanceState){

    }

    @Override
    public void onCreateNoView(Fragment fragment) {

    }

    @Override
    public void onCreateViewExists(Fragment fragment, Bundle savedInstanceState) {

    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(this, inflater, container, savedInstanceState);
    }

    @Override
    public void onStart(Fragment fragment) {
        setCalledParent();
    }

    @Override
    public void onResume(Fragment fragment) {
        setCalledParent();
    }

    @Override
    public void onPause(Fragment fragment) {
        setCalledParent();
    }

    @Override
    public void onStop(Fragment fragment) {
        setCalledParent();
    }

    @Override
    public void onDestroy(Fragment fragment) {
        setCalledParent();
    }

    private void setCalledParent() {
        mCalledParent = true;
    }

    private void resetCalledParent() {
        mCalledParent = false;
    }

    private void checkCalledParent() {
        if (mCalledParent != true) {
            throw new IllegalStateException("Must call through parents super method");
        }
        mCalledParent = false;
    }

    public static void ifAttached(Fragment fragment, Receiver<Activity> receiver){
        Optional.ofNullable(fragment.getActivity()).ifPresent(receiver);
    }

    public static void runOnUiThread(Fragment fragment, final Runnable runnable){
        ifAttached(fragment, new Receiver<Activity>() {
            @Override
            public void accept(Activity activity) {
                activity.runOnUiThread(runnable);
            }
        });
    }

    public WeakReferenceSupplier<Fragment> buildFragmentSupplier(final Fragment fragment){
        return new WeakReferenceSupplier<>(fragment);
    }
}
