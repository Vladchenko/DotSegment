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
public class Segment {
    
    private Dot dot1 = new Dot();
    private Dot dot2 = new Dot();
    private Shape shp;
    
    public Segment() { }
    
    public Segment(double x1, double y1, double x2, double y2) {
        dot1.x = x1;
        dot1.y = y1;
        dot2.x = x2;
        dot2.y = y2;
        shp = new Line2D.Double(dot1.x, dot1.y, dot2.x, dot2.y);
    }
    
    public void drawSegment(Graphics2D g2) {
        shp = new Line2D.Double(dot1.x, dot1.y, dot2.x, dot2.y);
        g2.draw(shp);
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
//</editor-fold>
    
}
