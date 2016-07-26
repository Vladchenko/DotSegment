/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.yanchenko.vlad.dotsegment.generics;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 *
 * @author v.yanchenko
 */
public class Line {
    
    Dot dot1 = new Dot();
    Dot dot2 = new Dot();
    // Perpendicular line #1
    Dot dot1p = new Dot();
    Dot dot2p = new Dot();
    // Perpendicular line #2
    Dot dot3p = new Dot();
    Dot dot4p = new Dot();
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
    }
    
    public void computeDots(double x1, double y1, double x2, double y2) {
        
        double y;
        
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
        
    }
    
    public void computePerpendicularDots(double x1, double y1) {
        
    }
}
