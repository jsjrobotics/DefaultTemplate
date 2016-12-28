package com.jsjrobotics.defaultTemplate.lifecycle.functional;

public class Optional<T> {
    public static final Optional EMPTY = new Optional<>(null);
    private final T mData;

    private Optional(T value) {
        mData = value;
    }

    public static <R> Optional<R> of(R value){
        if(value == null){
            return Optional.EMPTY;
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
            return Optional.EMPTY;
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
