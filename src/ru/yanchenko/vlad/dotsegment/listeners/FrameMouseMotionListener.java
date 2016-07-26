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
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
