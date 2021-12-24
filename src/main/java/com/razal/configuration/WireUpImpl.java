package com.razal.configuration;

import java.util.HashMap;
import java.util.Map;

public class WireUpImpl implements WireUp{

    Map<Class<?>,Class<?>> dependencies = new HashMap<>();

    @Override
    public <T> void configure(Class<T> baseClass, Class<? extends T> subClass) {
        dependencies.put(baseClass,subClass.asSubclass(baseClass));
    }

    @Override
    public <T> Class<? extends T> getDependency(Class<T> baseClass) {
        Class<?> dependency = dependencies.get(baseClass);
        if(dependency == null)
            throw  new IllegalArgumentException("No dependencies registered for type :" + baseClass);

        return dependency.asSubclass(baseClass);
    }
}
