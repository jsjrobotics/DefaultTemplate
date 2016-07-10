package com.spookyrobotics.defaultproject.nonCompat;

import com.spookyrobotics.defaultproject.nonCompat.MyFragment;
import com.spookyrobotics.defaultproject.nonCompat.wrappers.LifecycleFragment;
import com.spookyrobotics.defaultproject.nonCompat.wrappers.interfaces.ILifecycleFragment;

public class DefaultLifecycleFragment extends LifecycleFragment {
    private ILifecycleFragment mFragment = new MyFragment();

    @Override
    protected ILifecycleFragment getLifecycleFragment() {
        return mFragment;
    }
}
