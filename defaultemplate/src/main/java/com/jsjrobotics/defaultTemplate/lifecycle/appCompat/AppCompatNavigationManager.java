package com.jsjrobotics.defaultTemplate.lifecycle.appCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.AppCompatActivityWrapper;

public class AppCompatNavigationManager {
    public static final String ACTIVITY_EXTRA = "activity_extras";
    public static final String KEY_CONTENT_ID = "frame_layout_content_id";


    public static void launchActivity(Activity activity, Class<? extends AppCompatActivityWrapper> activityClass, Bundle activityExtras, boolean finish) {
        Intent intent = new Intent(activity, activityClass);
        intent.putExtra(ACTIVITY_EXTRA, activityExtras);
        activity.startActivity(intent);
        if(finish){
            activity.finish();
        }
    }

    public static void displayFragment(FragmentActivity activity, int containerId, Fragment fragment, String addToBackstackTag) {
        addContainerId(fragment, containerId);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction().replace(containerId, fragment);
        if(addToBackstackTag != null){
            transaction.addToBackStack(addToBackstackTag);
        }
        transaction.commit();
    }

    private static void addContainerId(Fragment fragment, int containerId) {
        if (fragment.getArguments() != null) {
            fragment.getArguments().putInt(KEY_CONTENT_ID, containerId);
            return;
        }
        Bundle arguments = new Bundle();
        arguments.putInt(KEY_CONTENT_ID, containerId);
        fragment.setArguments(arguments);

    }

    public static void displayFragment(FragmentActivity activity, int containerId, Fragment fragment, String fragmentTag, String addToBackstackTag) {
        addActivityExtras(activity, fragment);
        addContainerId(fragment, containerId);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction().replace(containerId, fragment, fragmentTag);
        if(addToBackstackTag != null){
            transaction.addToBackStack(addToBackstackTag);
        }
        transaction.commit();
    }

    private static void addActivityExtras(FragmentActivity activity, Fragment fragment) {
        Bundle activityExtras = activity.getIntent().getBundleExtra(AppCompatNavigationManager.ACTIVITY_EXTRA);
        if (activityExtras == null) {
            return;
        }
        Bundle fragmentExtras = fragment.getArguments();
        final Bundle arguments = new Bundle(activityExtras);
        if (fragmentExtras != null) {
            arguments .putAll(fragmentExtras);
        }
        fragment.setArguments(arguments);

    }

    public static void launchActivity(Activity activity, Class<? extends AppCompatActivityWrapper> activityClass, boolean finish) {
        launchActivity(activity, activityClass, null, finish);
    }


}

