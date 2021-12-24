package com.razal;

import com.razal.annotations.InjectMe;
import com.razal.configuration.WireUp;
import com.razal.configuration.WireUpImpl;

import java.lang.reflect.Field;

public class Injectinator {

    private static Injectinator instance;
    private WireUp wireUp;

    private Injectinator(){
        wireUp = new WireUpImpl();
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
        return injectViaFields(classToInjectTo);
    }

    private <T> T injectViaFields(Class<T> classToInjectTo) throws Exception {
        T instance = classToInjectTo.getConstructor().newInstance();
        for (Field field : classToInjectTo.getDeclaredFields()) {
            if(field.isAnnotationPresent(InjectMe.class)){
                field.setAccessible(true);
                //field(instance in which i set this field,value i want to set)
                //field.set(instance,field.getType());
                //ali ja moram da ubacim i dependencije tog dependencija ako ih ima
                field.set(instance, getBuiltObject(wireUp.getDependency(field.getType())));
            }
        }
        return  instance;
    }
}
