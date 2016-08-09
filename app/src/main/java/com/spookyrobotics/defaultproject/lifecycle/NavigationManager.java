package com.spookyrobotics.defaultproject.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.spookyrobotics.defaultproject.lifecycle.wrappers.LifecycleActivity;

public class NavigationManager {
    public static final String ACTIVITY_EXTRA = "activity_extras";
    public static final String FRAGMENT_EXTRA = "fragment_extras";


    public static void launchActivity(Activity activity, Class<? extends LifecycleActivity> activityClass, Bundle activityExtras, boolean finish) {
        Intent intent = new Intent(activity, activityClass);
        intent.putExtra(ACTIVITY_EXTRA, activityExtras);
        activity.startActivity(intent);
        if(finish){
            activity.finish();
        }
    }

    public static void replaceFragment(Activity activity, Integer fragmentToReplace, Fragment fragment , String addToBackstackTag){
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction().replace(fragmentToReplace, fragment);
        if(addToBackstackTag != null){
            transaction.addToBackStack(addToBackstackTag);
        }
        transaction.commit();
    }

    public static void displayFragment(Activity activity, int containerId, Fragment fragment, String addToBackstackTag) {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction().add(containerId, fragment);
        if(addToBackstackTag != null){
            transaction.addToBackStack(addToBackstackTag);
        }
        transaction.commit();
    }

    public static void displayFragment(Activity activity, int containerId, Fragment fragment, String fragmentTag, String addToBackstackTag) {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction().add(containerId, fragment, fragmentTag);
        if(addToBackstackTag != null){
            transaction.addToBackStack(addToBackstackTag);
        }
        transaction.commit();
    }


}
