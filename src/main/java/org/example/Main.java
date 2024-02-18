package org.example;

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