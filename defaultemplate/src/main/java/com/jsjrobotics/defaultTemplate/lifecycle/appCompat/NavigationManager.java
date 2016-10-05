package com.jsjrobotics.defaultTemplate.lifecycle.appCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.jsjrobotics.defaultTemplate.lifecycle.appCompat.wrappers.AppCompatActivityWrapper;

public class NavigationManager {
    public static final String ACTIVITY_EXTRA = "activity_extras";
    public static final String FRAGMENT_EXTRA = "fragment_extras";


    public static void launchActivity(Activity activity, Class<? extends AppCompatActivityWrapper> activityClass, Bundle activityExtras, boolean finish) {
        Intent intent = new Intent(activity, activityClass);
        intent.putExtra(ACTIVITY_EXTRA, activityExtras);
        activity.startActivity(intent);
        if(finish){
            activity.finish();
        }
    }

    public static void replaceFragment(AppCompatActivity activity, Integer fragmentToReplace, Fragment fragment , String addToBackstackTag){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction().replace(fragmentToReplace, fragment);
        if(addToBackstackTag != null){
            transaction.addToBackStack(addToBackstackTag);
        }
        transaction.commit();
    }

    public static void displayFragment(AppCompatActivity activity, int containerId, Fragment fragment, String addToBackstackTag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction().add(containerId, fragment);
        if(addToBackstackTag != null){
            transaction.addToBackStack(addToBackstackTag);
        }
        transaction.commit();
    }

    public static void displayFragment(AppCompatActivity activity, int containerId, Fragment fragment, String fragmentTag, String addToBackstackTag) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction().add(containerId, fragment, fragmentTag);
        if(addToBackstackTag != null){
            transaction.addToBackStack(addToBackstackTag);
        }
        transaction.commit();
    }


}
