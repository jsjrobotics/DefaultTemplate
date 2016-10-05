package com.jsjrobotics.defaultTemplate.lifecycle.wrappers;

import android.os.Bundle;

import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleFragment;

import java.lang.reflect.Constructor;

public class FragmentInflater {
    public static <T extends ILifecycleFragment> FragmentWrapper instantiante(Class<T> fragmentClass){
        WrappedFragmentInflater wrapper = new WrappedFragmentInflater();
        Bundle b = new Bundle();
        b.putSerializable(WrappedFragmentInflater.FRAGMENT_TYPE, fragmentClass);
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
