/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class tambah_barang extends javax.swing.JFrame {
    koneksi db = new koneksi();
String role;
    /**
     * Creates new form login
     */
    public tambah_barang(String role) {
        initComponents();
         this.role = role;
        this.setLocationRelativeTo(this);
        ResultSet rs = db.ambilData("SELECT * FROM data_barang order by kode_barang desc");
            try {
                if(rs.next()) {
                    int id = Integer.parseInt(rs.getString("kode_barang").substring(2, 4)); //KP01 
                    id++;
                    if(id < 9) {
                        txt_kodebarang.setText(rs.getString("kode_barang").substring(0, 2) + "0" + id);
                    } else if(id < 99) {
                        txt_kodebarang.setText(rs.getString("kode_barang").substring(0, 2) + "" + id);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_kodebarang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_harga = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_stok = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_namabarang1 = new javax.swing.JTextField();
        customButton1 = new project.CustomButtons();
        customButton3 = new project.CustomButtons();
        status = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(25, 192, 236));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TAMBAH BARANG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 735, -1));

        txt_kodebarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_kodebarangFocusGained(evt);
            }
        });
        txt_kodebarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodebarangActionPerformed(evt);
            }
        });
        jPanel1.add(txt_kodebarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 200, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Stok");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 60, -1));

        txt_harga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_hargaFocusGained(evt);
            }
        });
        jPanel1.add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 320, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Kode Barang");
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 140, -1));

        txt_stok.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_stokFocusGained(evt);
            }
        });
        txt_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stokActionPerformed(evt);
            }
        });
        jPanel1.add(txt_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 320, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Harga");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 70, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Status");
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 80, -1));

        txt_namabarang1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_namabarang1FocusGained(evt);
            }
        });
        txt_namabarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namabarang1ActionPerformed(evt);
            }
        });
        jPanel1.add(txt_namabarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 320, 30));

        customButton1.setText("customButton1");
        customButton1.setBackgroundColor(new java.awt.Color(25, 192, 236));
        customButton1.setTextColor(new java.awt.Color(255, 255, 255));
        customButton1.setTheText("Batal");
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(customButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 360, 130, 30));

        customButton3.setText("customButton1");
        customButton3.setBackgroundColor(new java.awt.Color(25, 192, 236));
        customButton3.setTextColor(new java.awt.Color(255, 255, 255));
        customButton3.setTheText("Tambah");
        customButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(customButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 130, 30));

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silahkan Pilih", "berkurang", "tidak berkurang" }));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });
        jPanel1.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 170, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Nama Barang");
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_kodebarangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_kodebarangFocusGained

    }//GEN-LAST:event_txt_kodebarangFocusGained

    private void txt_kodebarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodebarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodebarangActionPerformed

    private void txt_hargaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hargaFocusGained
        String pass=txt_harga.getText();
        if(pass.equals("Username")){
            txt_harga.setText("");
        }
    }//GEN-LAST:event_txt_hargaFocusGained

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged

    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed

    }//GEN-LAST:event_jPanel1MousePressed

    private void txt_stokFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_stokFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stokFocusGained

    private void txt_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stokActionPerformed
        // TODO add your handling code here:
        status.requestFocus();
    }//GEN-LAST:event_txt_stokActionPerformed

    private void txt_namabarang1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_namabarang1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namabarang1FocusGained

    private void txt_namabarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namabarang1ActionPerformed
        // TODO add your handling code here:
        txt_stok.requestFocus();
    }//GEN-LAST:event_txt_namabarang1ActionPerformed

    private void customButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton3ActionPerformed
        // TODO add your handling code here:
        db.aksi("INSERT INTO data_barang (kode_barang, nama_barang, stok, harga, Status) VALUES ('" + txt_kodebarang.getText() + "', '" + txt_namabarang1.getText() + "', '" + txt_stok.getText() + "', '" + txt_harga.getText() + "', '" + status.getSelectedItem() + "')");
        JOptionPane.showMessageDialog(null, "Tambah Berhasil", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
        data_barang1 db1 = new data_barang1(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_customButton3ActionPerformed

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
        // TODO add your handling code here:
        data_barang1 db1 = new data_barang1(role);
        db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_customButton1ActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
        txt_harga.requestFocus();
    }//GEN-LAST:event_statusActionPerformed

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
            java.util.logging.Logger.getLogger(tambah_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tambah_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tambah_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tambah_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tambah_barang("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private project.CustomButtons customButton1;
    private project.CustomButtons customButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_kodebarang;
    private javax.swing.JTextField txt_namabarang1;
    private javax.swing.JTextField txt_stok;
    // End of variables declaration//GEN-END:variables
}
