/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lala;

import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import log.login;
import project.koneksi;
import utama.defaultform;

/**
 *
 * @author ASUS
 */
public class pengeluaran extends javax.swing.JPanel {
    koneksi db = new koneksi();
    String role;

    /**
     * Creates new form pengeluaran
     */
    public pengeluaran() {
        initComponents();
        kode_pengeluaran();
        tanggal_pengeluaran.setText(LocalDate.now().toString());
    }

     public void kode_pengeluaran() {
        try {
            ResultSet rs = db.ambilData("SELECT kode_pengeluaran FROM pengeluaran ORDER BY kode_pengeluaran DESC");
            if (rs.next()) {
                String TR = rs.getString("kode_pengeluaran");
                int angka = Integer.parseInt(TR.split("P")[1]);
                angka++;
                if (angka <= 9) {
                    kode_pengeluaran.setText("KP0" + angka);
                } else if (angka <= 99) {
                    kode_pengeluaran.setText("KP" + angka);
                }
            }

        } catch (Exception e) {
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

        pengeluaran = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        kode_pengeluaran = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jd_kodepengeluaran = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jd_jenispengeluaran = new javax.swing.JLabel();
        jenis_pengeluaran = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jd_totalpengeluaran = new javax.swing.JLabel();
        total_pengeluaran = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jd_keterangan = new javax.swing.JLabel();
        tanggal_pengeluaran = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        customButton4 = new project.CustomButtons();
        customButton5 = new project.CustomButtons();
        jScrollPane1 = new javax.swing.JScrollPane();
        keterangan = new javax.swing.JTextArea();

        pengeluaran.setBackground(new java.awt.Color(255, 255, 255));
        pengeluaran.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1190, 640));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kode_pengeluaran.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        kode_pengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_pengeluaranActionPerformed(evt);
            }
        });
        jPanel2.add(kode_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 200, 40));

        jPanel9.setBackground(new java.awt.Color(25, 192, 236));

        jd_kodepengeluaran.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jd_kodepengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        jd_kodepengeluaran.setText("Kode Pengeluaran");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jd_kodepengeluaran)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jd_kodepengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 200, 30));

        jPanel11.setBackground(new java.awt.Color(25, 192, 236));

        jd_jenispengeluaran.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jd_jenispengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        jd_jenispengeluaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jd_jenispengeluaran.setText("Jenis Pengeluaran");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jd_jenispengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jd_jenispengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 230, 30));

        jenis_pengeluaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silahkan Pilih", "Listrik", "Air", "Bahan", "Gaji" }));
        jenis_pengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_pengeluaranActionPerformed(evt);
            }
        });
        jPanel2.add(jenis_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 230, 30));

        jPanel12.setBackground(new java.awt.Color(25, 192, 236));

        jd_totalpengeluaran.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jd_totalpengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        jd_totalpengeluaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jd_totalpengeluaran.setText("Total Pengeluaran");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jd_totalpengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jd_totalpengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 230, 30));

        total_pengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_pengeluaranActionPerformed(evt);
            }
        });
        jPanel2.add(total_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 230, 40));

        jPanel13.setBackground(new java.awt.Color(25, 192, 236));

        jd_keterangan.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jd_keterangan.setForeground(new java.awt.Color(255, 255, 255));
        jd_keterangan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jd_keterangan.setText("Keterangan");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jd_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jd_keterangan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 800, 30));

        tanggal_pengeluaran.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tanggal_pengeluaran.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tanggal_pengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggal_pengeluaranActionPerformed(evt);
            }
        });
        jPanel2.add(tanggal_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 220, 40));

        jPanel10.setBackground(new java.awt.Color(25, 192, 236));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tanggal Pengeluaran");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 220, 30));

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
        jPanel2.add(customButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 550, 160, 40));

        customButton5.setText("customButton2");
        customButton5.setBackgroundColor(new java.awt.Color(25, 192, 236));
        customButton5.setBorderRadius(25);
        customButton5.setTextBold(0);
        customButton5.setTextColor(java.awt.Color.white);
        customButton5.setTheText("Batal");
        customButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(customButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 550, 160, 40));

        keterangan.setColumns(20);
        keterangan.setRows(5);
        jScrollPane1.setViewportView(keterangan);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 800, 180));

        pengeluaran.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pengeluaran, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void kode_pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_pengeluaranActionPerformed
        // TODO add your handling code here:
        ResultSet rs = db.ambilData("SELECT * FROM pengeluaran WHERE kode_pengeluaran = '" + kode_pengeluaran.getText() + "'");
        try {

        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_kode_pengeluaranActionPerformed

    private void jenis_pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_pengeluaranActionPerformed
        // TODO add your handling code here:
        jenis_pengeluaran.requestFocus();
    }//GEN-LAST:event_jenis_pengeluaranActionPerformed

    private void total_pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_pengeluaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_pengeluaranActionPerformed

    private void tanggal_pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggal_pengeluaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggal_pengeluaranActionPerformed

    private void customButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton4ActionPerformed
        // TODO add your handling code here:
        db.aksi("INSERT INTO pengeluaran (kode_pengeluaran, tanggal, jenis_pengeluaran, total_pengeluaran, keterangan) VALUES ('" + kode_pengeluaran.getText() + "','"+ tanggal_pengeluaran.getText() +"', '" + jenis_pengeluaran.getSelectedItem()+ "', '" + total_pengeluaran.getText() + "', '" + keterangan.getText() + "')");
        JOptionPane.showMessageDialog(null, "Tambah Berhasil", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
        //tabel_pengeluaran db1 = new tabel_pengeluaran(role);
        //db1.setVisible(true);
        //this.setVisible(false);
        defaultform f = new defaultform(4, 2);
        f.setDimens(1086, 595);
        f.setComp(4, 2);
        login.showForm(f);
    }//GEN-LAST:event_customButton4ActionPerformed

    private void customButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton5ActionPerformed
        // TODO add your handling code here:
        //tabel_pengeluaran db1 = new tabel_pengeluaran(role);
        //db1.setVisible(true);
        //this.setVisible(false);
        defaultform f = new defaultform(4, 2);
        f.setDimens(1086, 595);
        f.setComp(4, 2);
        login.showForm(f);
    }//GEN-LAST:event_customButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private project.CustomButtons customButton4;
    private project.CustomButtons customButton5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jd_jenispengeluaran;
    private javax.swing.JLabel jd_keterangan;
    private javax.swing.JLabel jd_kodepengeluaran;
    private javax.swing.JLabel jd_totalpengeluaran;
    private javax.swing.JComboBox<String> jenis_pengeluaran;
    private javax.swing.JTextArea keterangan;
    private javax.swing.JTextField kode_pengeluaran;
    private javax.swing.JPanel pengeluaran;
    private javax.swing.JTextField tanggal_pengeluaran;
    private javax.swing.JTextField total_pengeluaran;
    // End of variables declaration//GEN-END:variables
}
