package com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers;

import android.os.Bundle;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.NavigationManager;
import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces.ILifecycleFragment;

import java.lang.reflect.Constructor;

public class AppCompatFragmentInflater {
    public static <T extends ILifecycleFragment> AppCompatFragmentWrapper instantiante(Class<T> fragmentClass){
        return instantiante(fragmentClass, null);
    }

    public static <T extends ILifecycleFragment> AppCompatFragmentWrapper instantiante(Class<T> fragmentClass, Bundle bundleExtra) {
        AppCompatWrappedFragmentInflater wrapper = new AppCompatWrappedFragmentInflater();
        Bundle b = new Bundle();
        b.putSerializable(AppCompatWrappedFragmentInflater.FRAGMENT_TYPE, fragmentClass);
        b.putBundle(NavigationManager.FRAGMENT_EXTRA, bundleExtra);
        wrapper.setArguments(b);
        return wrapper;
    }

    public static <T extends ILifecycleFragment> ILifecycleFragment inflateFragment(Class<T> fragmentClass) {
        for(Constructor c : fragmentClass.getConstructors()){
            if(c.getParameterTypes().length == 0){
                try {
                    T fragment = (T) c.newInstance(null);
                    return fragment;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        throw new IllegalArgumentException("To instantiate a fragment with default wrapper, must have empty constructor");
    }


}
