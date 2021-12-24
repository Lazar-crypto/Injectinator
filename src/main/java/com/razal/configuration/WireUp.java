package com.razal.configuration;

public interface WireUp {

    //wire up dependencies(interface with its impl)
    <T> void configure(Class<T> baseClass, Class<? extends T> subClass);

    //get dependency
    <T> Class<? extends T> getDependency(Class<T> type);

}
