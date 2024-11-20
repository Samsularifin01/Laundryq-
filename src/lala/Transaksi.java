/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lala;

import com.barcodelib.barcode.Linear;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import log.login;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import project.koneksi;
import utama.defaultform;

/**
 *
 * @author ASUS
 */
public class Transaksi extends javax.swing.JPanel {
    
    boolean drop = false;
    private Timer timer;
    DefaultTableModel model = new DefaultTableModel();
    koneksi db = new koneksi();
    String role;
    boolean sudahBayar = false;
    
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
//        pn_hilang1.setVisible(drop);
//        this.role = role;
//        if (role.equals("staff")) {
//            bt_databarang.setVisible(false);
//            bt_laporan1.setVisible(false);
//            btn_karyawan.setVisible(false);
//            btn_tambah.setVisible(false);
//            tbl_layanan.setVisible(false);
//
//            //lbLogin.setText("Masuk Sebagai Karyawan");
//        }
        db.koneksi();
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\box.png", 60, 60, img_box, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\payment.png", 60, 60, img_transaksi2, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\box1.png", 60, 60, img_box1, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\laporan.png", 60, 60, img_laporan1, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\logout.png", 80, 80, img_logout, this);


        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDayDateTime();
            }

        });
        timer.start();
        kode_transaksi();
        setColumn();
        tgl_transaksi.setText(setDateFromMili(0));
        setCBDataLayanan();
    }
    
    public void setCBDataLayanan() {
        ResultSet rs = db.ambilData("select *  from `data_layanan`");
        try {
            while (rs.next()) {
                if (rs.getString("jenis_layanan").equals("Kiloan")) {
                    input_jenis_service.addItem(rs.getString("nama_layanan"));
                } else {
                    jenis_pakaian.addItem(rs.getString("nama_layanan"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void countTotal() {
        int sumTotal = 0;
        int row = tblData.getRowCount();
        for (int i = 0; i < row; i++) {
            sumTotal += Integer.parseInt(String.valueOf(tblData.getValueAt(i, 5)));
        }
        lb_totalharga.setText(String.valueOf(sumTotal));
    }

    public String setDateFromMili(int days) {
        Date d = new Date();
        long temp = d.getTime() + (86400000L * days);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(temp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    public void setColumn() {
        model.setColumnCount(0);
        model.addColumn("Kode Transaksi");
        model.addColumn("Nama Customer");
        model.addColumn("Kiloan / Satuan");
        model.addColumn("Total Berat");
        model.addColumn("No Penempatan");
        model.addColumn("Total Harga");
        model.addColumn("Tgl Pengambilan");
        model.addColumn("Tgl pesanan");
        model.addColumn("Catatan");
        tblData.setModel(model);
    }

    public void addRow() {
        String kd = kode_transaksi.getText();
        String namacs = input_name.getText();
        String jenisKiloan = input_jenis_service.getSelectedItem().toString();
        String jenisPakaian = jenis_pakaian.getSelectedItem().toString();
        int totalBerat = Integer.parseInt(total_berat.getText());
        int noPenempatan = Integer.parseInt((String) nomor_penempatan.getSelectedItem());
        int totalharga = Integer.parseInt((String) total_harga.getText());
        String tglPengambilan = tgl_pengembalian.getText();
        String tglpesanan = (String) tgl_transaksi.getText();
        String txtcatatan = catatan.getText();
        if (jenisKiloan.equals("Silahkan Pilih")) {
            model.addRow(new Object[]{kd, namacs, jenisPakaian, totalBerat, noPenempatan, totalharga, tglPengambilan, tglpesanan, txtcatatan});
        } else {
            model.addRow(new Object[]{kd, namacs, jenisKiloan, totalBerat, noPenempatan, totalharga, tglPengambilan, tglpesanan, txtcatatan});
        }

        tblData.setModel(model);
    }

    public void resetAll() {
        model.setRowCount(0);
        tblData.setModel(model);
        total_berat.setText("");
        total_harga.setText("");
        status_bayar.setSelectedItem("Silahkan Pilih");
        bayar.setText("");
        kembalian.setText("");
        catatan.setText("");
        nomor_penempatan.setSelectedItem("Silahkan Pilih");
        jenis_pakaian.setSelectedItem("Silahkan Pilih");
        input_jenis_service.setSelectedItem("Silahkan Pilih");
        harga_kg.setText("");
        tgl_pengembalian.setText("");
        lb_totalharga.setText("");
        tgl_transaksi.setText("");
        input_name.setText("Masukkan Nama");

    }

    private void showDayDateTime() {
        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        SimpleDateFormat formatHari = new SimpleDateFormat("EEEE", new Locale("in", "ID"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hari = formatHari.format(calendar.getTime());
        String dateTime = dateFormat.format(now);
        //lb_date.setText(hari + ", " + dateTime);
    }

    public void kode_transaksi() {
        try {
            ResultSet rs = db.ambilData("SELECT kode_transaksi FROM laporan ORDER BY kode_transaksi DESC");
            if (rs.next()) {
                String TR = rs.getString("kode_transaksi");
                int angka = Integer.parseInt(TR.split("R")[1]);
                angka++;
                if (angka <= 9) {
                    kode_transaksi.setText("TR0" + angka);
                } else if (angka <= 99) {
                    kode_transaksi.setText("TR" + angka);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resizeImage(String path, int width, int height, JLabel theLabel, JFrame theFrame) {
        try {
            BufferedImage bi = ImageIO.read(new File(path));
            Image i = bi.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon ii = new ImageIcon(i);
            //JLabel label = new JLabel();
            theLabel.setIcon(ii);
            //theFrame.add(label);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void satuan() {
        ResultSet hasil = db.ambilData("SELECT * FROM data_layanan WHERE jenis_layanan = 'Satuan'");
        try {
            while (hasil.next()) {
                System.out.println("kiloan");
                jenis_pakaian.addItem(hasil.getString("nama_layanan"));
            }
        } catch (Exception e) {

        }
    }

    public void kiloan() {
        ResultSet hasil = db.ambilData("SELECT * FROM data_layanan WHERE jenis_layanan = 'Kiloan'");
        try {
            while (hasil.next()) {
                input_jenis_service.addItem(hasil.getString("nama_layanan"));
            }
        } catch (Exception e) {

        }
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
        jPanel11 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        status_bayar = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lb_totalharga = new javax.swing.JLabel();
        btn_cancel = new project.CustomButtons();
        customButton4 = new project.CustomButtons();
        customButton7 = new project.CustomButtons();
        bayar = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        kembalian = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        total_harga = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        kode_transaksi = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        kodee_transaksi = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        input_name = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        input_jenis_service = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jenis_pakaian = new javax.swing.JComboBox<>();
        total_berat = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        nomor_penempatan = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        tgl_pengembalian = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        customButton2 = new project.CustomButtons();
        customButton6 = new project.CustomButtons();
        jScrollPane1 = new javax.swing.JScrollPane();
        catatan = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        harga_kg = new javax.swing.JTextField();
        tgl_transaksi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_tambah = new project.CustomButtons();
        jPanel19 = new javax.swing.JPanel();
        tbl_layanan = new javax.swing.JLabel();

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 640));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setAutoscrolls(true);
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(25, 192, 236));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Status");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 200, -1));

        status_bayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silahkan Pilih", "Belum Dibayar", "Sudah Dibayar" }));
        status_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_bayarActionPerformed(evt);
            }
        });
        jPanel11.add(status_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 200, 38));

        jPanel5.setBackground(new java.awt.Color(25, 192, 236));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total Bayar:");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, 38));

        lb_totalharga.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_totalharga.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lb_totalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 229, 40));

        jPanel11.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 930, -1));

        btn_cancel.setText("customButton2");
        btn_cancel.setBackgroundColor(new java.awt.Color(25, 192, 236));
        btn_cancel.setBorderRadius(25);
        btn_cancel.setTextBold(0);
        btn_cancel.setTextColor(java.awt.Color.white);
        btn_cancel.setTheText("Batal");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        jPanel11.add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 220, 130, 30));

        customButton4.setText("customButton2");
        customButton4.setBackgroundColor(new java.awt.Color(25, 192, 236));
        customButton4.setBorderRadius(25);
        customButton4.setTextBold(0);
        customButton4.setTextColor(java.awt.Color.white);
        customButton4.setTheText("Simpan");
        customButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton4ActionPerformed(evt);
            }
        });
        jPanel11.add(customButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 180, 130, 30));

        customButton7.setText("customButton2");
        customButton7.setBackgroundColor(new java.awt.Color(25, 192, 236));
        customButton7.setBorderRadius(25);
        customButton7.setTextBold(0);
        customButton7.setTextColor(java.awt.Color.white);
        customButton7.setTheText("Cetak Struk");
        customButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton7ActionPerformed(evt);
            }
        });
        jPanel11.add(customButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 140, 130, 30));

        bayar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        bayar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });
        jPanel11.add(bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 200, 38));

        jPanel15.setBackground(new java.awt.Color(25, 192, 236));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Bayar");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel12)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, -1, -1));

        kembalian.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        kembalian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalianActionPerformed(evt);
            }
        });
        jPanel11.add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 200, 38));

        jPanel16.setBackground(new java.awt.Color(25, 192, 236));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kembalian");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel13)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, 200, -1));

        total_harga.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        total_harga.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_harga.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                total_hargaCaretUpdate(evt);
            }
        });
        total_harga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                total_hargaFocusGained(evt);
            }
        });
        total_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_hargaActionPerformed(evt);
            }
        });
        jPanel11.add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 200, 40));

        jPanel17.setBackground(new java.awt.Color(25, 192, 236));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Total Harga");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 200, -1));

        tblData.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblData);

        jPanel11.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 110));

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 1270, 320));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kode_transaksi.setEnabled(false);
        kode_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_transaksiActionPerformed(evt);
            }
        });
        jPanel14.add(kode_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 200, 30));

        jPanel9.setBackground(new java.awt.Color(25, 192, 236));

        kodee_transaksi.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        kodee_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        kodee_transaksi.setText("Kode Transaksi");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(kodee_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(kodee_transaksi)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, 20));

        jPanel2.setBackground(new java.awt.Color(25, 192, 236));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama Customer");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jPanel14.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        input_name.setText("Masukkan Nama");
        input_name.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                input_nameCaretUpdate(evt);
            }
        });
        input_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                input_nameFocusGained(evt);
            }
        });
        input_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_nameActionPerformed(evt);
            }
        });
        jPanel14.add(input_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 310, 30));

        jPanel4.setBackground(new java.awt.Color(25, 192, 236));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kiloan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jPanel14.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        input_jenis_service.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silahkan Pilih" }));
        input_jenis_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_jenis_serviceActionPerformed(evt);
            }
        });
        jPanel14.add(input_jenis_service, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 310, 30));

        jPanel10.setBackground(new java.awt.Color(25, 192, 236));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Satuan");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9)
        );

        jPanel14.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));

        jenis_pakaian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silahkan Pilih" }));
        jenis_pakaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_pakaianActionPerformed(evt);
            }
        });
        jPanel14.add(jenis_pakaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 310, 30));

        total_berat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_beratActionPerformed(evt);
            }
        });
        jPanel14.add(total_berat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 310, 30));

        jPanel6.setBackground(new java.awt.Color(25, 192, 236));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total Berat");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );

        jPanel14.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 20));

        jPanel12.setBackground(new java.awt.Color(25, 192, 236));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nomor Penempatan");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10)
        );

        jPanel14.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, 20));

        nomor_penempatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silahkan Pilih", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" }));
        nomor_penempatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomor_penempatanActionPerformed(evt);
            }
        });
        jPanel14.add(nomor_penempatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 310, 30));

        jPanel3.setBackground(new java.awt.Color(25, 192, 236));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tanggal Transaksi");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, 20));

        tgl_pengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_pengembalianActionPerformed(evt);
            }
        });
        jPanel14.add(tgl_pengembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 310, 30));

        jPanel13.setBackground(new java.awt.Color(25, 192, 236));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Catatan");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, -1, 20));

        customButton2.setText("customButton2");
        customButton2.setBackgroundColor(new java.awt.Color(25, 192, 236));
        customButton2.setBorderRadius(25);
        customButton2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        customButton2.setTextBold(0);
        customButton2.setTextColor(java.awt.Color.white);
        customButton2.setTheText("Batal");
        customButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton2ActionPerformed(evt);
            }
        });
        jPanel14.add(customButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 270, 100, 30));

        customButton6.setText("customButton2");
        customButton6.setBackgroundColor(new java.awt.Color(25, 192, 236));
        customButton6.setBorderRadius(25);
        customButton6.setTextBold(0);
        customButton6.setTextColor(java.awt.Color.white);
        customButton6.setTheText("OK");
        customButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton6ActionPerformed(evt);
            }
        });
        jPanel14.add(customButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 230, 100, 30));

        catatan.setColumns(20);
        catatan.setRows(5);
        jScrollPane1.setViewportView(catatan);

        jPanel14.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, 310, 30));

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel14.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 20, 20));

        jPanel8.setBackground(new java.awt.Color(25, 192, 236));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Harga/Kg");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, 90, 20));

        harga_kg.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        harga_kg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        harga_kg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                harga_kgActionPerformed(evt);
            }
        });
        jPanel14.add(harga_kg, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 310, 30));

        tgl_transaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tgl_transaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tgl_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_transaksiActionPerformed(evt);
            }
        });
        jPanel14.add(tgl_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 310, 30));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Harga/Kg");
        jPanel14.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -190, -1, -1));

        jPanel18.setBackground(new java.awt.Color(25, 192, 236));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal Pengambilan");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 190, 20));

        btn_tambah.setBackground(new java.awt.Color(25, 192, 236));
        btn_tambah.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah.setText("+");
        btn_tambah.setBackgroundColor(new java.awt.Color(25, 192, 236));
        btn_tambah.setTextColor(java.awt.Color.white);
        btn_tambah.setTheText("+");
        btn_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambahMouseClicked(evt);
            }
        });
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel14.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 30, 30));

        tbl_layanan.setBackground(new java.awt.Color(204, 204, 204));
        tbl_layanan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tbl_layanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/opsi.png"))); // NOI18N
        tbl_layanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_layananMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tbl_layanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tbl_layanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel14.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 40, 40));

        jPanel1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void status_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_status_bayarActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        resetAll();

    }//GEN-LAST:event_btn_cancelActionPerformed

    private void customButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton4ActionPerformed

        ResultSet rs = db.ambilData("SELECT SUM(total_kilo) AS hehe FROM detail_transaksi");
        ResultSet rs1 = db.ambilData("SELECT * FROM data_barang WHERE Status = 'berkurang'");
        if(!sudahBayar) {
            try {
                double totalBeratPadaTabel = 0.0;
                for (int i = 0; i < tblData.getRowCount(); i++) {
                    totalBeratPadaTabel += Integer.parseInt(tblData.getValueAt(i, 3).toString());
                }
                if(rs.next()) {
                    boolean item = true;
                    double totalBerat2 = ((Double.parseDouble(rs.getString("hehe") + ".0") + totalBeratPadaTabel) / 63.0) % 1;
                    System.out.println(((Double.parseDouble(rs.getString("hehe") + ".0") + totalBeratPadaTabel) / 63.0) % 1);
                    //int sabunPcs = Integer.parseInt(rs1.getString("stok").split("pcs")[0].trim());

                    while (rs1.next()) {
                        int pcs = Integer.parseInt(rs1.getString("stok").split("pcs")[0].trim());
                        System.out.println(pcs);
                        if (totalBerat2 == 0.0) {
                            if ((pcs - 1) <= -1) {
                                System.out.println("false");
                                item = false;
                            }
                        }
                    }
                    HashMap<String, String> map = new HashMap<>();
                    ResultSet hasil = db.ambilData("SELECT * FROM data_layanan");
                    for(int i = 0; hasil.next(); i++) {
                        map.put(hasil.getString("nama_layanan"), hasil.getString("id_layanan"));
                    }
                    //System.out.println(map.get(input_jenis_service.getSelectedItem().toString()));
                    if(item) {
                        ResultSet rs2 = db.ambilData("SELECT * FROM data_barang WHERE Status = 'berkurang'");
                        while(rs2.next()) {
                            int pcs = Integer.parseInt(rs2.getString("stok").split("pcs")[0].trim());
                            db.aksi("UPDATE data_barang SET stok = '" + (pcs - 1) + " pcs' WHERE nama_barang = '" + rs2.getString("nama_barang") + "'");
                        }
                        if(!status_bayar.getSelectedItem().toString().equals("Belum Dibayar")) {
                            db.aksi("INSERT INTO `laporan`( `kode_transaksi`, `nama_customer`, tgl_transaksi, subtotal, status, bayar, kembalian, no_penempatan, keterangan) VALUES "
                                + "('" + kode_transaksi.getText() + "','" + input_name.getText() + "', '" + LocalDate.now() + " " + LocalTime.now().toString().substring(0, 8) + "', '" + lb_totalharga.getText() + "', '" + status_bayar.getSelectedItem() + "' ,'" + bayar.getText() + "', '" + kembalian.getText() + "', '" + nomor_penempatan.getSelectedItem() + "', 'Belum Diambil')");
                            for (int i = 0; i < tblData.getRowCount(); i++) {
                                //String jenisKiloan = tblData.getValueAt(i, 2).toString();
                                String totalBerat = tblData.getValueAt(i, 3).toString();
                                String totalharga = tblData.getValueAt(i, 5).toString();
                                String tglPengambilan = tblData.getValueAt(i, 6).toString();
                                String tglpesanan = tblData.getValueAt(i, 7).toString();
                                String txtcatatan = tblData.getValueAt(i, 8).toString();
                                String jenisLayanan = map.get(tblData.getValueAt(i, 2));
                                System.out.println(jenisLayanan);

                                db.aksi("INSERT INTO `detail_transaksi`(`id_transaksi`, `jenis_service`, `total_harga`,`tgl_pesanan`, `tgl_pengambilan`, `catatan`,`total_kilo`) VALUES "
                                    + "('" + kode_transaksi.getText() + "','" + jenisLayanan + "','" + totalharga + "','" + tglpesanan + "','" + tglPengambilan + "','" + txtcatatan + "','" + totalBerat + "')");

                            }
                            JOptionPane.showMessageDialog(this, "Simpan berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            db.aksi("INSERT INTO `laporan`( `kode_transaksi`, `nama_customer`, tgl_transaksi, subtotal, status, bayar, kembalian, no_penempatan, keterangan) VALUES "
                                + "('" + kode_transaksi.getText() + "','" + input_name.getText() + "', '" + LocalDate.now() + " " + LocalTime.now().toString().substring(0, 8) + "', '" + lb_totalharga.getText() + "', '" + status_bayar.getSelectedItem() + "' ,'0', '0', '" + nomor_penempatan.getSelectedItem() + "', 'Belum Diambil')");
                            for (int i = 0; i < tblData.getRowCount(); i++) {
                                String jenisKiloan = tblData.getValueAt(i, 2).toString();
                                String totalBerat = tblData.getValueAt(i, 3).toString();
                                String totalharga = tblData.getValueAt(i, 5).toString();
                                String tglPengambilan = tblData.getValueAt(i, 6).toString();
                                String tglpesanan = tblData.getValueAt(i, 7).toString();
                                String txtcatatan = tblData.getValueAt(i, 8).toString();
                                String jenisLayanan = map.get(tblData.getValueAt(i, 2));

                                db.aksi("INSERT INTO `detail_transaksi`(`id_transaksi`, `jenis_service`, `total_harga`,`tgl_pesanan`, `tgl_pengambilan`, `catatan`,`total_kilo`) VALUES "
                                    + "('" + kode_transaksi.getText() + "','" + jenisLayanan + "','" + totalharga + "','" + tglpesanan + "','" + tglPengambilan + "','" + txtcatatan + "','" + totalBerat + "')");
                            }
                            JOptionPane.showMessageDialog(this, "Simpan berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);

                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Sabun anda tidak cukup!\nPeriksa sabun anda!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(sudahBayar) {
            db.aksi("UPDATE laporan SET bayar = '" + bayar.getText() + "', kembalian = '" + kembalian.getText() + "', status = '" + status_bayar.getSelectedItem() + "' WHERE kode_transaksi = '" + kode_transaksi.getText() + "'");
            JOptionPane.showMessageDialog(this, "Update Berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_customButton4ActionPerformed

    private void customButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton7ActionPerformed
        // TODO add your handling code here:
        //String sql ="insert into laporan values(?)";

        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(kode_transaksi.getText());
            barcode.setI(11.0f);
            String fname = kode_transaksi.getText();

            barcode.renderBarcode("src/image/" + fname + ".jpg");
            File ffff = new File("src/image/" + fname + ".jpg");
            db.koneksi();

            ResultSet rs2 = db.ambilData("SELECT * FROM detail_transaksi WHERE id_transaksi = '" + kode_transaksi.getText() + "' ORDER BY tgl_pengambilan DESC");
            rs2.next();
            if (status_bayar.getSelectedItem().toString().equals("Sudah Dibayar")) {
                File f = new File("src/struk/report.jasper");
                HashMap<String, Object> param = new HashMap<>();
                param.put("no_tr", new String(kode_transaksi.getText()));
                param.put("tanggal_pengambilan", new String(rs2.getString("tgl_pengambilan")));
                JasperPrint print = JasperFillManager.fillReport(f.getAbsolutePath(), param, db.con);
                JasperViewer.viewReport(print, true);

                ResultSet rs = db.ambilData("SELECT * FROM laporan WHERE kode_transaksi = '" + kode_transaksi.getText() + "'");
                if (rs.next()) {
                    System.out.println(ffff.getAbsolutePath());
                    File format = new File("src/struk/report_barcode.jasper");
                    HashMap<String, Object> parahmen = new HashMap<>();
                    parahmen.put("img_path", new String(ffff.getAbsolutePath()));
                    parahmen.put("no_tr", new String(kode_transaksi.getText()));
                    parahmen.put("tanggal_pengambilan", new String(rs2.getString("tgl_pengambilan")));
                    JasperPrint printer = JasperFillManager.fillReport(format.getAbsolutePath(), parahmen, db.con);

                    JasperViewer.viewReport(printer, false);

                }

                resetAll();

            } else {
                File f = new File("src/struk/report_barcode.jasper");
                HashMap<String, Object> param = new HashMap<>();
                param.put("img_path", new String(ffff.getAbsolutePath()));
                param.put("no_tr", new String(kode_transaksi.getText()));
                param.put("tanggal_pengambilan", new String(rs2.getString("tgl_pengambilan")));
                JasperPrint print = JasperFillManager.fillReport(f.getAbsolutePath(), param, db.con);

                JasperViewer.viewReport(print, false);
                resetAll();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mencetak barcode" + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_customButton7ActionPerformed

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here
        int a1, a2, kembali;
        a1 = Integer.parseInt(lb_totalharga.getText());
        a2 = Integer.parseInt(bayar.getText());
        if (a2 < a1) {
            JOptionPane.showMessageDialog(null, "Maaf, Uang Anda Tidak Cukup!");
        } else {
            kembali = a2 - a1;
            kembalian.setText(String.valueOf(kembali));
            //.requestFocus();
            kembalian.setText(String.valueOf(Integer.parseInt(String.valueOf(bayar.getText())) - Integer.parseInt(String.valueOf(lb_totalharga.getText()))));
        }
        status_bayar.setSelectedItem("Sudah Dibayar");
    }//GEN-LAST:event_bayarActionPerformed

    private void kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalianActionPerformed

    private void total_hargaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_total_hargaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_total_hargaCaretUpdate

    private void total_hargaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_total_hargaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_total_hargaFocusGained

    private void total_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_hargaActionPerformed

    private void kode_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_transaksiActionPerformed
        // TODO add your handling code here:
        ResultSet rs = db.ambilData("SELECT * FROM laporan WHERE kode_transaksi = '" + kode_transaksi.getText() + "'");
        try {
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Pelanggan '" + rs.getString("nama_customer") + "' barang berada di No. Penempatan '" + rs.getString("no_penempatan"));
                int jwb = JOptionPane.showConfirmDialog(this, "Apakah barang sudah dibayar?", "Peringatan", JOptionPane.YES_NO_OPTION);
                if(jwb == JOptionPane.YES_OPTION) {
                    // update
                    int ans = JOptionPane.showConfirmDialog(this, "Apakah barang akan / sudah diambil?", "Peringatan", JOptionPane.YES_NO_OPTION);
                    if(ans == JOptionPane.YES_OPTION) {
                        db.aksi("UPDATE laporan SET keterangan = 'Sudah Diambil' WHERE kode_transaksi = '" + kode_transaksi.getText() + "'");
                    }
                } else {
                    lb_totalharga.setText(rs.getString("subtotal"));
                    sudahBayar = true;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Data dengan kode transaksi '" + kode_transaksi.getText() + "' tidak tersedia dalam database!\nSilahkan periksa kembali kode transaksi!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_kode_transaksiActionPerformed

    private void input_nameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_input_nameCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_input_nameCaretUpdate

    private void input_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_input_nameFocusGained
        // TODO add your handling code here:
        String input = input_name.getText();
        if (input.equals("Masukkan Nama")) {
            input_name.setText("");
        }
    }//GEN-LAST:event_input_nameFocusGained

    private void input_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_nameActionPerformed

    private void input_jenis_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_jenis_serviceActionPerformed
        try {
            // TODO add your handling code here:
            if ((input_jenis_service.getSelectedItem().equals("Silahkan Pilih"))) {
                jenis_pakaian.setEnabled(true);
            } else {
                ResultSet rs = db.ambilData("SELECT * FROM data_layanan WHERE nama_layanan = '" + input_jenis_service.getSelectedItem() + "'");
                if (rs.next()) {
                    jenis_pakaian.setEnabled(false);
                    harga_kg.setText(rs.getString("harga_layanan"));
                    tgl_pengembalian.setText(setDateFromMili(Integer.parseInt(rs.getString("tgl_layanan"))));
                    System.out.println(rs.getString("tgl_layanan"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_input_jenis_serviceActionPerformed

    private void jenis_pakaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_pakaianActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            if ((jenis_pakaian.getSelectedItem().equals("Silahkan Pilih"))) {
                input_jenis_service.setEnabled(true);
            } else {
                ResultSet rs = db.ambilData("SELECT * FROM data_layanan WHERE nama_layanan = '" + jenis_pakaian.getSelectedItem() + "'");
                if (rs.next()) {
                    input_jenis_service.setEnabled(false);
                    harga_kg.setText(rs.getString("harga_layanan"));
                    tgl_pengembalian.setText(setDateFromMili(Integer.parseInt(rs.getString("tgl_layanan"))));
                    System.out.println(rs.getString("tgl_layanan"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jenis_pakaianActionPerformed

    private void total_beratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_beratActionPerformed
        // TODO add your handling code here:
        String kilo = harga_kg.getText();
        String berat = total_berat.getText();
        total_harga.setText(String.valueOf(Integer.parseInt(berat) * Integer.parseInt(kilo)));
    }//GEN-LAST:event_total_beratActionPerformed

    private void nomor_penempatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomor_penempatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomor_penempatanActionPerformed

    private void tgl_pengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_pengembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_pengembalianActionPerformed

    private void customButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton2ActionPerformed
        // TODO add your handling code here:
        int row = tblData.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda ingin hapus data ini?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(row);
                tblData.setModel(model);
            }

        }
    }//GEN-LAST:event_customButton2ActionPerformed

    private void customButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton6ActionPerformed
        // TODO add your handling code here:
        addRow();
        countTotal();
    }//GEN-LAST:event_customButton6ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (!kode_transaksi.isEnabled()) {
            kode_transaksi.setEnabled(true);
        } else {
            kode_transaksi.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void harga_kgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_harga_kgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_harga_kgActionPerformed

    private void tgl_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_transaksiActionPerformed

    private void btn_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMouseClicked
        // TODO add your handling code here:
//        nambah_layanan db1 = new nambah_layanan(role);
//        db1.setVisible(true);
//        this.setVisible(false);
    }//GEN-LAST:event_btn_tambahMouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
         defaultform f = new defaultform(13, 0);
        f.setDimens(1086, 595);
        f.setComp(13, 0);
        login.showForm(f);
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void tbl_layananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_layananMouseClicked
        // TODO add your handling code here:
//        tabel_layanan db1 = new tabel_layanan(role);
//        db1.setVisible(true);
//        this.setVisible(false);
defaultform f = new defaultform(14, 0);
        f.setDimens(1086, 595);
        f.setComp(14, 0);
        login.showForm(f);
    }//GEN-LAST:event_tbl_layananMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bayar;
    private project.CustomButtons btn_cancel;
    private project.CustomButtons btn_tambah;
    private javax.swing.JTextArea catatan;
    private project.CustomButtons customButton2;
    private project.CustomButtons customButton4;
    private project.CustomButtons customButton6;
    private project.CustomButtons customButton7;
    private javax.swing.JTextField harga_kg;
    private javax.swing.JComboBox<String> input_jenis_service;
    private javax.swing.JTextField input_name;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jenis_pakaian;
    private javax.swing.JTextField kembalian;
    private javax.swing.JTextField kode_transaksi;
    private javax.swing.JLabel kodee_transaksi;
    private javax.swing.JLabel lb_totalharga;
    private javax.swing.JComboBox<String> nomor_penempatan;
    private javax.swing.JComboBox<String> status_bayar;
    private javax.swing.JTable tblData;
    private javax.swing.JLabel tbl_layanan;
    private javax.swing.JTextField tgl_pengembalian;
    private javax.swing.JTextField tgl_transaksi;
    private javax.swing.JTextField total_berat;
    private javax.swing.JTextField total_harga;
    // End of variables declaration//GEN-END:variables
}
