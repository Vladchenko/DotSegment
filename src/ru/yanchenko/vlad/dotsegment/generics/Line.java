/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.yanchenko.vlad.dotsegment.generics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 *
 * @author v.yanchenko
 */
public class Line {
    
    private Dot dot1 = new Dot();
    private Dot dot2 = new Dot();
    // Perpendicular line #1
    private Dot dot1p = new Dot();
    private Dot dot2p = new Dot();
    // Perpendicular line #2
    private Dot dot3p = new Dot();
    private Dot dot4p = new Dot();
    // Dot for a line representing a shortest distance
    private Dot dot5 = new Dot();
    // Subject dot
    private Dot dot6 = new Dot();
    Shape shp;
    
    public Line() { }
    
    public Line(double x1, double y1, double x2, double y2) {
        dot1.x = x1;
        dot1.y = y1;
        dot2.x = x2;
        dot2.y = y2;
        shp = new Line2D.Double(dot1.x, dot1.y, dot2.x, dot2.y);
    }
    
    public void drawLine(Graphics2D g2) {
        shp = new Line2D.Double(dot1.x, dot1.y, dot2.x, dot2.y);
        g2.draw(shp);
        shp = new Line2D.Double(dot1p.x, dot1p.y, dot2p.x, dot2p.y);
        g2.draw(shp);
        shp = new Line2D.Double(dot3p.x, dot3p.y, dot4p.x, dot4p.y);
        g2.draw(shp);
        g2.setColor(new Color(150,150,255));
        shp = new Line2D.Double(dot5.x, dot5.y, dot6.x, dot6.y);
        g2.draw(shp);
    }
    
    public void computeDots(double x1, double y1, double x2, double y2, double x3, double y3) {
        
        double y;
        dot6.setX(x3);
        dot6.setY(y3);
        
        Dot dot = new Dot();
        y = (0 - x1) / (x2 - x1) * (y2 - y1) + y1;
        dot.setX(0);
        dot.setY(y);
        dot1 = dot;
        
        dot = new Dot();
        y = (1920 - x1) / (x2 - x1) * (y2 - y1) + y1;
        dot.setX(1920);
        dot.setY(y);
        dot2 = dot;
        
        shp = new Line2D.Double(dot1.x, dot1.y, dot2.x, dot2.y);
        
        // Computing dots for perpendicular
        dot1p.setX(0);
        dot1p.y = y1 - (dot1p.getX() - x1) * (x2 - x1) / (y2 - y1);
        
        dot2p.setX(1920);
        dot2p.y = y1 - (dot2p.getX() - x1) * (x2 - x1) / (y2 - y1);
        
        dot3p.setX(0);
        dot3p.y = y2 - (dot3p.getX() - x2) * (x2 - x1) / (y2 - y1);
        
        dot4p.setX(1920);
        dot4p.y = y2 - (dot4p.getX() - x2) * (x2 - x1) / (y2 - y1);
        
        
        // Checking if a subject dot is within a perpendicular lines
        
//        dot5.setX(dot6.getX());
//        dot5.setY(y2 - (dot6.getY() - x2) * (x2 - x1) / (y2 - y1));
//        
//        dot5.setX(dot6.getX());
//        dot5.setY(y1 - (dot6.getY() - x1) * (x2 - x1) / (y2 - y1));
        
        double distance1 = Math.sqrt(
                Math.pow(x1 - x3, 2)
                + Math.pow(y1 - y3, 2)
        );
        double distance2 = Math.sqrt(
                Math.pow(x2 - x3, 2)
                + Math.pow(y2 - y3, 2)
        );
//        System.out.println(distance1 + " " + distance2);
        if (distance1 < distance2) {
            dot5.setX(x1); 
            dot5.setY(y1);
        } else if (distance1 > distance2) {
            dot5.setX(x2); 
            dot5.setY(y2);
        } else if (distance1 == distance2) {
            
        }
        
    }
    
    public void computePerpendicularDots(double x1, double y1) {
        
    }

    //<editor-fold defaultstate="collapsed" desc="getters & setters">
    public Dot getDot1() {
        return dot1;
    }
    
    public void setDot1(Dot dot1) {
        this.dot1 = dot1;
    }
    
    public Dot getDot2() {
        return dot2;
    }
    
    public void setDot2(Dot dot2) {
        this.dot2 = dot2;
    }
    
    public Dot getDot1p() {
        return dot1p;
    }
    
    public void setDot1p(Dot dot1p) {
        this.dot1p = dot1p;
    }
    
    public Dot getDot2p() {
        return dot2p;
    }
    
    public void setDot2p(Dot dot2p) {
        this.dot2p = dot2p;
    }
    
    public Dot getDot3p() {
        return dot3p;
    }
    
    public void setDot3p(Dot dot3p) {
        this.dot3p = dot3p;
    }
    
    public Dot getDot4p() {
        return dot4p;
    }
    
    public void setDot4p(Dot dot4p) {
        this.dot4p = dot4p;
    }
//</editor-fold>
}
