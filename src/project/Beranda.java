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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author ASUS
 */
public class Beranda extends javax.swing.JFrame {boolean drop = false;

   
    private Timer timer;
    private Beranda modelLogin;
    String role;

    /**
     * Creates new form login
     * @param <error>
     */
    public Beranda(String role) {
        initComponents();
        pn_hilang1.setVisible(drop);
        this.role = role;
        if(role.equals("staff")) {
//            bt_databarang.setVisible(false);
//            bt_laporan1.setVisible(false);
//            btn_karyawan.setVisible(false);

            //lbLogin.setText("Masuk Sebagai Karyawan");
        }
        
         //lb_level.setText( modelLogin.getrole());
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\box.png", 60, 60, img_box, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\payment.png", 60, 60, img_transaksi2, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\box1.png", 60, 60, img_box1, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\laporan.png", 60, 60, img_laporan1, this);
        //resizeImage("C:\\Users\\ASUS\\Documents\\Ari\\JavaApplication13\\src\\image\\logout.png", 80, 80, img_logout, this);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //execute (modelLogin);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDayDateTime();
            }
        });

        timer.start();
      
    }

    
   // public static void resizeImage(String path, int width, int height, JLabel theLabel, JFrame theFrame) {
       // try {
          //  BufferedImage bi = ImageIO.read(new File(path));
          //  Image i = bi.getScaledInstance(width, height, Image.SCALE_DEFAULT);
           // ImageIcon ii = new ImageIcon(i);
            //JLabel label = new JLabel();
           // theLabel.setIcon(ii);
            //theFrame.add(label);//

      //  } catch (Exception e) {
          //  e.printStackTrace();
       // }
   // } 

    private void showDayDateTime() {
        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        SimpleDateFormat formatHari = new SimpleDateFormat("EEEE", new Locale("in", "ID"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hari = formatHari.format(calendar.getTime());
        String dateTime = dateFormat.format(now);
        lb_date.setText(hari + ", " + dateTime);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanelGradient2 = new pallette.JPanelGradient();
        txt_navbar = new javax.swing.JLabel();
        lb_date = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        home = new javax.swing.JLabel();
        btn_transaksi = new project.CustomButtons();
        pn_hilang1 = new javax.swing.JPanel();
        btn_databarang1 = new project.CustomButtons();
        btn_pengeluaran = new project.CustomButtons();
        btn_karyawan1 = new project.CustomButtons();
        btn_laporan1 = new project.CustomButtons();
        btn_pemasukan1 = new project.CustomButtons();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgfinal-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jLabel2)
                .addContainerGap(370, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel2)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 1190, 640));

        jPanelGradient2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_navbar.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        txt_navbar.setForeground(new java.awt.Color(255, 255, 255));
        txt_navbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_navbar.setText("SELAMAT DATANG DI LAUNDRY Q");
        jPanelGradient2.add(txt_navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 892, 60));

        lb_date.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lb_date.setForeground(new java.awt.Color(255, 255, 255));
        jPanelGradient2.add(lb_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 276, 36));

        jPanel1.add(jPanelGradient2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 60));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel3.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 138, -1));

        btn_transaksi.setText("customButtons1");
        btn_transaksi.setTheText("Transaksi");
        jPanel3.add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 130, 30));

        pn_hilang1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pn_hilang1Layout = new javax.swing.GroupLayout(pn_hilang1);
        pn_hilang1.setLayout(pn_hilang1Layout);
        pn_hilang1Layout.setHorizontalGroup(
            pn_hilang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pn_hilang1Layout.setVerticalGroup(
            pn_hilang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        jPanel3.add(pn_hilang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 130, -1));

        btn_databarang1.setText("customButtons1");
        btn_databarang1.setTheText("Data Barang");
        jPanel3.add(btn_databarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 130, 30));

        btn_pengeluaran.setText("customButtons1");
        btn_pengeluaran.setTheText("Pengeluaran");
        jPanel3.add(btn_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 130, 30));

        btn_karyawan1.setText("customButtons1");
        btn_karyawan1.setTheText("Data Karyawan");
        jPanel3.add(btn_karyawan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 130, 30));

        btn_laporan1.setText("customButtons1");
        btn_laporan1.setTheText("Laporan");
        jPanel3.add(btn_laporan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 130, 30));

        btn_pemasukan1.setText("customButtons1");
        btn_pemasukan1.setTheText("Pemasukan");
        jPanel3.add(btn_pemasukan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 130, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 150, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_databarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_databarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_databarangActionPerformed

    private void bt_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_logoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_logoutActionPerformed

    private void bt_databarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_databarangMouseClicked
        // TODO add your handling code here:
        data_barang1 db1 = new data_barang1(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_databarangMouseClicked

    private void bt_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_logoutMouseClicked
        // TODO add your handling code here:
        login db1 = new login();
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_logoutMouseClicked

    private void bt_laporan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_laporan1ActionPerformed
        // TODO add your handling code here:
        if(drop) {
            pn_hilang1.setVisible(drop);
            drop = false;

        }else {
            pn_hilang1.setVisible(drop);
            drop = true;
        }
    }//GEN-LAST:event_bt_laporan1ActionPerformed

    private void bt_pemasukan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_pemasukan1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Laporan(role).setVisible(true);
    }//GEN-LAST:event_bt_pemasukan1MouseClicked

    private void bt_pemasukan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pemasukan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_pemasukan1ActionPerformed

    private void bt_pengeluaran1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_pengeluaran1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new pengeluaran(role).setVisible(true);
    }//GEN-LAST:event_bt_pengeluaran1MouseClicked

    private void bt_pengeluaran1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pengeluaran1ActionPerformed
        // TODO add your handling code here:
        new pengeluaran(role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bt_pengeluaran1ActionPerformed

    private void bt_transaksi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_transaksi1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_transaksi1MouseClicked

    private void bt_transaksi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_transaksi1ActionPerformed
        // TODO add your handling code here:
         Transaksi db1 = new Transaksi(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_transaksi1ActionPerformed

    private void btn_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_karyawanMouseClicked
        // TODO add your handling code here:
        data_karyawan db1 = new data_karyawan(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_karyawanMouseClicked

    private void btn_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_karyawanActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_karyawanActionPerformed

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
        //Beranda db1 = new Beranda();
        //db1.setVisible(true);
        //this.setVisible(false);
    }//GEN-LAST:event_homeMouseClicked

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
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Beranda("staff").setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private project.CustomButtons btn_databarang1;
    private project.CustomButtons btn_karyawan1;
    private project.CustomButtons btn_laporan1;
    private project.CustomButtons btn_pemasukan1;
    private project.CustomButtons btn_pengeluaran;
    private project.CustomButtons btn_transaksi;
    private javax.swing.JLabel home;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private pallette.JPanelGradient jPanelGradient2;
    private javax.swing.JLabel lb_date;
    private javax.swing.JPanel pn_hilang1;
    private javax.swing.JLabel txt_navbar;
    // End of variables declaration//GEN-END:variables

    private void execute(Beranda modelLogin1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //if (modelLogin.getLevel().equals("owner")) {
        //pn_menu();
    }
    }
    //private String getLevel() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

    //private String getrole() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

    //private void addMenu() {
  
      // for (int i = 0; i <addMenu.length;) {
    //}
//}
