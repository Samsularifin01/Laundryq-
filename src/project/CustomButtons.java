/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Mahayoga
 */
public class CustomButtons extends JButton {
    private int borderRadius = 20;
    private String textButton = "JButton";
    private int fontSize = 20;
    private int boldSize = 0;
    private Color textColor =  new Color(0, 0, 0);
    private Color bgColor =  new Color(255, 255, 255);
    private Color mouseEntered;
    private Color mousePressed;
    private Color mouseExited;
    private int i = 0;
    
    public CustomButtons() {
        this.setText("");
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(200, 80));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setBackgroundColor(mouseEntered);
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                setBackgroundColor(mouseExited);
            }
            
            @Override
            public void mousePressed(MouseEvent evt) {
                setBackgroundColor(mousePressed);
            }
            
            @Override
            public void mouseReleased(MouseEvent evt) {
                setBackgroundColor(mouseEntered);
            }
        });
    }
    
    private Color makeFinalColorEvent(Color originalColor, int i) {
        if(i >= 2) {
            return null;
        }
        String color = String.valueOf(bgColor);
        System.out.println(color);
        String r1 = color.split("java.awt.Color")[1];
        String red = r1.split("=")[1].split(",")[0];
        String green = r1.split(",")[1].split("=")[1];
        String blue = r1.split("b=")[1].split("]")[0];

        int Red = Integer.parseInt(red);
        int Green = Integer.parseInt(green);
        int Blue = Integer.parseInt(blue);
        
        // Mouse Entered
        if((Red + 20) > 255) {
            Red -= 10;
        } else {
            Red += 20;
        }
        if((Green + 20) > 255) {
            Green -= 10;
        } else {
            Green += 20;
        }
        if((Blue + 20) > 255) {
            Blue -= 10;
        } else {
            Blue += 20;
        }
        setMouseEnteredColor(new Color(Red, Green, Blue));
        setMouseExitedColor(bgColor);
        
        //Mouse Pressed
        if(Red < 20) {
            Red = 0;
        } else {
            Red -= 20;
        }
        if(Green < 20) {
            Green = 0;
        } else {
            Green -= 20;
        }
        if(Blue < 20) {
            Blue = 0;
        } else {
            Blue -= 20;
        }
        
        setMousePressedColor(new Color(Red, Green, Blue));
        return null;
    }
    
    private void setMouseExitedColor(Color newColor) {
        this.mouseExited = newColor;
    }
    
    private void setMousePressedColor(Color newColor) {
        this.mousePressed = newColor;
    }
    
    private void setMouseEnteredColor(Color newColor) {
        this.mouseEntered = newColor;
    }
    
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.bgColor = backgroundColor;
    }
    
    public void setTheText(String text) {
        this.textButton = text;
    }
    
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    } 
    
    public void setTextBold(int boldSize) {
        this.boldSize = boldSize;
    }
    
    public void setTextColor(Color color) {
        this.textColor = color;
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(!this.isEnabled()) {
            g2.setColor(new Color(204, 204, 204));
        } else {
            g2.setColor(bgColor);
        }
        i++;
        makeFinalColorEvent(g2.getColor(), i);
        g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), borderRadius, borderRadius);
        g2.setColor(textColor);
        
        FontMetrics metrics = g2.getFontMetrics(new Font("", boldSize, fontSize));
        int x = (this.getWidth() / 2) - (metrics.stringWidth(textButton) / 2);
        int y = (this.getHeight() / 2) + ((metrics.getHeight() / 2) / 2);
        g2.setFont(new Font("", boldSize, fontSize));
        g2.drawString(textButton, x, y);
    }
}
