package com.example.InPostPW.services;

import java.util.Map;

public interface UserTokenProvider<T> {

    Map<String, String> provide(T t);
}
