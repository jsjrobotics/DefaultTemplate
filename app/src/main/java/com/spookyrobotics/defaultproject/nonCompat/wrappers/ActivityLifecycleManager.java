package com.spookyrobotics.defaultproject.nonCompat.wrappers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;

import com.spookyrobotics.defaultproject.functional.Receiver;
import com.spookyrobotics.defaultproject.nonCompat.wrappers.interfaces.IActivityLifecycleManager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class ActivityLifecycleManager implements IActivityLifecycleManager {
    private final Set<Receiver<Activity>> mOnResumeListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Activity>> mOnPauseListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Activity>> mOnDestroyedListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Pair<Activity, Bundle>>> mOnSaveInstanceStateListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Activity>> mOnStartedListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Activity>> mOnStoppedListeners = Collections.synchronizedSet(new HashSet<>());

    boolean addOnResumeListener(Receiver<Activity> receiver){
        return mOnResumeListeners.add(receiver);
    }

    public boolean addOnPauseListener(Receiver<Activity> receiver){
        return mOnPauseListeners.add(receiver);
    }

    public boolean addOnDestroyedListener(Receiver<Activity> receiver){
        return mOnDestroyedListeners.add(receiver);
    }

    public boolean addOnSaveInstanceStateListener(Receiver<Pair<Activity, Bundle>> receiver){
        return mOnSaveInstanceStateListeners.add(receiver);
    }
    public boolean addOnStartedListener(Receiver<Activity> receiver){
        return mOnStartedListeners.add(receiver);
    }

    public boolean addOnStoppedListener(Receiver<Activity> receiver){
        return mOnStoppedListeners.add(receiver);
    }

    public boolean removeOnResumeListener(Receiver<Activity> receiver){
        return mOnResumeListeners.remove(receiver);
    }

    public boolean removeOnPauseListener(Receiver<Activity> receiver){
        return mOnPauseListeners.remove(receiver);
    }

    public boolean removeOnDestroyedListener(Receiver<Activity> receiver){
        return mOnDestroyedListeners.remove(receiver);
    }

    public boolean removeOnSaveInstanceStateListener(Receiver<Pair<Activity, Bundle>> receiver){
        return mOnSaveInstanceStateListeners.remove(receiver);
    }
    public boolean removeOnStartedListener(Receiver<Activity> receiver){
        return mOnStartedListeners.remove(receiver);
    }

    public boolean removeOnStoppedListener(Receiver<Activity> receiver){
        return mOnStoppedListeners.remove(receiver);
    }

    public void notifyOnStart(final Activity activity) {
        synchronized (mOnStartedListeners){
            for(Receiver<Activity> r : mOnStartedListeners){
                r.accept(activity);
            }
        }
    }

    public void notifyOnResume(final Activity activity) {
        synchronized (mOnResumeListeners){
            for(Receiver<Activity> r : mOnResumeListeners){
                r.accept(activity);
            }
        }
    }

    public void notifyOnPause(final Activity activity) {
        synchronized (mOnPauseListeners){
            for(Receiver<Activity> r : mOnPauseListeners){
                r.accept(activity);
            }
        }
    }

    public void notifyOnStop(Activity activity) {
        mOnSaveInstanceStateListeners.clear();
        mOnStartedListeners.clear();
        mOnResumeListeners.clear();
        mOnPauseListeners.clear();

        synchronized (mOnStoppedListeners){
            for(Receiver<Activity> r : mOnStoppedListeners){
                r.accept(activity);
            }
            mOnStoppedListeners.clear();
        }
    }

    /**
     * Removes all listeners
     * @param activity
     */
    public void notifyOnDestroy(Activity activity) {
        synchronized (mOnDestroyedListeners){
            for(Receiver<Activity> r : mOnDestroyedListeners){
                r.accept(activity);
            }
        }
        mOnDestroyedListeners.clear();
    }
}
