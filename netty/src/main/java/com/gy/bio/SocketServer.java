package com.gy.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: SpringBoot-rocketmq
 * @description: BIO案例
 * @author: guoyong
 * @create: 2021-10-25 09:55
 **/
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true){
            System.out.println("等待连接！");
            //阻塞
            Socket clientSocket = serverSocket.accept();
            handler(clientSocket);
        }
    }

    private static void handler(Socket clientSocket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("准备read..");
        //接收客户但的数据，阻塞方法，没有数据可读时就阻塞
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("read完毕。。");
        if (read!=-1){
            System.out.println("接收到客户端的数据："+new String(bytes,0,read));
        }
        clientSocket.getOutputStream().write("HelloClient".getBytes());
        clientSocket.getOutputStream().flush();
    }
}
