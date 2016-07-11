package com.spookyrobotics.defaultproject.wrappers.interfaces;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;

import com.spookyrobotics.defaultproject.functional.Receiver;

public interface IActivityLifecycleManager {
    boolean addOnResumeListener(Receiver<Activity> receiver);

    boolean addOnPauseListener(Receiver<Activity> receiver);

    boolean addOnDestroyedListener(Receiver<Activity> receiver);

    boolean addOnSaveInstanceStateListener(Receiver<Pair<Activity, Bundle>> receiver);
    boolean addOnStartedListener(Receiver<Activity> receiver);

    boolean addOnStoppedListener(Receiver<Activity> receiver);

    boolean removeOnResumeListener(Receiver<Activity> receiver);

    boolean removeOnPauseListener(Receiver<Activity> receiver);

    boolean removeOnDestroyedListener(Receiver<Activity> receiver);

    boolean removeOnSaveInstanceStateListener(Receiver<Pair<Activity, Bundle>> receiver);
    boolean removeOnStartedListener(Receiver<Activity> receiver);

    boolean removeOnStoppedListener(Receiver<Activity> receiver);
}
