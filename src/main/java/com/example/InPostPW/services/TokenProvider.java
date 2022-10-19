package com.example.InPostPW.services;

import java.util.Map;

public interface TokenProvider<T> {
    Map<String, String> provide(T t);
}
