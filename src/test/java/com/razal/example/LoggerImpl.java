package com.razal.example;

import com.razal.annotations.InjectMe;

public class LoggerImpl implements Logger{

    @InjectMe
   Message message;

    @Override
    public void log(String msg) {
        System.out.println("Logged with LoggerImpl");
        message.getMsg(msg);
    }


}
