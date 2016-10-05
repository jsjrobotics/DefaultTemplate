package com.jsjrobotics.defaultTemplate.lifecycle.wrappers;

import com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces.ILifecycleFragment;

public class WrappedFragmentInflater extends FragmentWrapper {
    public static final String FRAGMENT_TYPE = "WrappedFragmentInflater.FragmentType";
    private ILifecycleFragment mFragment;

    @Override
    protected ILifecycleFragment getLifecycleFragment() {
        if(mFragment == null){
            Class<? extends ILifecycleFragment> fragmentClazz = (Class<? extends ILifecycleFragment>) getArguments().getSerializable(FRAGMENT_TYPE);
            mFragment = FragmentInflater.inflateFragment(fragmentClazz);
        }
        return mFragment;
    }
}
