package com.spookyrobotics.defaultproject.lifecycle.appCompat.wrappers;

import android.os.Bundle;


import java.lang.reflect.Constructor;

import com.spookyrobotics.defaultproject.lifecycle.appCompat.NavigationManager;
import com.spookyrobotics.defaultproject.lifecycle.appCompat.wrappers.interfaces.ILifecycleFragment;

public class DefaultFragmentWrapper {
    public static <T extends ILifecycleFragment> LifecycleFragment instantiante(Class<T> fragmentClass){
        return instantiante(fragmentClass, null);
    }

    public static <T extends ILifecycleFragment> LifecycleFragment instantiante(Class<T> fragmentClass, Bundle bundleExtra) {
        WrappedFragmentInflater wrapper = new WrappedFragmentInflater();
        Bundle b = new Bundle();
        b.putSerializable(WrappedFragmentInflater.FRAGMENT_TYPE, fragmentClass);
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
