package com.spookyrobotics.defaultproject.appCompat;

import com.spookyrobotics.defaultproject.appCompat.MyFragment;
import com.spookyrobotics.defaultproject.appCompat.wrappers.LifecycleFragment;
import com.spookyrobotics.defaultproject.appCompat.wrappers.interfaces.ILifecycleFragment;

public class DefaultLifecycleFragment extends LifecycleFragment {

    ILifecycleFragment mFragment = new MyFragment();

    @Override
    protected ILifecycleFragment getLifecycleFragment() {
        return mFragment;
    }
}
