/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project;

import javax.swing.JFrame;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class nambah_layanan extends javax.swing.JFrame {
String role;
koneksi db = new koneksi();



    /**
     * Creates new form nambah_karyawan
     */
    public nambah_layanan(String role) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.role = role;
        db.koneksi();
         ResultSet rs = db.ambilData("SELECT * FROM data_layanan order by id_layanan desc");
            try {
                if(rs.next()) {
                    int id = Integer.parseInt(rs.getString("id_layanan").substring(2, 4)); //KP01 
                    id++;
                    if(id <= 9) {
                        txt_kode_layanan.setText(rs.getString("id_layanan").substring(0, 2) + "0" + id);
                    } else if(id <= 99) {
                        txt_kode_layanan.setText(rs.getString("id_layanan").substring(0, 2) + "" + id);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
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
        jPanel3 = new javax.swing.JPanel();
        tambah = new project.CustomButtons();
        nama_layanan = new javax.swing.JTextField();
        harga_layanan = new javax.swing.JTextField();
        batal = new project.CustomButtons();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        opsi_layanan = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        tgl_layanan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_kode_layanan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(735, 380));
        jPanel1.setPreferredSize(new java.awt.Dimension(735, 380));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelGradient2.setColorEnd(new java.awt.Color(25, 192, 236));
        jPanelGradient2.setColorStart(new java.awt.Color(25, 192, 236));
        jPanelGradient2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_navbar.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        txt_navbar.setForeground(new java.awt.Color(255, 255, 255));
        txt_navbar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_navbar.setText("Tambah Jenis Layanan");
        jPanelGradient2.add(txt_navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 830, 60));

        lb_date.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lb_date.setForeground(new java.awt.Color(255, 255, 255));
        jPanelGradient2.add(lb_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 276, 36));

        jPanel1.add(jPanelGradient2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tambah.setForeground(new java.awt.Color(255, 255, 255));
        tambah.setText("Tambah");
        tambah.setBackgroundColor(new java.awt.Color(25, 192, 236));
        tambah.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tambah.setName("Tambah"); // NOI18N
        tambah.setTextColor(java.awt.Color.white);
        tambah.setTheText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel3.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 140, 31));

        nama_layanan.setText("Masukkan nama");
        nama_layanan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nama_layananFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nama_layananFocusLost(evt);
            }
        });
        nama_layanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_layananActionPerformed(evt);
            }
        });
        jPanel3.add(nama_layanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 320, 33));

        harga_layanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                harga_layananActionPerformed(evt);
            }
        });
        jPanel3.add(harga_layanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 320, 30));

        batal.setForeground(new java.awt.Color(255, 255, 255));
        batal.setText("Batal");
        batal.setBackgroundColor(new java.awt.Color(255, 0, 51));
        batal.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        batal.setName("Data jenis Laundry\n"); // NOI18N
        batal.setTextColor(java.awt.Color.white);
        batal.setTheText("Batal");
        batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batalMouseClicked(evt);
            }
        });
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });
        jPanel3.add(batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, 144, 31));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Harga Kilo/Satuan");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 150, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Nama Service");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 124, -1));

        opsi_layanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silahkan Pilih", "Kiloan", "Satuan" }));
        opsi_layanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opsi_layananActionPerformed(evt);
            }
        });
        jPanel3.add(opsi_layanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 320, 33));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setText("Kode Layanan");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 124, -1));

        tgl_layanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_layananActionPerformed(evt);
            }
        });
        jPanel3.add(tgl_layanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 320, 33));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Tanggal Pengambilan");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 170, -1));

        txt_kode_layanan.setEnabled(false);
        txt_kode_layanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kode_layananActionPerformed(evt);
            }
        });
        jPanel3.add(txt_kode_layanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 200, 30));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setText("Jenis Layanan");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 124, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 890, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tgl_layananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_layananActionPerformed
        // TODO add your handling code here:
        tambah.requestFocus();
    }//GEN-LAST:event_tgl_layananActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batalActionPerformed

    private void harga_layananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_harga_layananActionPerformed
        // TODO add your handling code here:
        tgl_layanan.requestFocus();
    }//GEN-LAST:event_harga_layananActionPerformed

    private void nama_layananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_layananActionPerformed
        // TODO add your handling code here:
        harga_layanan.requestFocus();
    }//GEN-LAST:event_nama_layananActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        db.aksi("INSERT INTO `data_layanan`(`id_layanan`,`jenis_layanan`, `nama_layanan`, `harga_layanan`, `tgl_layanan`) VALUES ('"+txt_kode_layanan.getText()+"','"+opsi_layanan.getSelectedItem()+"','"+nama_layanan.getText()+"','"+harga_layanan.getText()+"','"+tgl_layanan.getText()+"')");
        JOptionPane.showMessageDialog(null, "Tambah Berhasil", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
        tabel_layanan db1 = new tabel_layanan(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_tambahActionPerformed

    private void batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalMouseClicked
        // TODO add your handling code here:
        Transaksi db1 = new Transaksi(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_batalMouseClicked

    private void opsi_layananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opsi_layananActionPerformed
        // TODO add your handling code here:
        nama_layanan.requestFocus();
    }//GEN-LAST:event_opsi_layananActionPerformed

    private void txt_kode_layananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kode_layananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kode_layananActionPerformed

    private void nama_layananFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nama_layananFocusGained
        // TODO add your handling code here:
            String input=nama_layanan.getText();
        if(input.equals("Masukkan nama")){
            nama_layanan.setText("");
        } 
    }//GEN-LAST:event_nama_layananFocusGained

    private void nama_layananFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nama_layananFocusLost
        // TODO add your handling code here:
           String input=nama_layanan.getText();
        if(input.equals("")){
            nama_layanan.setText("Masukkan nama");
        } 
    }//GEN-LAST:event_nama_layananFocusLost

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
            java.util.logging.Logger.getLogger(nambah_layanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nambah_layanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nambah_layanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nambah_layanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nambah_layanan("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private project.CustomButtons batal;
    private javax.swing.JTextField harga_layanan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private pallette.JPanelGradient jPanelGradient2;
    private javax.swing.JLabel lb_date;
    private javax.swing.JTextField nama_layanan;
    private javax.swing.JComboBox<String> opsi_layanan;
    private project.CustomButtons tambah;
    private javax.swing.JTextField tgl_layanan;
    private javax.swing.JTextField txt_kode_layanan;
    private javax.swing.JLabel txt_navbar;
    // End of variables declaration//GEN-END:variables
}
