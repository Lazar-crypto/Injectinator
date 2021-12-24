package com.razal.example;

public class LoggerImpl2 implements Logger{
    @Override
    public void log(String msg) {
        System.out.println("Logged with LoggerImpl2:" + msg);
    }
}
