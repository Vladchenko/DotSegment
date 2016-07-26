package ru.yanchenko.vlad.dotsegment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Drawing extends JPanel {

    private static Repository oRepository;

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

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.GRAY);
        oRepository.getLine().drawLine(g2);

        float[] dash2 = {1000f, 0f};
        g2.setStroke(
                new BasicStroke(4, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 0.0f, dash2, 0f)
        );

        g.setColor(Color.RED);
        oRepository.getDot().drawDot(g2);

        g.setColor(Color.green);
        oRepository.getSegment().drawSegment(g2);

    }
}
