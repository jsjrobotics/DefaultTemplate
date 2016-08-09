package com.spookyrobotics.defaultproject.lifecycle.wrappers.interfaces;

import android.app.Activity;
import android.os.Bundle;

public interface ILifecycleActivity {
    void onCreate(Activity activity, Bundle savedInstanceState);

    void onStart(Activity activity);

    void onResume(Activity activity);

    void onPause(Activity activity);

    void onStop(Activity activity);

    void onDestroy(Activity activity);
}
