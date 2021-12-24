package com.razal.annotations;

import com.razal.configuration.InjectionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectMe {

    //by default every time you inject dependency you will get a new instance of it
    InjectionType value() default InjectionType.NEW;

}
