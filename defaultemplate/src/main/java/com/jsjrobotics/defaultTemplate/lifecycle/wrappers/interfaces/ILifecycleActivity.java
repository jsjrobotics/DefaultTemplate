package com.jsjrobotics.defaultTemplate.lifecycle.wrappers.interfaces;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

public interface ILifecycleActivity {
    void onCreateNoView(Activity activity);
    void onCreateViewExists(Activity activity, Bundle savedInstanceState);

    void onStart(Activity activity);

    void onResume(Activity activity);

    void onPause(Activity activity);

    void onStop(Activity activity);

    void onDestroy(Activity activity);
    @LayoutRes int getLayoutXml();
}
