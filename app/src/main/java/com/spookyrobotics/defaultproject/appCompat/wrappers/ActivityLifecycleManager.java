package com.spookyrobotics.defaultproject.appCompat.wrappers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.spookyrobotics.defaultproject.appCompat.wrappers.interfaces.IActivityLifecycleManager;
import com.spookyrobotics.defaultproject.functional.Receiver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class ActivityLifecycleManager implements IActivityLifecycleManager{
    private final Set<Receiver<AppCompatActivity>> mOnResumeListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<AppCompatActivity>> mOnPauseListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<AppCompatActivity>> mOnDestroyedListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Pair<AppCompatActivity, Bundle>>> mOnSaveInstanceStateListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<AppCompatActivity>> mOnStartedListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<AppCompatActivity>> mOnStoppedListeners = Collections.synchronizedSet(new HashSet<>());

    public boolean addOnResumeListener(Receiver<AppCompatActivity> receiver){
        return mOnResumeListeners.add(receiver);
    }

    public boolean addOnPauseListener(Receiver<AppCompatActivity> receiver){
        return mOnPauseListeners.add(receiver);
    }

    public boolean addOnDestroyedListener(Receiver<AppCompatActivity> receiver){
        return mOnDestroyedListeners.add(receiver);
    }

    public boolean addOnSaveInstanceStateListener(Receiver<Pair<AppCompatActivity, Bundle>> receiver){
        return mOnSaveInstanceStateListeners.add(receiver);
    }
    public boolean addOnStartedListener(Receiver<AppCompatActivity> receiver){
        return mOnStartedListeners.add(receiver);
    }

    public boolean addOnStoppedListener(Receiver<AppCompatActivity> receiver){
        return mOnStoppedListeners.add(receiver);
    }

    public boolean removeOnResumeListener(Receiver<AppCompatActivity> receiver){
        return mOnResumeListeners.remove(receiver);
    }

    public boolean removeOnPauseListener(Receiver<AppCompatActivity> receiver){
        return mOnPauseListeners.remove(receiver);
    }

    public boolean removeOnDestroyedListener(Receiver<AppCompatActivity> receiver){
        return mOnDestroyedListeners.remove(receiver);
    }

    public boolean removeOnSaveInstanceStateListener(Receiver<Pair<AppCompatActivity, Bundle>> receiver){
        return mOnSaveInstanceStateListeners.remove(receiver);
    }
    public boolean removeOnStartedListener(Receiver<AppCompatActivity> receiver){
        return mOnStartedListeners.remove(receiver);
    }

    public boolean removeOnStoppedListener(Receiver<AppCompatActivity> receiver){
        return mOnStoppedListeners.remove(receiver);
    }

    public void notifyOnStart(final AppCompatActivity activity) {
        synchronized (mOnStartedListeners){
            for(Receiver<AppCompatActivity> r : mOnStartedListeners){
                r.accept(activity);
            }
        }
    }

    public void notifyOnResume(final AppCompatActivity activity) {
        synchronized (mOnResumeListeners){
            for(Receiver<AppCompatActivity> r : mOnResumeListeners){
                r.accept(activity);
            }
        }
    }

    public void notifyOnPause(final AppCompatActivity activity) {
        synchronized (mOnPauseListeners){
            for(Receiver<AppCompatActivity> r : mOnPauseListeners){
                r.accept(activity);
            }
        }
    }

    public void notifyOnStop(AppCompatActivity activity) {
        mOnSaveInstanceStateListeners.clear();
        mOnStartedListeners.clear();
        mOnResumeListeners.clear();
        mOnPauseListeners.clear();

        synchronized (mOnStoppedListeners){
            for(Receiver<AppCompatActivity> r : mOnStoppedListeners){
                r.accept(activity);
            }
            mOnStoppedListeners.clear();
        }
    }

    /**
     * Removes all listeners
     * @param activity
     */
    public void notifyOnDestroy(AppCompatActivity activity) {
        synchronized (mOnDestroyedListeners){
            for(Receiver<AppCompatActivity> r : mOnDestroyedListeners){
                r.accept(activity);
            }
        }
        mOnDestroyedListeners.clear();
    }
}
