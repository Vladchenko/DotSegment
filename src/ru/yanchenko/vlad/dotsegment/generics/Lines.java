/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.yanchenko.vlad.dotsegment.generics;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * @author v.yanchenko
 */
public class Lines {

    // Says if a dot is inside of two lines
    private boolean inside;
    // Distance from a subject dot to a line1
    private double distanceToLine1;
    // Distance from a subject dot to a line2
    private double distanceToLine2;
    private Line lineThroughSegment = new Line();
    // Perpendicular for a first dot of a segment
    private Line perpendicular = new Line();
    // Perpendicular for a second dot of a segment
    private Line perpendicular2 = new Line();
    // Line representing a shortest distance btw subject dot and a closest segment's dot
    private Line connectionLine = new Line();
    private Dot dot5 = new Dot();
    // Subject dot
    private Dot dotSubject = new Dot();
    private Shape shp;

    public Lines() {
    }

    public void drawLines(Graphics2D g2) {
        // Lines through segment
        drawLine2D(g2, lineThroughSegment.getDot1().getX(),
                lineThroughSegment.getDot1().getY(),
                lineThroughSegment.getDot2().getX(),
                lineThroughSegment.getDot2().getY());
        // Perpendicular line through first segment dot
        drawLine2D(g2, perpendicular.getDot1().getX(),
                perpendicular.getDot1().getY(),
                perpendicular.getDot2().getX(),
                perpendicular.getDot2().getY());
        // Perpendicular line through second segment dot
        drawLine2D(g2, perpendicular2.getDot1().getX(),
                perpendicular2.getDot1().getY(),
                perpendicular2.getDot2().getX(),
                perpendicular2.getDot2().getY());
        g2.setColor(new Color(50, 30, 30));
        // Lines from subject dot to a closest segment dot
        drawLine2DDashed(g2, dot5.x, dot5.y, dotSubject.x, dotSubject.y);
    }

    private void drawLine2D(Graphics2D g2, double x1, double y1, double x2, double y2) {
        g2.draw(new Line2D.Double(x1, y1, x2, y2));
    }

    private void drawLine2DDashed(Graphics2D g2, double x1, double y1, double x2, double y2) {
        float[] dash2 = {5f, 8f};
        g2.setStroke(
                new BasicStroke(2, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 6.0f, dash2, 0f)
        );
        // Lines from subject dot to a closest segment dot
        drawLine2D(g2, x1, y1, x2, y2);
    }

    public void computeDots(double x1, double y1, double x2, double y2, double x3, double y3) {

//        double y;
        dotSubject.setX(x3);
        dotSubject.setY(y3);

        computeLineTroughSegmentDots(x1, y1, x2, y2);

        shp = new Line2D.Double(
                lineThroughSegment.getDot1().getX(),
                lineThroughSegment.getDot1().getY(),
                lineThroughSegment.getDot2().getX(),
                lineThroughSegment.getDot2().getY());

        double distance1 = Math.sqrt(
                Math.pow(x1 - x3, 2)
                        + Math.pow(y1 - y3, 2)
        );
        double distance2 = Math.sqrt(
                Math.pow(x2 - x3, 2)
                        + Math.pow(y2 - y3, 2)
        );

        if (distance1 < distance2) {
            dot5.setX(x1);
            dot5.setY(y1);
        } else if (distance1 > distance2) {
            dot5.setX(x2);
            dot5.setY(y2);
        } else if (distance1 == distance2) {

        }

//        computePerpendicularDots(x1,y1,x2,y2);
//        computeDotToLine1Distance(x1,y1,x2,y2);
//        computeDotToLine2Distance(x1,y1,x2,y2);

    }

    public void computeLineTroughSegmentDots(double x1, double y1, double x2, double y2) {

        lineThroughSegment.getDot1().setX(0);
        lineThroughSegment.getDot1().setY((0 - x1) / (x2 - x1) * (y2 - y1) + y1);

        lineThroughSegment.getDot2().setX(1920);
        lineThroughSegment.getDot2().setY((1920 - x1) / (x2 - x1) * (y2 - y1) + y1);
    }

    public void computePerpendicularDots(double x1, double y1, double x2, double y2) {

        perpendicular.getDot1().setX(0);
        perpendicular.getDot1().setY(y1 - (perpendicular.getDot1().getX() - x1)
                * (x2 - x1) / (y2 - y1));

        perpendicular.getDot2().setX(1920);
        perpendicular.getDot2().setY(y1
                - (perpendicular.getDot2().getX() - x1) * (x2 - x1) / (y2 - y1));

        perpendicular2.getDot1().setX(0);
        perpendicular2.getDot1().setY(y2
                - (perpendicular2.getDot1().getX() - x2) * (x2 - x1) / (y2 - y1));

        perpendicular2.getDot2().setX(1920);
        perpendicular2.getDot2().setY(y2
                - (perpendicular2.getDot2().getX() - x2) * (x2 - x1) / (y2 - y1));
    }

    public void computeDotToLine1Distance(double x1, double y1, double x2, double y2) {

        setDistanceToLine1(((y2 - y1) * dotSubject.getX() - (x2 - x1) * dotSubject.getY()
                + x2 * y1 - y2 * x1) / Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
    }

    public void computeDotToLine2Distance(double x1, double y1, double x2, double y2) {

        setDistanceToLine2(((y2 - y1) * dotSubject.getX() - (x2 - x1) * dotSubject.getY()
                + x2 * y1 - y2 * x1) / Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
    }

    // Checking if a dot resides between the two lines
    public void checkSectorBelonging() {
        if (distanceToLine1 < 0
                && distanceToLine2 > 0
                || distanceToLine1 > 0
                && distanceToLine2 < 0) {
            inside = true;
        } else {
            inside = false;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="getters & setters">

    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }

    public Line getLineThroughSegment() {
        return lineThroughSegment;
    }

    public void setLineThroughSegment(Line lineThroughSegment) {
        this.lineThroughSegment = lineThroughSegment;
    }

    public Line getPerpendicular() {
        return perpendicular;
    }

    public void setPerpendicular(Line perpendicular) {
        this.perpendicular = perpendicular;
    }

    public Line getPerpendicular2() {
        return perpendicular2;
    }

    public void setPerpendicular2(Line perpendicular2) {
        this.perpendicular2 = perpendicular2;
    }

    public double getDistanceToLine1() {
        return distanceToLine1;
    }

    public void setDistanceToLine1(double distanceToLine1) {
        this.distanceToLine1 = distanceToLine1;
    }

    public double getDistanceToLine2() {
        return distanceToLine2;
    }

    public void setDistanceToLine2(double distanceToLine2) {
        this.distanceToLine2 = distanceToLine2;
    }
//</editor-fold>
}
