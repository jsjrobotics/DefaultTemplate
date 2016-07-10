package com.spookyrobotics.defaultproject.functional;

public interface Receiver<T> {
    void accept(T data);
}
