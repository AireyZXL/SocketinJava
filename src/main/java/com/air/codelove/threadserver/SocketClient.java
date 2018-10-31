package com.air.codelove.threadserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 通过指定长度发送优化的客户端
 *
 * @author zxlei1
 * @date 2018/10/31 10:29
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {

        String host = "127.0.0.1";
        int port = 55533;
        Socket socket = new Socket(host, port);
        OutputStream os = socket.getOutputStream();
        String message = "Hello,Airey!";
        byte[] sendBytes = message.getBytes("utf-8");
        //将消息的长度优先发出去
        //os.write(sendBytes.length >> 8);
        // os.write(sendBytes.length);
        //然后将消息再次发出去
        os.write(sendBytes);
        os.flush();
        //==========此处重复发送一次，实际项目中为多个命名，此处只为展示用法
        message = "第二条消息";
        sendBytes = message.getBytes("UTF-8");
        //os.write(sendBytes.length >> 8);
        // os.write(sendBytes.length);
        os.write(sendBytes);
        os.flush();

        //==========此处重复发送一次，实际项目中为多个命名，此处只为展示用法
        message = "the third message!";
        sendBytes = message.getBytes("UTF-8");
        //os.write(sendBytes.length >> 8);
        // os.write(sendBytes.length);
        os.write(sendBytes);

        os.close();
        socket.close();

    }


}
