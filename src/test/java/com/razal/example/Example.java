package com.razal.example;

import com.razal.annotations.InjectMe;

public class Example {

    @InjectMe
    private Logger logger;

    public void log(String msg){
        logger.log(msg);
    }

}
