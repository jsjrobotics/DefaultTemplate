package com.jsjrobotics.defaultTemplate.lifecycle.functional;


public interface Function<T, R> {
    R call (T data);
}
