/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextRPG;

/**
 *
 * @author asus
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Other {

    private AudioPlay au;
    private JButton btnStop;

    public Other(AudioPlay aupl, JFrame f){
        this.au = aupl;
        btnStop = new JButton("Stop");
        f.add(btnStop);
        btnStop.addActionListener(new  ActionListener() {
           public void actionPerformed(ActionEvent e) {
                au.stopSound();                       
           }


        });
    }

}
