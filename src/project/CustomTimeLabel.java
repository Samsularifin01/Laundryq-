/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.awt.Robot;
import java.time.LocalTime;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class CustomTimeLabel extends JLabel implements Runnable {
    String time = "23:59:59";
    public CustomTimeLabel() {
        this.setText(time);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            LocalTime time = LocalTime.now();
            String[] arr = String.valueOf(time).split(":");
            try {
                this.setText(arr[0] + " : " + arr[1] + " : " + arr[2].split("")[0] + arr[2].split("")[1]);
                Robot r = new Robot();
                r.delay(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
