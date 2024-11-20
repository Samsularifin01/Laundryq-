/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project;

import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class data_karyawan extends javax.swing.JFrame {boolean drop = false;
String role;
  koneksi db = new koneksi();
  DefaultTableModel model = new DefaultTableModel();
  


    /**
     * Creates new form nambah_karyawan
     */
    public data_karyawan(String role) {
        initComponents();
        addcoloum();
        Bariss();
                
                
         pn_hilang1.setVisible(drop);
          this.setExtendedState(JFrame.MAXIMIZED_BOTH);
          this.role = role;
          if(role.equals("staff")) {
            btn_databarang.setVisible(false);
            btn_laporan.setVisible(false);
     }
    }

     public void addcoloum(){
        model.setColumnCount(0);
        model.addColumn("Nama");
        model.addColumn("email");
        model.addColumn("Telepon");
        model.addColumn("Alamat");
        model.addColumn("username");
        model.addColumn("Password");
        model.addColumn("Kelamin");
        model.addColumn("Role");
        tabel_data.setModel(model);
        
    }
     public void Bariss(){
        model.setRowCount(0);
        ResultSet hasil = db.ambilData("SELECT * FROM login");
        try {
            while(hasil.next()) {
                model.addRow(new Object[]
                {   //hasil.getString("id_laporan"),
                    hasil.getString("nama_pengguna"),
                    hasil.getString("email"),
                    hasil.getString("telepon"),
                    hasil.getString("alamat"),
                    hasil.getString("username"),
                    hasil.getString("password"),
                    hasil.getString("kelamin"),
                    hasil.getString("role")});
            }
             tabel_data.setModel(model);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        jPanelGradient2 = new pallette.JPanelGradient();
        txt_navbar = new javax.swing.JLabel();
        lb_date = new javax.swing.JLabel();
        pn_menu = new pallette.JPanelGradient();
        home = new javax.swing.JLabel();
        pn_hilang1 = new javax.swing.JPanel();
        btn_databarang = new project.CustomButtons();
        btn_transaksi = new project.CustomButtons();
        btn_karyawan = new project.CustomButtons();
        btn_laporan = new project.CustomButtons();
        btn_pemasukan = new project.CustomButtons();
        btn_pengeluaran = new project.CustomButtons();
        pn_hilang2 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bg = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data = new project.CustomTable();
        hapus = new project.CustomButtons();
        tambah_karyawan3 = new project.CustomButtons();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelGradient2.setColorEnd(new java.awt.Color(25, 192, 236));
        jPanelGradient2.setColorStart(new java.awt.Color(25, 192, 236));
        jPanelGradient2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_navbar.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        txt_navbar.setForeground(new java.awt.Color(255, 255, 255));
        txt_navbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_navbar.setText("DATA KARYAWAN");
        jPanelGradient2.add(txt_navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 892, 60));

        lb_date.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lb_date.setForeground(new java.awt.Color(255, 255, 255));
        jPanelGradient2.add(lb_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 276, 36));

        jPanel1.add(jPanelGradient2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 60));

        pn_menu.setBackground(new java.awt.Color(247, 252, 253));
        pn_menu.setColorStart(new java.awt.Color(255, 255, 255));
        pn_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.png"))); // NOI18N
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        pn_menu.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 138, -1));

        pn_hilang1.setBackground(new java.awt.Color(255, 255, 255));
        pn_hilang1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pn_menu.add(pn_hilang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 130, 80));

        btn_databarang.setText("customButtons1");
        btn_databarang.setTheText("Data Barang");
        pn_menu.add(btn_databarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 130, 30));

        btn_transaksi.setText("customButtons1");
        btn_transaksi.setTheText("Transaksi");
        pn_menu.add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 130, 30));

        btn_karyawan.setText("customButtons1");
        btn_karyawan.setTheText("Data Karyawan");
        pn_menu.add(btn_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 130, 30));

        btn_laporan.setText("customButtons1");
        btn_laporan.setTheText("Laporan");
        pn_menu.add(btn_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 130, 30));

        btn_pemasukan.setText("customButtons1");
        btn_pemasukan.setTheText("Pemasukan");
        pn_menu.add(btn_pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 130, 30));

        btn_pengeluaran.setText("customButtons1");
        btn_pengeluaran.setTheText("Pengeluaran");
        pn_menu.add(btn_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 130, 30));

        pn_hilang2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pn_hilang2Layout = new javax.swing.GroupLayout(pn_hilang2);
        pn_hilang2.setLayout(pn_hilang2Layout);
        pn_hilang2Layout.setHorizontalGroup(
            pn_hilang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        pn_hilang2Layout.setVerticalGroup(
            pn_hilang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
        );

        pn_menu.add(pn_hilang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 130, -1));

        jPanel1.add(pn_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 150, 640));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(25, 192, 236));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_data.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel_data);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1110, 490));

        hapus.setForeground(new java.awt.Color(255, 255, 255));
        hapus.setText("Data jenis Laundry");
        hapus.setBackgroundColor(new java.awt.Color(255, 0, 51));
        hapus.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        hapus.setName("Data jenis Laundry\n"); // NOI18N
        hapus.setTextColor(java.awt.Color.white);
        hapus.setTheText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel3.add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, 120, 30));

        tambah_karyawan3.setForeground(new java.awt.Color(255, 255, 255));
        tambah_karyawan3.setText("Data jenis Laundry");
        tambah_karyawan3.setBackgroundColor(new java.awt.Color(25, 192, 236));
        tambah_karyawan3.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tambah_karyawan3.setName("Data jenis Laundry\n"); // NOI18N
        tambah_karyawan3.setTextColor(java.awt.Color.white);
        tambah_karyawan3.setTheText("Tambah");
        tambah_karyawan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_karyawan3ActionPerformed(evt);
            }
        });
        jPanel3.add(tambah_karyawan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 120, 30));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1110, 620));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 1190, 640));

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

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
        //Beranda db1 = new Beranda();
        //db1.setVisible(true);
        //this.setVisible(false);
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

    private void bt_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_logoutMouseClicked
        // TODO add your handling code here:
        login db1 = new login();
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_logoutMouseClicked

    private void bt_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_logoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_logoutActionPerformed

    private void bt_laporan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_laporan1ActionPerformed
        //TODO add your handling code here://
        if(drop) {
            pn_hilang1.setVisible(drop);
            drop = false;

        }else {
            pn_hilang1.setVisible(drop);
            drop = true;
        }
    }//GEN-LAST:event_bt_laporan1ActionPerformed

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

    private void bt_pemasukan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_pemasukan1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Laporan(role).setVisible(true);
    }//GEN-LAST:event_bt_pemasukan1MouseClicked

    private void bt_pemasukan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pemasukan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_pemasukan1ActionPerformed

    private void bt_transaksi2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_transaksi2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_transaksi2MouseClicked

    private void bt_transaksi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_transaksi2ActionPerformed
        // TODO add your handling code here:
        Transaksi db1 = new Transaksi(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_transaksi2ActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
         int row = tabel_data.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda ingin hapus data ini?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                db.aksi("DELETE FROM login WHERE nama_pengguna = '" + tabel_data.getValueAt(row, 0) + "'");
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                Bariss();
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void jenis_laundry2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jenis_laundry2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jenis_laundry2MouseClicked

    private void jenis_laundry2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_laundry2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenis_laundry2ActionPerformed

    private void tambah_karyawan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_karyawan3ActionPerformed
        // TODO add your handling code here:
        nambah_karyawan db1 = new nambah_karyawan (role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_tambah_karyawan3ActionPerformed

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
            java.util.logging.Logger.getLogger(data_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data_karyawan("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private project.CustomButtons btn_databarang;
    private project.CustomButtons btn_karyawan;
    private project.CustomButtons btn_laporan;
    private project.CustomButtons btn_pemasukan;
    private project.CustomButtons btn_pengeluaran;
    private project.CustomButtons btn_transaksi;
    private project.CustomButtons hapus;
    private javax.swing.JLabel home;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private pallette.JPanelGradient jPanelGradient2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_date;
    private javax.swing.JPanel pn_hilang1;
    private javax.swing.JPanel pn_hilang2;
    private pallette.JPanelGradient pn_menu;
    private project.CustomTable tabel_data;
    private project.CustomButtons tambah_karyawan3;
    private javax.swing.JLabel txt_navbar;
    // End of variables declaration//GEN-END:variables
}
