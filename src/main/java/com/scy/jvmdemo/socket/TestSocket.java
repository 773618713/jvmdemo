package com.scy.jvmdemo.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 参考以下博客：
 * https://blog.csdn.net/qq_25775675/article/details/107580298
 */
public class TestSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("step1: new ServerSocket(9090)");
        while (true) {
            Socket client = serverSocket.accept();//阻塞
            System.out.println("step2:client\t" + client.getPort());
            new Thread(() -> {
                InputStream in = null;
                try {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        String dataline = reader.readLine();//阻塞
                        if (null != dataline) {
                            System.out.println(dataline);
                        } else {
                            client.close();
                            break;
                        }
                    }
                    System.out.println("客户端断开");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
