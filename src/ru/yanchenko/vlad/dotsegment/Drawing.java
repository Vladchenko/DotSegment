package ru.yanchenko.vlad.dotsegment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Drawing extends JPanel {

    private static Repository oRepository;
    int[] xPoints;
    int[] yPoints;

    /**
     * Constructor is made private, for it could not be instantiated anywhere
     * besides "getInstance" method.
     */
    private Drawing() {
    }

    public static synchronized Drawing getInstance(Repository Repository_) {
        oRepository = Repository_;
        if (Repository_.getoDrawing() == null) {
            Repository_.setoDrawing(new Drawing());
        }
        return Repository_.getoDrawing();
    }

    @Override
    public void paintComponent(Graphics g) {

        /**
         * Hiding mouse cursor is turned on in Repository.initializeData() by
         * uncommenting a respective code.
         */
        //** Erasing a previously drawn data 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        xPoints = new int[]{
            (int) oRepository.getLines().getPerpendicular().getDot1().getX(),
            (int) oRepository.getLines().getPerpendicular2().getDot1().getX(),
            (int) oRepository.getLines().getPerpendicular2().getDot2().getX(),
            (int) oRepository.getLines().getPerpendicular().getDot2().getX()};
        yPoints = new int[]{
            (int) oRepository.getLines().getPerpendicular().getDot1().getY(),
            (int) oRepository.getLines().getPerpendicular2().getDot1().getY(),
            (int) oRepository.getLines().getPerpendicular2().getDot2().getY(),
            (int) oRepository.getLines().getPerpendicular().getDot2().getY()};

        g.setColor(new Color(2,20,2));
        g.fillPolygon(xPoints, yPoints, 4);        

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.GRAY);
        oRepository.getLines().drawLines(g2);

        if (oRepository.getLines().isInside()) {
            g.setColor(Color.GREEN);
            oRepository.getDot().drawDot(g2, 8);
        } else {
            g.setColor(Color.RED);
            oRepository.getDot().drawDot(g2, 4);
        }

        float[] dash2 = {1000f, 0f};
        g2.setStroke(
                new BasicStroke(4, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 0.0f, dash2, 0f)
        );

        g.setColor(Color.green);
        oRepository.getSegment().drawSegment(g2);



    }
}
