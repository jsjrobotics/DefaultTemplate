package com.jsjrobotics.defaultTemplate.lifecycle.functional;


import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Wraps an object with a weak reference.
 * Each time get is called, the weak reference is checked and
 * removed from memory if the object it is holding is garbage collected.
 * Hence the programmer does not need to worry about collecting dead weak references
 * @param <Data> Any object
 */
public class WeakReferenceSupplier<Data> {
    private WeakReference<Data> mData;

    public WeakReferenceSupplier(Data data) {
        mData = new WeakReference<>(data);
    }

    /**
     * @return The context if it hasn't been garbage collected
     */
    public Optional<Data> get() {
        if (mData != null) {
            Data context = mData.get();
            if (context == null) {
                mData = null;
            }
            return Optional.ofNullable(context);
        }
        return Optional.empty();
    }

    public Data value() {
        return get().get();
    }

    public boolean isPresent() {
        return get().isPresent();
    }

    public void ifPresent(Receiver<Data> receiver) {
        get().ifPresent(receiver);
    }
}
