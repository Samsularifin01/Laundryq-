/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import java.awt.Component;
import java.awt.MenuItem;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author ALYA GHANIA
 */
public class menuanimasi {
    public static void showMenu(Component component, menuitems item, MigLayout layout, boolean show) {
        int height=component.getPreferredSize().height;
        Animator animator=new Animator(300, new TimingTargetAdapter(){
            @Override
            public void timingEvent (float fraction){
                float f = show ? fraction : 1f - fraction;
                layout.setComponentConstraints(component, "h " + height * f + "!");
                item.setAnimate(f);
                component.revalidate();
                item.repaint();
            }
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        animator.start();
    }
       
}
