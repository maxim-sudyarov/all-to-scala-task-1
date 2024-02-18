package org.example;

import org.example.service.Handler;
import org.example.service.HandlerImpl;

public class Main {
    private static final Handler handler = new HandlerImpl();
    public static void main(String[] args) {
        handler.performOperation();
        handler.performOperation();
        handler.performOperation();
        handler.performOperation();
        handler.performOperation();
        handler.performOperation();
    }
}