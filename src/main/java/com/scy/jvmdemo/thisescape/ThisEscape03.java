package com.scy.jvmdemo.thisescape;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * this逃逸
 * 构造器中注册监听器
 */
public class ThisEscape03 {
    private final int value;

    public ThisEscape03() {
        //注册事件，会一直监听，当点击按键，会执行方法
        new MyKeyHandle(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped" + value);
            }
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed"+ value);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased"+ value);
            }
        });
        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = 10;
    }

    public static void main(String[] args) {
        new ThisEscape03();
    }
}

/**
 * 监听键盘的窗口
 */
class MyKeyHandle extends JFrame {
    MyKeyHandle(KeyListener keyListener) {
        super.setTitle("Welcome！");
        super.setSize(310, 210);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.addKeyListener(keyListener);
    }
}