/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.yanchenko.vlad.dotsegment.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import ru.yanchenko.vlad.dotsegment.Repository;

/**
 *
 * @author Влад
 */
public class FrameMouseMotionListener implements MouseMotionListener {

    private Repository oRepository = Repository.getInstance();

    //** When mouse cursor dragged
    @Override
    public void mouseDragged(MouseEvent e) {

        int b1 = MouseEvent.BUTTON1_DOWN_MASK;
        int b2 = MouseEvent.BUTTON3_DOWN_MASK;

        if (!oRepository.isKeyAlt()
                && !oRepository.isKeyCtrl()
                && !oRepository.isKeyShift()) {
            if ((e.getModifiersEx() & (b1 | b2)) == b1) {
                oRepository.getSegment().getDot1().setX(e.getX());
                oRepository.getSegment().getDot1().setY(e.getY());
            }
            if ((e.getModifiersEx() & (b1 | b2)) == b2) {
                oRepository.getSegment().getDot2().setX(e.getX());
                oRepository.getSegment().getDot2().setY(e.getY());
            }

        }

        //<editor-fold defaultstate="collapsed" desc="When CTRL is pressed">
        if (oRepository.isKeyCtrl()) {
            oRepository.getDot().setX(e.getX());
            oRepository.getDot().setY(e.getY());
        }
        //</editor-fold>

        oRepository.getLine().computeDots(
                oRepository.getSegment().getDot1().getX(),
                oRepository.getSegment().getDot1().getY(),
                oRepository.getSegment().getDot2().getX(),
                oRepository.getSegment().getDot2().getY(),
                oRepository.getDot().getX(),
                oRepository.getDot().getY()
        );

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
