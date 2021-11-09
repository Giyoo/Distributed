package com.guo.producer;

import com.guo.framwork.protocol.http.HttpServer;

public class Producer {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost",8080);
    }
}
