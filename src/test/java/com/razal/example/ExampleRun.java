package com.razal.example;

import com.razal.Injectinator;

public class ExampleRun {

    public static void main(String[] args) throws Exception {

        Injectinator injectinator = Injectinator.getInstance();

        injectinator.setWireUp(Message.class,MessageImpl.class);
        injectinator.setWireUp(Logger.class,LoggerImpl.class);


        Example example = injectinator.getBuiltObject(Example.class);
        example.log("nesto");

    }

}
