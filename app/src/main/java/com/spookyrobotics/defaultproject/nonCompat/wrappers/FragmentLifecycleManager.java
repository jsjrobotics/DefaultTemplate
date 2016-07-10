package com.spookyrobotics.defaultproject.nonCompat.wrappers;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Pair;

import com.spookyrobotics.defaultproject.functional.Receiver;
import com.spookyrobotics.defaultproject.nonCompat.wrappers.interfaces.IFragmentLifecycleManager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class FragmentLifecycleManager implements IFragmentLifecycleManager {
    private final Set<Receiver<Fragment>> mOnResumeListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Fragment>> mOnPauseListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Fragment>> mOnDestroyedListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Pair<Fragment, Bundle>>> mOnSaveInstanceStateListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Fragment>> mOnStartedListeners = Collections.synchronizedSet(new HashSet<>());
    private final Set<Receiver<Fragment>> mOnStoppedListeners = Collections.synchronizedSet(new HashSet<>());

    public boolean addOnResumeListener(Receiver<Fragment> receiver){
        return mOnResumeListeners.add(receiver);
    }

    public boolean addOnPauseListener(Receiver<Fragment> receiver){
        return mOnPauseListeners.add(receiver);
    }

    public boolean addOnDestroyedListener(Receiver<Fragment> receiver){
        return mOnDestroyedListeners.add(receiver);
    }

    public boolean addOnSaveInstanceStateListener(Receiver<Pair<Fragment, Bundle>> receiver){
        return mOnSaveInstanceStateListeners.add(receiver);
    }
    public boolean addOnStartedListener(Receiver<Fragment> receiver){
        return mOnStartedListeners.add(receiver);
    }

    public boolean addOnStoppedListener(Receiver<Fragment> receiver){
        return mOnStoppedListeners.add(receiver);
    }

    public boolean removeOnResumeListener(Receiver<Fragment> receiver){
        return mOnResumeListeners.remove(receiver);
    }

    public boolean removeOnPauseListener(Receiver<Fragment> receiver){
        return mOnPauseListeners.remove(receiver);
    }

    public boolean removeOnDestroyedListener(Receiver<Fragment> receiver){
        return mOnDestroyedListeners.remove(receiver);
    }

    public boolean removeOnSaveInstanceStateListener(Receiver<Pair<Fragment, Bundle>> receiver){
        return mOnSaveInstanceStateListeners.remove(receiver);
    }
    public boolean removeOnStartedListener(Receiver<Fragment> receiver){
        return mOnStartedListeners.remove(receiver);
    }

    public boolean removeOnStoppedListener(Receiver<Fragment> receiver){
        return mOnStoppedListeners.remove(receiver);
    }

    void notifyOnStart(final Fragment Fragment) {
        synchronized (mOnStartedListeners){
            for(Receiver<Fragment> r : mOnStartedListeners){
                r.accept(Fragment);
            }
        }
    }

    void notifyOnResume(final Fragment Fragment) {
        synchronized (mOnResumeListeners){
            for(Receiver<Fragment> r : mOnResumeListeners){
                r.accept(Fragment);
            }
        }
    }

    void notifyOnPause(final Fragment Fragment) {
        synchronized (mOnPauseListeners){
            for(Receiver<Fragment> r : mOnPauseListeners){
                r.accept(Fragment);
            }
        }
    }

    void notifyOnStop(Fragment Fragment) {
        synchronized (mOnStoppedListeners){
            for(Receiver<Fragment> r : mOnStoppedListeners){
                r.accept(Fragment);
            }
        }
    }

    /**
     * Removes all listeners
     * @param Fragment
     */
    void notifyOnDestroy(Fragment Fragment) {
        synchronized (mOnDestroyedListeners){
            for(Receiver<Fragment> r : mOnDestroyedListeners){
                r.accept(Fragment);
            }
        }
        mOnSaveInstanceStateListeners.clear();
        mOnStartedListeners.clear();
        mOnResumeListeners.clear();
        mOnPauseListeners.clear();
        mOnStoppedListeners.clear();
        mOnDestroyedListeners.clear();
    }
}
