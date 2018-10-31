package com.air.codelove.threadserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池代码服务端
 *
 * @author zxlei1
 * @date 2018/10/31 16:04
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        int port = 55533;
        ServerSocket server = new ServerSocket(port);
        System.out.println("server将一直等待连接的到来");

        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        while (true) {
            final Socket socket = server.accept();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                        InputStream inputStream = socket.getInputStream();
                        byte[] bytes = new byte[1024];
                        int len;
                        StringBuilder sb = new StringBuilder();
                        while ((len = inputStream.read(bytes)) != -1) {
                            // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                            sb.append(new String(bytes, 0, len, "UTF-8"));
                        }
                        System.out.println("get message from client: " + sb);
                        inputStream.close();
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.submit(runnable);
        }


    }

}
