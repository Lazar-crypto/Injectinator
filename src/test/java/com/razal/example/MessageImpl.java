package com.razal.example;

public class MessageImpl implements Message{
    @Override
    public void getMsg(String msg) {
        System.out.println("MessageImpl" + msg);
    }
}
