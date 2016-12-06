package com.jsjrobotics.defaultTemplate.lifecycle.functional;

public class Optional<T> {
    private final T mData;

    private Optional(T value) {
        mData = value;
    }

    public static <R> Optional<R> of(R value){
        if(value == null){
            return empty();
        }
        return new Optional<>(value);
    }

    public void ifPresent(Receiver<T> r){
        if(mData != null){
            r.accept(mData);
        }
    }

    public T get(){
        if(!isPresent()){
            throw new IllegalArgumentException("optional not present");
        }
        return mData;
    }

    public static <T> Optional<T> ofNullable(T o) {
        if(o == null){
            return new Optional<T>(null);
        }
        return Optional.of(o);
    }

    public boolean isPresent(){
        return mData != null;
    }

    public static <T> Optional<T> empty() {
        return Optional.ofNullable(null);
    }


    public T or(T other) {
        if (isPresent()){
            return get();
        }
        return other;
    }
}
