package com.lotus.rpc.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class RpcServiveApiStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RpcServerProvider.class);
        context.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}