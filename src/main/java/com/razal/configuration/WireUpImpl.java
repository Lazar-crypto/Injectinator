package com.razal.configuration;

public class WireUpImpl implements WireUp{

    @Override
    public void configure() {

    }

    @Override
    public <T> Class<? extends T> getDependency(Class<T> type) {
        return null;
    }
}
