/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.yanchenko.vlad.dotsegment.listeners;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import ru.yanchenko.vlad.dotsegment.Repository;

/**
 *
 * @author Влад
 */
public class FrameMouseWheelListener implements MouseWheelListener {

    private Repository oRepository = Repository.getInstance();

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        //<editor-fold defaultstate="collapsed" desc="When no ALT / CTRL / SHIFT keys pressed">
        if (!oRepository.isKeyAlt()
                && !oRepository.isKeyCtrl()
                && !oRepository.isKeyShift()) {
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="When CTRL is pressed">
        if (oRepository.isKeyCtrl()) {
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="When SHIFT is pressed">
        if (oRepository.isKeyShift()) {
        }
        //</editor-fold>

    }
}
