package ru.yanchenko.vlad.dotsegment.generics;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Dot {

    //** x ordinate of a dot (to represent it on a screen)
    protected double x = 0;
    //** y ordinate of a dot (to represent it on a screen)
    protected double y = 0;

    public Dot() {
    }

    /**
     * Defining a coordinate of a Dot randomly or directly assigning the values
     */
    public Dot(int x, int y, boolean random) {
        if (random) {
            this.x = (Math.random() * (x));
            this.y = (Math.random() * (y));
        } else {
            this.x = x;
            this.y = y;
        }
//        shp = new Point2D.Double(x, y);
    }

    /**
     * Defining a coordinate of a Dot at random within a size of a screen,
     * having a size of some object (i.e. ball's image) negated. This is done,
     * for a ball's image could not fall outside of a screen's area.
     */
    public Dot(int x, int y, boolean random, int shiftX, int shiftY) {
        if (random) {
            this.x = (Math.random() * (x - shiftX));
            this.y = (Math.random() * (x - shiftY));
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public void drawDot(Graphics2D g2, int size) {
        g2.fillOval((int)this.x - size / 2, (int)this.y - size / 2, size, size);
    }

    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    //</editor-fold>
}
