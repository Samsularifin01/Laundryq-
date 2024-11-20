/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class Laporan extends javax.swing.JFrame {boolean drop = false;
    koneksi db = new koneksi();
    DefaultTableModel model = new DefaultTableModel();
    
    private Timer timer;
    String role;
    HashMap<String, Integer> map = new HashMap<>();
    
    public void setCbTahun() {
        for(int i = 2019; i < 2035; i++) {
            tahun.addItem(String.valueOf(i));
        }
        map.put("Januari", 1);
        map.put("Februari", 2);
        map.put("Maret", 3);
        map.put("April", 4);
        map.put("Mei", 5);
        map.put("Juni", 6);
        map.put("Juli", 7);
        map.put("Agustus", 8);
        map.put("September", 9);
        map.put("Oktober", 10);
        map.put("November", 11);
        map.put("Desember", 12);
    }

    /**
     * Creates new form login
     */
    public Laporan(String role) {
        initComponents();
        pn_hilang.setVisible(drop);
        this.role = role;
        if(role.equals("staff")) {
            btn_databarang.setVisible(false);            
           btn_karyawan.setVisible(false);
        }
        
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\box.png", 60, 60, img_box, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\payment.png", 60, 60, img_transaksi2, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\box1.png", 60, 60, img_box1, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\laporan.png", 60, 60, img_laporan1, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\logout.png", 80, 80, img_logout, this);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        db.koneksi();
        addrow();
         aturBariss();
        setCbTahun();
        
        
        timer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        showDayDateTime();
            }
            });
            
        
       timer.start();
    }
    
    public void setRowFromMonth(int month, String year) {
        model.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM laporan WHERE tgl_transaksi >= '" + year + "-" + month + "-01' AND tgl_transaksi <= '" + year + "-" + month + "-31'");
    try {
        while(rs.next()) {
            model.addRow(new Object[]
                {   //hasil.getString("id_laporan"),
                    rs.getString("kode_transaksi"),
                    rs.getString("nama_customer"),
//                    hasil.getString("jenis_service"),
                    rs.getString("subtotal"), 
                    rs.getString("status"), 
                    rs.getString("tgl_transaksi"), 
                    rs.getString("keterangan"),
//                    hasil.getString("tgl_pengambilan"), 
                    /*hasil.getString("catatan")*/});
            
             table_pemasukan.setModel(model);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public String changeToNum(String num) {
        String result = "";
        for(int i = 0; i < num.replace('.', 'a').split("a").length; i++) {
            result += num.replace('.', 'a').split("a")[i];
        }
        return result;
    }
    
    public String changeToRp(String num) { 
        try {
            NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
        return "Rp. " + nf.format(Double.parseDouble(num));
        } catch (Exception e) {
        }
            
        return "Rp. " + 0;
    }
    
    
    public void setCbTgl() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Januari", 31);
        map.put("Februari", 28);
        map.put("Maret", 31);
        map.put("April", 30);
        map.put("Mei", 31);
        map.put("Juni", 30);
        map.put("Juli", 31);
        map.put("Agustus", 31);
        map.put("September", 30);
        map.put("Oktober", 31);
        map.put("November", 30);
        map.put("Desember", 31);
        tanggal.removeAllItems();
        tanggal.addItem("Tanggal");
        try {
            if(!tahun.getSelectedItem().equals("--Tidak dipilih--") || !bulan.getSelectedItem().equals("Tahun")) {
                int kabisat = Integer.parseInt(String.valueOf(tahun.getSelectedItem())) % 4;
                if(kabisat == 0 && bulan.getSelectedItem().equals("Februari")) {
                    for(int i = 0; i < map.get("Februari") + 1; i++) {
                        tanggal.addItem(String.valueOf(i + 1));
                    }
                } else {
                    for(int i = 0; i < map.get(String.valueOf(bulan.getSelectedItem())); i++) {
                        tanggal.addItem(String.valueOf(i + 1));
                    }
                }
            } else {
                // Hehe
            }
        } catch(Exception e) {
            System.err.println("Masukin dulu bulan atau tahun nya!");
        }
    }
    
    
    
    private void showDayDateTime() {
        Calendar calendar = Calendar.getInstance();     
        Date now = new Date();
        SimpleDateFormat formatHari = new SimpleDateFormat ("EEEE", new Locale("in", "ID"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hari = formatHari.format(calendar.getTime());
        String dateTime = dateFormat.format(now);
        lb_date.setText(hari+", "+dateTime);
        }
    
    public void addrow(){
        model.setColumnCount(0);
        //model.addColumn("id");
     
        model.addColumn("Kode");
        model.addColumn("Nama");
//        model.addColumn("Jenis");
        model.addColumn("Total");
        model.addColumn("Status");
        model.addColumn("Tgl pesanan");
//        model.addColumn("Tgl Pengambilan");
        model.addColumn("Keterangan");
//        model.addColumn("Catatan");
        table_pemasukan.setModel(model);
        
    }
     public void aturBariss(){
         model.setRowCount(0);
        ResultSet hasil = db.ambilData("SELECT * FROM laporan;");
        try {
            while(hasil.next()) {
                model.addRow(new Object[]
                {   //hasil.getString("id_laporan"),
                    hasil.getString("kode_transaksi"),
                    hasil.getString("nama_customer"),
//                    hasil.getString("jenis_service"),
                    hasil.getString("subtotal"), 
                    hasil.getString("status"), 
                    hasil.getString("tgl_transaksi"), 
                    hasil.getString("keterangan"),
//                    hasil.getString("tgl_pengambilan"), 
                    /*hasil.getString("catatan")*/});
            }
             table_pemasukan.setModel(model);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
 
    
    public void resetAll() {
        aturBariss();
        table_pemasukan.setModel(model);
        pemasukan_harian.setText("");
        pemasukan_bulan.setText("");
        pemasukan_tahun.setText("");
        tanggal.setSelectedItem("Tanggal");
        bulan.setSelectedItem("Bulan");
        tahun.setSelectedItem("Tahun");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Navbar = new javax.swing.JPanel();
        txt_navbar = new javax.swing.JLabel();
        lb_date = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        pemasukan_tahun = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        pemasukan_harian = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        pemasukan_bulan = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_pemasukan = new project.CustomTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_oke = new project.CustomButtons();
        tanggal = new javax.swing.JComboBox<>();
        bulan = new javax.swing.JComboBox<>();
        tahun = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        side_bar = new javax.swing.JPanel();
        pn_hilang = new javax.swing.JPanel();
        home = new javax.swing.JLabel();
        btn_databarang = new project.CustomButtons();
        btn_transaksi = new project.CustomButtons();
        btn_karyawan = new project.CustomButtons();
        btn_laporan = new project.CustomButtons();
        btn_pemasukan = new project.CustomButtons();
        btn_pengeluaran = new project.CustomButtons();
        pn_hilang1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Navbar.setBackground(new java.awt.Color(25, 192, 236));

        txt_navbar.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        txt_navbar.setForeground(new java.awt.Color(255, 255, 255));
        txt_navbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_navbar.setText("PEMASUKAN");

        lb_date.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_date.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout NavbarLayout = new javax.swing.GroupLayout(Navbar);
        Navbar.setLayout(NavbarLayout);
        NavbarLayout.setHorizontalGroup(
            NavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavbarLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(txt_navbar, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_date, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        NavbarLayout.setVerticalGroup(
            NavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_navbar, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NavbarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_date, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel1.add(Navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 70));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pemasukan_tahun.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        pemasukan_tahun.setForeground(new java.awt.Color(25, 192, 236));
        pemasukan_tahun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pemasukan_tahun.setText("Rp. 000.000");
        jPanel6.add(pemasukan_tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 240, 20));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(25, 192, 236));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Rp. 000.000");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 70));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 240, 80));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(25, 192, 236));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Rp. 000.000");
        jPanel11.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 70));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(25, 192, 236));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Rp. 000.000");
        jPanel12.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 70));

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 240, 80));

        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 240, 80));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(25, 192, 236));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Rp. 000.000");
        jPanel13.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 70));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(25, 192, 236));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Rp. 000.000");
        jPanel14.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 70));

        jPanel13.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 240, 80));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(25, 192, 236));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Rp. 000.000");
        jPanel15.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 70));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(25, 192, 236));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Rp. 000.000");
        jPanel16.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 70));

        jPanel15.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 240, 80));

        jPanel13.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 240, 80));

        jPanel6.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 240, 80));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 240, 80));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pemasukan_harian.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        pemasukan_harian.setForeground(new java.awt.Color(25, 192, 236));
        pemasukan_harian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pemasukan_harian.setText("Rp. 000.000");
        jPanel7.add(pemasukan_harian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 240, -1));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 240, 80));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pemasukan_bulan.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        pemasukan_bulan.setForeground(new java.awt.Color(25, 192, 236));
        pemasukan_bulan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pemasukan_bulan.setText("Rp. 000.000");
        jPanel9.add(pemasukan_bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 240, 20));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(25, 192, 236));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Rp. 000.000");
        jPanel10.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 70));

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 240, 80));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 240, 80));

        jPanel4.setBackground(new java.awt.Color(25, 192, 236));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pemasukan Bulanan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        table_pemasukan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(table_pemasukan);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 1090, 320));

        jPanel3.setBackground(new java.awt.Color(25, 192, 236));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pemasukan Harian");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(69, 69, 69))
        );

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 40));

        jPanel2.setBackground(new java.awt.Color(25, 192, 236));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pemasukan Tahunan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(74, 74, 74))
        );

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 240, 40));

        btn_oke.setText("customButtons1");
        btn_oke.setBackgroundColor(new java.awt.Color(25, 192, 236));
        btn_oke.setTextColor(java.awt.Color.white);
        btn_oke.setTheText("OK");
        btn_oke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okeActionPerformed(evt);
            }
        });
        jPanel5.add(btn_oke, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 200, 50, 30));

        tanggal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tanggal" }));
        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });
        jPanel5.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, 110, 30));

        bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bulan", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        bulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bulanActionPerformed(evt);
            }
        });
        jPanel5.add(bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 110, 30));

        tahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tahun" }));
        tahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tahunActionPerformed(evt);
            }
        });
        jPanel5.add(tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 200, 110, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 1190, 680));

        side_bar.setBackground(new java.awt.Color(255, 255, 255));
        side_bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pn_hilang.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pn_hilangLayout = new javax.swing.GroupLayout(pn_hilang);
        pn_hilang.setLayout(pn_hilangLayout);
        pn_hilangLayout.setHorizontalGroup(
            pn_hilangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pn_hilangLayout.setVerticalGroup(
            pn_hilangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        side_bar.add(pn_hilang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 130, 80));

        home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        side_bar.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, -1));

        btn_databarang.setText("customButtons1");
        btn_databarang.setTheText("Data Barang");
        side_bar.add(btn_databarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 130, 30));

        btn_transaksi.setText("customButtons1");
        btn_transaksi.setTheText("Transaksi");
        side_bar.add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 130, 30));

        btn_karyawan.setText("customButtons1");
        btn_karyawan.setTheText("Data Karyawan");
        side_bar.add(btn_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 130, 30));

        btn_laporan.setText("customButtons1");
        btn_laporan.setTheText("Laporan");
        side_bar.add(btn_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 130, 30));

        btn_pemasukan.setText("customButtons1");
        btn_pemasukan.setTheText("Pemasukan");
        side_bar.add(btn_pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 130, 30));

        btn_pengeluaran.setText("customButtons1");
        btn_pengeluaran.setTheText("Pengeluaran");
        side_bar.add(btn_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 130, 30));

        pn_hilang1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pn_hilang1Layout = new javax.swing.GroupLayout(pn_hilang1);
        pn_hilang1.setLayout(pn_hilang1Layout);
        pn_hilang1Layout.setHorizontalGroup(
            pn_hilang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pn_hilang1Layout.setVerticalGroup(
            pn_hilang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
        );

        side_bar.add(pn_hilang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 130, -1));

        jPanel1.add(side_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 150, 610));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
        Beranda db1 = new Beranda(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_homeMouseClicked

    private void bt_databarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_databarangMouseClicked
        // TODO add your handling code here:
        data_barang1 db1 = new data_barang1(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_databarangMouseClicked

    private void bt_databarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_databarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_databarangActionPerformed

    private void bt_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_transaksiMouseClicked
        // TODO add your handling code here:
        Transaksi db1 = new Transaksi(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_transaksiMouseClicked

    private void bt_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_transaksiActionPerformed

    private void bt_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_laporanActionPerformed
        // TODO add your handling code here:
        if(drop) {
            pn_hilang.setVisible(drop);
            drop = false;

        }else {
            pn_hilang.setVisible(drop);
            drop = true;
        }
    }//GEN-LAST:event_bt_laporanActionPerformed

    private void bt_pemasukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_pemasukanMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Laporan(role).setVisible(true);
    }//GEN-LAST:event_bt_pemasukanMouseClicked

    private void bt_pemasukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pemasukanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_pemasukanActionPerformed

    private void bt_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_pengeluaranMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new tabel_pengeluaran(role).setVisible(true);
    }//GEN-LAST:event_bt_pengeluaranMouseClicked

    private void bt_pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pengeluaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_pengeluaranActionPerformed

    private void bt_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_logoutMouseClicked
        // TODO add your handling code here:
        login db1 = new login();
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_logoutMouseClicked

    private void bt_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_logoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_logoutActionPerformed

    private void btn_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_karyawanMouseClicked
        // TODO add your handling code here:
        data_karyawan db1 = new data_karyawan(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_karyawanMouseClicked

    private void btn_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_karyawanActionPerformed

    private void bulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulanActionPerformed

    }//GEN-LAST:event_bulanActionPerformed

    private void tahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tahunActionPerformed
        // TODO add your handling code here:
        setCbTgl();
    }//GEN-LAST:event_tahunActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        resetAll();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btn_okeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okeActionPerformed
        // TODO add your handling code here:
        String tgl = tanggal.getSelectedItem().toString();
        String bln = bulan.getSelectedItem().toString();
        String thn = tahun.getSelectedItem().toString();
        ResultSet pemasukanharian = db.ambilData("SELECT SUM(subtotal) AS totalharga FROM laporan WHERE tgl_transaksi = '" + thn + "-" + map.get(bln) + "-" + tgl + "'");
        ResultSet pemasukanbulanan = db.ambilData("SELECT SUM(subtotal) AS totalharga FROM laporan WHERE tgl_transaksi  >= '" + thn + "-" + map.get(bln) + "-01' AND tgl_transaksi <= '" + thn + "-" + map.get(bln) + "-31'");
        ResultSet pemasukantahunan = db.ambilData("SELECT SUM(subtotal) AS totalharga FROM laporan WHERE tgl_transaksi >= '" + thn + "-01-01' AND tgl_transaksi <= '" + thn + "-12-31'");

        /*ResultSet pengeluaranarian = db.ambilData("SELECT SUM(harga) AS hehe FROM pengeluaran WHERE tanggal_pengeluaran = '" + thn + "-" + map.get(bln) + "-" + tgl + "'");
        ResultSet pengeluaranBulanan = db.ambilData("SELECT SUM(harga) AS hehe FROM pengeluaran WHERE tanggal_pengeluaran >= '" + thn + "-" + map.get(bln) + "-01' AND tgl_pengeluaran <= '" + thn + "-" + map.get(bln) + "-31'");
        ResultSet pengeluaranTahunan = db.ambilData("SELECT SUM(harga) AS hehe FROM pengeluaran WHERE tanggal_pengeluaran >= '" + thn + "-01-01' AND tgl_pengeluaran <= '" + thn + "-12-31'");*/
        try {
        if(pemasukanharian.next()) {
                pemasukan_harian.setText(changeToRp(pemasukanharian.getString("totalharga")));
            } else {
                pemasukan_harian.setText("Tidak ada Data!");
            }
            if(pemasukanbulanan.next()) {
                pemasukan_bulan.setText(changeToRp(pemasukanbulanan.getString("totalharga")));
            } else {
                pemasukan_bulan.setText("Tidak ada Data!");
            }
            if(pemasukantahunan.next()) {
                pemasukan_tahun.setText(changeToRp(pemasukantahunan.getString("totalharga")));
            } else {
                pemasukan_tahun.setText("Tidak ada Data!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setRowFromMonth(map.get(bln), thn);

    }//GEN-LAST:event_btn_okeActionPerformed

    private void tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggalActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tanggalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Navbar;
    private project.CustomButtons btn_databarang;
    private project.CustomButtons btn_karyawan;
    private project.CustomButtons btn_laporan;
    private project.CustomButtons btn_oke;
    private project.CustomButtons btn_pemasukan;
    private project.CustomButtons btn_pengeluaran;
    private project.CustomButtons btn_transaksi;
    private javax.swing.JComboBox<String> bulan;
    private javax.swing.JLabel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_date;
    private javax.swing.JLabel pemasukan_bulan;
    private javax.swing.JLabel pemasukan_harian;
    private javax.swing.JLabel pemasukan_tahun;
    private javax.swing.JPanel pn_hilang;
    private javax.swing.JPanel pn_hilang1;
    private javax.swing.JPanel side_bar;
    private project.CustomTable table_pemasukan;
    private javax.swing.JComboBox<String> tahun;
    private javax.swing.JComboBox<String> tanggal;
    private javax.swing.JLabel txt_navbar;
    // End of variables declaration//GEN-END:variables
}
