package com.cimara;

public class App{

    public static void main(String[] args) {
        ServerStr server = new ServerStr(5000);

        server.connetti();
        server.comunica();
    }

}
