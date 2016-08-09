package com.spookyrobotics.defaultproject.lifecycle.functional;

public interface Receiver<T> {
    void accept(T data);
}
