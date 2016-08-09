package com.spookyrobotics.defaultproject.lifecycle.wrappers;

import com.spookyrobotics.defaultproject.lifecycle.wrappers.interfaces.ILifecycleFragment;

public class WrappedFragmentInflater extends LifecycleFragment {
    public static final String FRAGMENT_TYPE = "WrappedFragmentInflater.FragmentType";
    private ILifecycleFragment mFragment;

    @Override
    protected ILifecycleFragment getLifecycleFragment() {
        if(mFragment == null){
            Class<? extends ILifecycleFragment> fragmentClazz = (Class<? extends ILifecycleFragment>) getArguments().getSerializable(FRAGMENT_TYPE);
            mFragment = DefaultFragmentWrapper.inflateFragment(fragmentClazz);
        }
        return mFragment;
    }
}
