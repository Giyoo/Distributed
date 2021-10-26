package com.gy.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @program: SpringBoot-rocketmq
 * @description: BIO客户端
 * @author: guoyong
 * @create: 2021-10-25 11:41
 **/
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9000);
        //向服务端发送数据
        socket.getOutputStream().write("HelloServer".getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务端发送数据结束");
        byte[] bytes = new byte[1024];
        //接收服务端回传的数据
        socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据："+new String(bytes));
        socket.close();
    }
}
