package com.air.codelove.waitreceive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Socket双向通信客户端
 *
 * @author zxlei1
 * @date 2018/10/30 15:54
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 55534;
        Socket socket = new Socket(host, port);
        OutputStream os = socket.getOutputStream();
        String message = "Hello，Aiery~~~~~~~~~~";
        os.write(message.getBytes("utf-8"));
        //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = is.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, "utf-8"));
        }
        System.out.println("get message from server : " + sb);

        is.close();
        os.close();
        socket.close();


    }
}
