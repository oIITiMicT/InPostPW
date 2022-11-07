package com.example.InPostPW.services;

public interface UserTokenProvider<T> {

    String provide(T t);
}
