package com.example.InPostPW.services;

import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public interface UserTokenProvider<T> {

    String provide(T t);
}
