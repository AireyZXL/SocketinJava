package com.air.codelove.onlysend;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Socket基本通信客户端
 *
 * @author zxlei1
 * @date 2018/10/30 14:40
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {

        //要连接的服务端的IP地址和端口
        String host = "127.0.0.1";
        int port = 55533;
        Socket socket = new Socket(host, port);
        OutputStream ops = socket.getOutputStream();
        String message = "Hello,Airey!";
        ops.write(message.getBytes("utf-8"));
        ops.close();
        socket.close();


    }
}
