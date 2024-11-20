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
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class tabel_pengeluaran extends javax.swing.JFrame {boolean drop = false;
    koneksi db = new koneksi();
    private Timer timer;
    String role;
    HashMap<String, Integer> map = new HashMap<>();
    DefaultTableModel table = new DefaultTableModel();
    //DefaultTableModel model = new DefaultTableModel();
    /**
     * Creates new form login
     */
    
    
    
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
    
    
    
    public tabel_pengeluaran(String role) {
        initComponents();
        pn_hilang.setVisible(drop);
        this.role = role;
        if(role.equals("staff")) {
            btn_databarang.setVisible(false);
           btn_karyawan.setVisible(false);
        }
         String[] blnArr = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        for(int i = 0; i < blnArr.length; i++) {
            map.put(blnArr[i], i + 1);
        }
       
        setColumn();
        aturBariss();
        setCbTahun();
         setRows();
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\box.png", 60, 60, img_box, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\payment.png", 60, 60, img_transaksi2, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\box1.png", 60, 60, img_box1, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\laporan.png", 60, 60, img_laporan1, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\logout.png", 80, 80, img_logout, this);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        

        
        timer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        showDayDateTime();
            }
            });
            
        
       timer.start();
    }
    
   public void setRowFromMonth(int month, String year) {
        table.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM pengeluaran WHERE tanggal >= '" + year + "-" + month + "-01' AND tanggal <= '" + year + "-" + month + "-31'");
    try {
        while(rs.next()) {
            table.addRow(new Object[]
                {   rs.getString("kode_pengeluaran"),
                    rs.getString("tanggal"),
                    rs.getString("jenis_pengeluaran"), 
                    rs.getString("total_pengeluaran"), 
                    rs.getString("keterangan")});
            
             tabel_pengeluaran.setModel(table);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
   
    public void setColumn() {
        table.addColumn("Kode Pengeluaran");
        table.addColumn("Tanggal");
        table.addColumn("Jenis Pengeluaran");
        table.addColumn("Total");
        table.addColumn("Keterangan");
        tabel_pengeluaran.setModel(table);
    }
    
      public void setRows() {
        table.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM pengeluaran");
        try {
            while (rs.next()) {
                table.addRow(new Object[]{rs.getString("kode_pengeluaran"),
                    rs.getString("tanggal"), 
                    rs.getString("jenis_pengeluaran"),
                    rs.getString("total_pengeluaran"),
                    rs.getString("keterangan")});
                
            }
            tabel_pengeluaran.setModel(table);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
      
    public void aturBariss(){
         table.setRowCount(0);
        ResultSet hasil = db.ambilData("SELECT * FROM pengeluaran;");
        try {
            while(hasil.next()) {
                table.addRow(new Object[]
                {   hasil.getString("kode_pengeluaran"),
                    hasil.getString("tanggal"),
                    hasil.getString("jenis_pengeluaran"), 
                    hasil.getString("total_pengeluaran"), 
                    hasil.getString("keterangan")});
            }
             tabel_pengeluaran.setModel(table);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        tanggal.addItem("Silahkan Pilih");
        try {
            if(!tahun.getSelectedItem().equals("--Tidak dipilih--") || !bulan.getSelectedItem().equals("Silahkan Pilih")) {
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
      
    
    
//     table.addColumn("Kode Pengeluaran");
//        table.addColumn("Tanggal");
//        table.addColumn("Jenis Keluaran");
//        table.addColumn("Total");
//        table.addColumn("Keterangan");
////        tabel.setModel(table)
    
    public void setRow() {
        table.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM pengeluaran where jenis_pengeluaran");
        try {
            while(rs.next()) {
                table.addRow(new Object[]{rs.getString("kode_pengeluaran"),
                rs.getString("tanggal"),
                rs.getString("jenis_pengeluaran"),
                rs.getString("total_pengeluaran"),
                rs.getString("keterangan")});
            }
            tabel_pengeluaran.setModel(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void resetAll() {
        aturBariss();
        tabel_pengeluaran.setModel(table);
        pengeluaran_harian.setText("");
        pengeluaran_bulanan.setText("");
        pengeluaran_tahunan.setText("");
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
        side_bar = new javax.swing.JPanel();
        home = new javax.swing.JLabel();
        pn_hilang = new javax.swing.JPanel();
        btn_databarang = new project.CustomButtons();
        btn_transaksi = new project.CustomButtons();
        btn_karyawan = new project.CustomButtons();
        btn_laporan = new project.CustomButtons();
        btn_pemasukan = new project.CustomButtons();
        btn_pengeluaran = new project.CustomButtons();
        pn_hilang1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bt_hapus = new project.CustomButtons();
        bt_tambah = new project.CustomButtons();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pengeluaran = new project.CustomTable();
        jPanel7 = new javax.swing.JPanel();
        pengeluaran_harian = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pngeluarn_har = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        pengeluaran_bulanan = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pngeluarn_har1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        pngeluarn_har2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        pengeluaran_tahunan = new javax.swing.JLabel();
        refresh = new javax.swing.JLabel();
        tanggal = new javax.swing.JComboBox<>();
        bulan = new javax.swing.JComboBox<>();
        tahun = new javax.swing.JComboBox<>();
        btn_oke = new project.CustomButtons();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Navbar.setBackground(new java.awt.Color(25, 192, 236));
        Navbar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_navbar.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        txt_navbar.setForeground(new java.awt.Color(255, 255, 255));
        txt_navbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_navbar.setText("PENGELUARAN");
        Navbar.add(txt_navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 891, 70));

        lb_date.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_date.setForeground(new java.awt.Color(255, 255, 255));
        Navbar.add(lb_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 30, 276, 36));

        jPanel1.add(Navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 80));

        side_bar.setBackground(new java.awt.Color(255, 255, 255));
        side_bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        home.setIcon(new javax.swing.ImageIcon("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\home.png")); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        side_bar.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, -1));

        pn_hilang.setBackground(new java.awt.Color(255, 255, 255));
        pn_hilang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        side_bar.add(pn_hilang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 130, 80));

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

        jPanel1.add(side_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 150, 720));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bt_hapus.setText("customButton2");
        bt_hapus.setBackgroundColor(new java.awt.Color(25, 192, 236));
        bt_hapus.setBorderRadius(25);
        bt_hapus.setTextBold(0);
        bt_hapus.setTextColor(java.awt.Color.white);
        bt_hapus.setTheText("Hapus");
        bt_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_hapusActionPerformed(evt);
            }
        });
        jPanel2.add(bt_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 570, 120, 30));

        bt_tambah.setText("customButton2");
        bt_tambah.setBackgroundColor(new java.awt.Color(25, 192, 236));
        bt_tambah.setBorderRadius(25);
        bt_tambah.setTextBold(0);
        bt_tambah.setTextColor(java.awt.Color.white);
        bt_tambah.setTheText("Tambah");
        bt_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_tambahActionPerformed(evt);
            }
        });
        jPanel2.add(bt_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 530, 120, 30));

        tabel_pengeluaran.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel_pengeluaran);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 1100, 270));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pengeluaran_harian.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        pengeluaran_harian.setForeground(new java.awt.Color(25, 192, 236));
        pengeluaran_harian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pengeluaran_harian.setText("Rp. 000.000");
        jPanel7.add(pengeluaran_harian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 240, -1));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 240, 80));

        jPanel3.setBackground(new java.awt.Color(25, 192, 236));

        pngeluarn_har.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        pngeluarn_har.setForeground(new java.awt.Color(255, 255, 255));
        pngeluarn_har.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pngeluarn_har.setText("Pengeluaran Harian");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pngeluarn_har, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pngeluarn_har)
                .addGap(69, 69, 69))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 40));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pengeluaran_bulanan.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        pengeluaran_bulanan.setForeground(new java.awt.Color(25, 192, 236));
        pengeluaran_bulanan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pengeluaran_bulanan.setText("Rp. 000.000");
        jPanel8.add(pengeluaran_bulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 240, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, 80));

        jPanel4.setBackground(new java.awt.Color(25, 192, 236));

        pngeluarn_har1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        pngeluarn_har1.setForeground(new java.awt.Color(255, 255, 255));
        pngeluarn_har1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pngeluarn_har1.setText("Pengeluaran Bulanan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pngeluarn_har1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pngeluarn_har1)
                .addGap(69, 69, 69))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, 40));

        jPanel5.setBackground(new java.awt.Color(25, 192, 236));

        pngeluarn_har2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        pngeluarn_har2.setForeground(new java.awt.Color(255, 255, 255));
        pngeluarn_har2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pngeluarn_har2.setText("Pengeluaran Tahunan");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pngeluarn_har2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pngeluarn_har2)
                .addGap(69, 69, 69))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, 40));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pengeluaran_tahunan.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        pengeluaran_tahunan.setForeground(new java.awt.Color(25, 192, 236));
        pengeluaran_tahunan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pengeluaran_tahunan.setText("Rp. 000.000");
        jPanel9.add(pengeluaran_tahunan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 240, -1));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, -1, 80));

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
        });
        jPanel2.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, -1, -1));

        tanggal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tanggal" }));
        tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggalActionPerformed(evt);
            }
        });
        jPanel2.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, 110, 30));

        bulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bulan", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        bulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bulanActionPerformed(evt);
            }
        });
        jPanel2.add(bulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 110, 30));

        tahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tahun" }));
        tahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tahunActionPerformed(evt);
            }
        });
        jPanel2.add(tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 200, 110, 30));

        btn_oke.setText("customButtons1");
        btn_oke.setBackgroundColor(new java.awt.Color(25, 192, 236));
        btn_oke.setTextColor(java.awt.Color.white);
        btn_oke.setTheText("OK");
        btn_oke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okeActionPerformed(evt);
            }
        });
        jPanel2.add(btn_oke, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 200, 50, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 1190, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
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
        login db1 = new login(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_logoutMouseClicked

    private void bt_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_logoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_logoutActionPerformed

    private void bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tambahActionPerformed
        // TODO add your handling code here:
        //db.aksi("INSERT INTO pengeluaran (kode_pengeluaran, jenis_pengeluaran, total_pengeluaran, keterangan) VALUES ('" + kode_pengeluaran.getText() + "', '" + jns_pengeluaran.getText()+ "', '" + total_pengeluaran1.getText() + "', '" + input_keterangan1.getText() + "')");
        //JOptionPane.showMessageDialog(null, "Tambah Berhasil", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
        pengeluaran db1 = new pengeluaran(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_tambahActionPerformed

    private void btn_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_karyawanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_karyawanMouseClicked

    private void btn_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_karyawanActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_okActionPerformed

    private void bt_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_hapusActionPerformed
        // TODO add your handling code here:
        int row = tabel_pengeluaran.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda ingin hapus data ini?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                db.aksi("DELETE FROM pengeluaran WHERE kode_pengeluaran = '" + tabel_pengeluaran.getValueAt(row, 0) + "'");
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                setRows();
            }
        }
    }//GEN-LAST:event_bt_hapusActionPerformed

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        // TODO add your handling code here:
        resetAll();
    }//GEN-LAST:event_refreshMouseClicked

    private void bulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulanActionPerformed
setCbTgl();
    }//GEN-LAST:event_bulanActionPerformed

    private void tahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tahunActionPerformed
        // TODO add your handling code here:
        setCbTgl();
    }//GEN-LAST:event_tahunActionPerformed

    private void btn_okeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okeActionPerformed
        // TODO add your handling code here:
        String tgl = tanggal.getSelectedItem().toString();
        String bln = bulan.getSelectedItem().toString();
        String thn = tahun.getSelectedItem().toString();
        ResultSet pemasukanharian = db.ambilData("SELECT SUM(total_pengeluaran) AS totalharga FROM pengeluaran WHERE tanggal = '" + thn + "-" + map.get(bln) + "-" + tgl + "'");
        ResultSet pemasukanbulanan = db.ambilData("SELECT SUM(total_pengeluaran) AS totalharga FROM pengeluaran WHERE tanggal >= '" + thn + "-" + map.get(bln) + "-01' AND tanggal <= '" + thn + "-" + map.get(bln) + "-31'");
        ResultSet pemasukantahunan = db.ambilData("SELECT SUM(total_pengeluaran) AS totalharga FROM pengeluaran WHERE tanggal >= '" + thn + "-01-01' AND tanggal <= '" + thn + "-12-31'");

        /*ResultSet pengeluaranarian = db.ambilData("SELECT SUM(harga) AS hehe FROM pengeluaran WHERE tanggal_pengeluaran = '" + thn + "-" + map.get(bln) + "-" + tgl + "'");
        ResultSet pengeluaranBulanan = db.ambilData("SELECT SUM(harga) AS hehe FROM pengeluaran WHERE tanggal_pengeluaran >= '" + thn + "-" + map.get(bln) + "-01' AND tgl_pengeluaran <= '" + thn + "-" + map.get(bln) + "-31'");
        ResultSet pengeluaranTahunan = db.ambilData("SELECT SUM(harga) AS hehe FROM pengeluaran WHERE tanggal_pengeluaran >= '" + thn + "-01-01' AND tgl_pengeluaran <= '" + thn + "-12-31'");*/
        try {
            if(pemasukanharian.next()) {
                pengeluaran_harian.setText(changeToRp(pemasukanharian.getString("totalharga")));
            } else {
                pengeluaran_harian.setText("Tidak ada Data!");
            }
            if(pemasukanbulanan.next()) {
                pengeluaran_bulanan.setText(changeToRp(pemasukanbulanan.getString("totalharga")));
            } else {
                pengeluaran_bulanan.setText("Tidak ada Data!");
            }
            if(pemasukantahunan.next()) {
                pengeluaran_tahunan.setText(changeToRp(pemasukantahunan.getString("totalharga")));
            } else {
                pengeluaran_tahunan.setText("Tidak ada Data!");
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
            java.util.logging.Logger.getLogger(tabel_pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tabel_pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tabel_pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tabel_pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new tabel_pengeluaran("").setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Navbar;
    private project.CustomButtons bt_hapus;
    private project.CustomButtons bt_tambah;
    private project.CustomButtons btn_databarang;
    private project.CustomButtons btn_karyawan;
    private project.CustomButtons btn_laporan;
    private project.CustomButtons btn_oke;
    private project.CustomButtons btn_pemasukan;
    private project.CustomButtons btn_pengeluaran;
    private project.CustomButtons btn_transaksi;
    private javax.swing.JComboBox<String> bulan;
    private javax.swing.JLabel home;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_date;
    private javax.swing.JLabel pengeluaran_bulanan;
    private javax.swing.JLabel pengeluaran_harian;
    private javax.swing.JLabel pengeluaran_tahunan;
    private javax.swing.JPanel pn_hilang;
    private javax.swing.JPanel pn_hilang1;
    private javax.swing.JLabel pngeluarn_har;
    private javax.swing.JLabel pngeluarn_har1;
    private javax.swing.JLabel pngeluarn_har2;
    private javax.swing.JLabel refresh;
    private javax.swing.JPanel side_bar;
    private project.CustomTable tabel_pengeluaran;
    private javax.swing.JComboBox<String> tahun;
    private javax.swing.JComboBox<String> tanggal;
    private javax.swing.JLabel txt_navbar;
    // End of variables declaration//GEN-END:variables

    private void execute() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
