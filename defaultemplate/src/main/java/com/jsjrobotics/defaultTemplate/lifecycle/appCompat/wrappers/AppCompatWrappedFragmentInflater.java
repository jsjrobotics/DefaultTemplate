package com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.interfaces.ILifecycleFragment;

public class AppCompatWrappedFragmentInflater extends AppCompatFragmentWrapper {
    public static final String FRAGMENT_TYPE = "WrappedFragmentInflater.FragmentType";
    private ILifecycleFragment mFragment;

    @Override
    protected ILifecycleFragment getLifecycleFragment() {
        if(mFragment == null){
            Class<? extends ILifecycleFragment> fragmentClazz = (Class<? extends ILifecycleFragment>) getArguments().getSerializable(FRAGMENT_TYPE);
            mFragment = AppCompatFragmentInflater.inflateFragment(fragmentClazz);
        }
        return mFragment;
    }
}
