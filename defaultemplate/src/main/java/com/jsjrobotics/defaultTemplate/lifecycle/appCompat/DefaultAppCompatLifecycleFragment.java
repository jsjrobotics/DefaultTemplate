package com.jsjrobotics.defaultTemplate.lifecycle.appCompat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces.ILifecycleFragment;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.Optional;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.Receiver;
import com.jsjrobotics.defaultTemplate.lifecycle.functional.WeakReferenceSupplier;

public abstract class DefaultAppCompatLifecycleFragment extends Fragment implements ILifecycleFragment {


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
    public final View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        return createView(this, inflater, container,savedInstanceState);
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
        if (!mCalledParent) {
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            String className = getClass().getSimpleName();
            throw new IllegalStateException(className + " - " + methodName + " - Must call through parents super method");
        }
        mCalledParent = false;
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

    public static void runOnUiThread(WeakReferenceSupplier<Fragment> context, final Runnable runnable){
        context.get().ifPresent(new Receiver<Fragment>() {
            @Override
            public void accept(Fragment fragment) {
                ifAttached(fragment, new Receiver<FragmentActivity>() {
                    @Override
                    public void accept(FragmentActivity activity) {
                        activity.runOnUiThread(runnable);
                    }
                });
            }
        });

    }

    public WeakReferenceSupplier<Fragment> buildFragmentSupplier(final Fragment fragment){
        return new WeakReferenceSupplier<>(fragment);
    }
}
