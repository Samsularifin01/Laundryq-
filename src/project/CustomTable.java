/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
//simport javax.swing.JTable;

/**
 *
 * @author User
 */
public class CustomTable extends JTable{
    public DefaultTableModel model = new DefaultTableModel();
    
    public CustomTable() {
        //setBackgroundColor(Color.white);
        setRowHeight(30);
        this.setEditingRow(1);
        this.setEditingRow(ERROR);
        this.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean blnl, int i, int i1) {
                header h = new header(o + "");
                return h; //Return Label
            }
        });
        this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component theCom = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                theCom.setBackground(Color.WHITE);
                theCom.setForeground(Color.BLACK);
                if(isSelected) {
                    theCom.setBackground(new Color(71, 71, 71));
                    theCom.setForeground(Color.WHITE);
                } else {
                    theCom.setBackground(Color.WHITE);
                    theCom.setForeground(Color.BLACK);
                }
                this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
                this.setFont(new Font("Monospace", Font.ROMAN_BASELINE, 12));
                return theCom;
            }
        });
    }
    
    public void setBackgroundColor(Color color) {
        this.setBackground(color);
    }
    
    private class header extends JLabel {
        public header(String text) {
            super(text);
            this.setOpaque(true);
            this.setBackground(new Color(25, 192, 236));
            this.setForeground(Color.WHITE);
            this.setFont(new Font(null, Font.BOLD, 15));
            this.setBorder(new EmptyBorder(10, 5, 10, 5));
        }
    }
}
