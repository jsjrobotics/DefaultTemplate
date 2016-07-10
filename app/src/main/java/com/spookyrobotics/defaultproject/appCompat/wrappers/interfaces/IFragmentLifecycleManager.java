package com.spookyrobotics.defaultproject.appCompat.wrappers.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;

import com.spookyrobotics.defaultproject.functional.Receiver;

public interface IFragmentLifecycleManager {
    boolean addOnResumeListener(Receiver<Fragment> receiver);

    boolean addOnPauseListener(Receiver<Fragment> receiver);

    boolean addOnDestroyedListener(Receiver<Fragment> receiver);

    boolean addOnSaveInstanceStateListener(Receiver<Pair<Fragment, Bundle>> receiver);
    boolean addOnStartedListener(Receiver<Fragment> receiver);

    boolean addOnStoppedListener(Receiver<Fragment> receiver);

    boolean removeOnResumeListener(Receiver<Fragment> receiver);

    boolean removeOnPauseListener(Receiver<Fragment> receiver);

    boolean removeOnDestroyedListener(Receiver<Fragment> receiver);

    boolean removeOnSaveInstanceStateListener(Receiver<Pair<Fragment, Bundle>> receiver);
    boolean removeOnStartedListener(Receiver<Fragment> receiver);

    boolean removeOnStoppedListener(Receiver<Fragment> receiver);
}
