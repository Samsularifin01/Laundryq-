/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import raven.effect.RippleEffect;
import shadow.ShadowRenderer;


public class menuitems extends JButton {

    public float getAnimate() {
        return animate;
    }

    public void setAnimate(float animate) {
        this.animate = animate;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSubMenuable() {
        return subMenuable;
    }

    public void setSubMenuable(boolean subMenuable) {
        this.subMenuable = subMenuable;
    }

    public int getSubMenuindex() {
        return subMenuindex;
    }

    public void setSubMenuindex(int subMenuindex) {
        this.subMenuindex = subMenuindex;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private RippleEffect rippleEffect;
    private BufferedImage shadow;
    private int shadowWidth;
    private int shadowSize=10;
    private int index;
    private boolean subMenuable;
   
    private float animate;
    // submenu
    private int subMenuindex;
    private int length; 
    
    public menuitems(String name, int index, boolean subMenuAble) {
        super(name);
        this.index=index;
        this.subMenuable=subMenuAble;
        setContentAreaFilled(false);
        setForeground(new Color(230, 230, 230));
        setHorizontalAlignment(SwingConstants.LEFT);
        setBorder(new EmptyBorder(9, 10, 9, 10));
        setFont(new Font(getFont().getName(), Font.BOLD, 16));
        rippleEffect=new RippleEffect(this);
        rippleEffect.setRippleColor(new Color(220,220,220));
    }
    
    private void createShadowImage(){
        int width = getWidth();
        int height = 5;
        BufferedImage img=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(0, 0, width, height));
        shadow=new ShadowRenderer(shadowSize, 0.2f, Color.BLACK).createShadow(img);
        g2.dispose();
    }
    
    public void initsubmenu(int subMenuindex, int length){
        this.subMenuindex=subMenuindex;
        this.length=length;
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new EmptyBorder(9, 10, 9, 10));
        setBackground(new Color(22, 119, 164));
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2=(Graphics2D)grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(length!=0){
            g2.setColor(new Color(255, 255, 255));
            if(subMenuindex==1){
                // first index
                g2.drawImage(shadow, -shadowSize, -20, null);
                g2.drawLine(18, 0, 18, getHeight());
                g2.drawLine(18, getHeight()/2, 26, getHeight()/2);
            }else if(subMenuindex==length-1){
            // last index
                g2.drawImage(shadow, -shadowSize, getHeight() - 6, null);
                g2.drawLine(18, 0, 18, getHeight()/2);
                g2.drawLine(18, getHeight()/2, 26, getHeight()/2);
            }else{
                g2.drawLine(18, 0, 18, getHeight());
                g2.drawLine(18, getHeight()/2, 26, getHeight()/2);
            }
        }else if(subMenuable){
            g2.setColor(getForeground());
            int arrowWidth=8;
            int arrowHeight=4;
            Path2D p=new Path2D.Double();
            p.moveTo(0, arrowHeight*animate);
            p.lineTo(arrowWidth / 2, (1f-animate)*arrowHeight);
            p.lineTo(arrowWidth, arrowHeight*animate);
            g2.translate(getWidth() - arrowWidth - 15, (getHeight() - arrowHeight) / 2);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.draw(p);
        }
        g2.dispose();
        rippleEffect.reder(grphcs, new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        
    }    

    @Override
    public void setBounds(int i, int il, int i2, int i3) {
        super.setBounds(i, il, i2, i3);
        createShadowImage();
    }
    
} 
