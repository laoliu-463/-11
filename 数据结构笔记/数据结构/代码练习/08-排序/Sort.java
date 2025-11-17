package com.wei.Christmas;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class merryChristmas extends JFrame{
    public merryChristmas(){
        setSize(800,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void paint(Graphics g){
        g.setColor(Color.green);
        int x[] = {350,400,450};
        int y[] = {200,150,200};
//  第一个树冠
        int x1[] = {320,400,480};
        int y1[] = {230,180,230};
//  第二个树冠
        int x2[] = {290,400,510};
        int y2[] = {260,210,260};
//  第三个树冠
        int x3[] = {260,400,540};
        int y3[] = {290,240,290};
//  第四个树冠
        int x4[] = {230,400,570};
        int y4[] = {320,270,320};
//  第五个树冠

        int x5[] = {400,410,405,412,403,400,397,388,395,390};
        int y5[] = {150,155,145,143,140,133,140,143,145,155};
////  star
        Polygon polygon = new Polygon(x,y,3);
        Polygon polygon1 = new Polygon(x1,y1,3);
        Polygon polygon2 = new Polygon(x2,y2,3);
        Polygon polygon3 = new Polygon(x3,y3,3);
        Polygon polygon4 = new Polygon(x4,y4,3);
        Polygon polygon5 = new Polygon(x5,y5,10);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.drawPolygon(polygon);
        g.fillPolygon(polygon);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.drawPolygon(polygon1);
        g.fillPolygon(polygon1);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.drawPolygon(polygon2);
        g.fillPolygon(polygon2);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.drawPolygon(polygon3);
        g.fillPolygon(polygon3);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.drawPolygon(polygon4);
        g.fillPolygon(polygon4);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//矩形树桩
        g.setColor(Color.orange);
        g.drawRect(390,320,20,60);
        g.fillRect(390,320,20,60);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.drawPolygon(polygon5);
        g.fillPolygon(polygon5);
    }

    public static void main(String[] args) {
        new merryChristmas().setVisible(true);
    }
}