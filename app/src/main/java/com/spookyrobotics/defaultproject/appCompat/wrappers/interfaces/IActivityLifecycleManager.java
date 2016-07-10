package com.spookyrobotics.defaultproject.appCompat.wrappers.interfaces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.spookyrobotics.defaultproject.functional.Receiver;

public interface IActivityLifecycleManager {
    boolean addOnResumeListener(Receiver<AppCompatActivity> receiver);

    boolean addOnPauseListener(Receiver<AppCompatActivity> receiver);

    boolean addOnDestroyedListener(Receiver<AppCompatActivity> receiver);

    boolean addOnSaveInstanceStateListener(Receiver<Pair<AppCompatActivity, Bundle>> receiver);
    boolean addOnStartedListener(Receiver<AppCompatActivity> receiver);

    boolean addOnStoppedListener(Receiver<AppCompatActivity> receiver);

    boolean removeOnResumeListener(Receiver<AppCompatActivity> receiver);

    boolean removeOnPauseListener(Receiver<AppCompatActivity> receiver);

    boolean removeOnDestroyedListener(Receiver<AppCompatActivity> receiver);

    boolean removeOnSaveInstanceStateListener(Receiver<Pair<AppCompatActivity, Bundle>> receiver);
    boolean removeOnStartedListener(Receiver<AppCompatActivity> receiver);

    boolean removeOnStoppedListener(Receiver<AppCompatActivity> receiver);
}
