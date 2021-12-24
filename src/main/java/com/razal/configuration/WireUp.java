package com.razal.configuration;

public interface WireUp {

    //wire up dependencies
    void configure();

    //get dependency
    <T> Class<? extends T> getDependency(Class<T> type);

}
