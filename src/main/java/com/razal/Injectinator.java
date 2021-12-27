package com.razal;

import com.razal.annotations.InjectMe;
import com.razal.configuration.InjectionType;
import com.razal.configuration.WireUp;
import com.razal.configuration.WireUpImpl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Injectinator {

    private static Injectinator instance;
    private final WireUp wireUp;
    private final Map<Class<?>,Object> singletons;

    private Injectinator(){
        wireUp = new WireUpImpl();
        singletons = new HashMap<>();
    }

    public static Injectinator getInstance() {
        if(instance == null)
            instance = new Injectinator();

        return instance;
    }

    public <T> void setWireUp(Class<T> baseClass, Class<? extends T> subClass){
        this.wireUp.configure(baseClass,subClass);
    }

    //returns object with all of its dependencies
    public <T> T getBuiltObject(Class<T> classToInjectTo) throws Exception {
        try {
            return injectViaFields(classToInjectTo);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private <T> T injectViaFields(Class<T> classToInjectTo) throws Exception {

        T instance = createInstance(classToInjectTo);

        for (Field field : classToInjectTo.getDeclaredFields()) {
            if(field.isAnnotationPresent(InjectMe.class)){
                field.setAccessible(true);
                if(field.getAnnotation(InjectMe.class).value() == InjectionType.SINGLETON){
                    field.set(instance,getSingleton(field.getType()));
                }else{
                    //field.set(instance,field.getType());
                    //field(instance in which i set this field,value i want to set)
                    //ali ja moram da ubacim i dependencije tog dependencija ako ih ima
                    field.set(instance, getBuiltObject(wireUp.getDependency(field.getType())));
                }
            }
        }
        return  instance;
    }

    private <T> T createInstance(Class<T> classToInjectTo) throws Exception {

        for (Object object : singletons.values()) {
            if(object.getClass() == classToInjectTo)
                return (T) object;
        }
        return classToInjectTo.getConstructor().newInstance();
    }

    private Object getSingleton(Class<?> type) throws Exception{
        if (!singletons.containsKey(type)) {
            singletons.put(type, getBuiltObject(wireUp.getDependency(type)));
        }
        return singletons.get(type);
    }

}
