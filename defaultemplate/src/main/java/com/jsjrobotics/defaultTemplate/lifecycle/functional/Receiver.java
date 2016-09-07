package com.jsjrobotics.defaultTemplate.lifecycle.functional;

public interface Receiver<T> {
    void accept(T data);
}
