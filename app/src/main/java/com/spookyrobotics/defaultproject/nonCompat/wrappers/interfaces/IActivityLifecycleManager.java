package com.spookyrobotics.defaultproject.nonCompat.wrappers.interfaces;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Pair;

import com.spookyrobotics.defaultproject.functional.Receiver;

public interface IActivityLifecycleManager {
    boolean addOnResumeListener(Receiver<Activity> receiver);

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
