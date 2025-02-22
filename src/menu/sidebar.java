/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class sidebar extends JComponent {

    public menuevent getEvent() {
        return event;
    }

    public void setEvent(menuevent event) {
        this.event = event;
    }

    private menuevent event;
    private MigLayout layout;
    private String[][] menuitems = new String[][]{
        {"Dashboard"},
        {"Data Barang"},
        {"Data Karyawan"},
        {"Transaksi"},
        {"Laporan", "Pemasukan", "Pengeluaran"}
    };
    private String[][] menuitemsK = new String[][]{
        {"Dashboard"},
        {"Transaksi"}
    };

    public sidebar() {
        init();
    }

    private void init() {
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);

        //init menuitems
        for (int i = 0; i < menuitems.length; i++) {
            addMenu(menuitems[i][0], i);
        }
    }
    
    public void init2() {
        this.removeAll();
        layout = new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);

        //init menuitems
        for (int i = 0; i < menuitemsK.length; i++) {
            addMenu2(menuitemsK[i][0], i);
        }
    }

    private void addMenu(String menuName, int index) {
        int length = menuitems[index].length;
        menuitems item = new menuitems(menuName, index, length > 0);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (length > 1) {
                    if (!item.isSelected()) {
                        item.setSelected(true);
                        addsubmenu(item, index, length, getComponentZOrder(item));
                    } else {
                        //Hide menu
                        hideMenu(item, index);
                        item.setSelected(false);
                    }
                } else {
                    if (event != null) {
                        event.selected(index, 0);
                    }
                }
            }
        });
        add(item);
        revalidate();
        repaint();
    }
    
    private void addMenu2(String menuName, int index) {
        int length = menuitemsK[index].length;
        menuitems item = new menuitems(menuName, index, length > 3);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (length > 1) {
                    if (!item.isSelected()) {
                        item.setSelected(true);
                        addsubmenu2(item, index, length, getComponentZOrder(item));
                    } else {
                        //Hide menu
                        hideMenu(item, index);
                        item.setSelected(false);
                    }
                } else {
                    if (event != null) {
                        event.selected(index, 0);
                    }
                }
            }
        });
        add(item);
        revalidate();
        repaint();
    }

    private void addsubmenu(menuitems item, int index, int length, int indexZorder) {
        JPanel panel = new JPanel(new MigLayout("wrap 0, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        panel.setOpaque(false);
        for (int i = 1; i < length; i++) {
            menuitems subItem = new menuitems(menuitems[index][i], i, false);
            subItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (event != null) {
                        event.selected(index, subItem.getIndex());
                    }
                }
            });
            subItem.initsubmenu(i, length);
            panel.add(subItem);
        }

        add(panel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();
        menuanimasi.showMenu(panel, item, layout, true);
    }
    
    private void addsubmenu2(menuitems item, int index, int length, int indexZorder) {
        JPanel panel = new JPanel(new MigLayout("wrap 0, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        panel.setOpaque(false);
        for (int i = 1; i < length; i++) {
            menuitems subItem = new menuitems(menuitemsK[index][i], i, false);
            subItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (event != null) {
                        event.selected(index, subItem.getIndex());
                    }
                }
            });
            subItem.initsubmenu(i, length);
            panel.add(subItem);
        }

        add(panel, "h 0!", indexZorder + 1);
        revalidate();
        repaint();
        menuanimasi.showMenu(panel, item, layout, true);
    }

    private void hideMenu(menuitems item, int index) {
        for (Component com : getComponents()) {
            if (com instanceof JPanel && com.getName() != null && com.getName().equals(index + "")) {
                com.setName(null);
                menuanimasi.showMenu(com, item, layout, false);
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setColor(new Color(25, 192, 236));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        super.paintComponent(grphcs);
    }
}
