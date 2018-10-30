package com.air.codelove.waitreceive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket双向通信服务端
 *
 * @author zxlei1
 * @date 2018/10/30 14:57
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {

        int port = 55534;
        ServerSocket server = new ServerSocket(port);

        System.out.println("server将一直等待连接的到来");
        Socket socket = server.accept();
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = is.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, "utf-8"));
        }
        System.out.println("get message from client : " + sb);

        OutputStream os = socket.getOutputStream();

        String message = "Hello,Client! I get message .";
        os.write(message.getBytes("utf-8"));

        is.close();
        os.close();
        socket.close();
        server.close();

    }
}
