package com.air.codelove.waitreceive2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通过指定长度发送优化的服务端
 *
 * @author zxlei1
 * @date 2018/10/31 10:29
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        //监听指定的窗口
        int port = 55533;
        ServerSocket server = new ServerSocket(port);

        System.out.println("server将一直等待连接的到来");
        Socket socket = server.accept();
        //建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
        InputStream is = socket.getInputStream();
        byte[] bytes;
        //因为可以复用Socket且能判断长度，所以可以一个socket用到底
        while (true) {
            //首先读取2个字节表示的长度
            int first = is.read();
            //如果读取的值为-1 说明到了流的末尾，Socket已经被关闭了，此时将不能再去读取
            if (first == -1) {
                break;
            }
            int second = is.read();
            int length = (first << 8) + second;
            //然后构造一个指定长的byte数组
            bytes = new byte[length];
            //然后读取指定长度的消息即可
            is.read(bytes);
            System.out.println("get message from client ：" + new String(bytes, "utf-8"));
        }

        is.close();
        socket.close();
        server.close();

    }
}
