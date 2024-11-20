/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lala;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import log.login;
import project.koneksi;
import utama.defaultform;

/**
 *
 * @author ASUS
 */
public class tabel_layanan extends javax.swing.JPanel {
    koneksi db = new koneksi();
    DefaultTableModel table = new DefaultTableModel();
    String role;

    /**
     * Creates new form tabel_layanan
     */
    public tabel_layanan() {
        initComponents();
        this.role = role;
        db.koneksi();
       
        setColumn();
         setRows();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void setColumn() {
        table.addColumn("id layanan");
        table.addColumn("Jenis Layanan");
        table.addColumn("Nama Layanan");
        table.addColumn("Harga Layanan");
        table.addColumn("Tanggal Layanan");
        tabel_layanan1.setModel(table);
    }
    
    public void setRows() {
        table.setRowCount(0);
//        tabel_databarang1.setModel(table);
        ResultSet rs = db.ambilData("SELECT * FROM data_layanan");
        try {
            while (rs.next()) {
                table.addRow(new Object[]{rs.getString("id_layanan"),rs.getString("jenis_layanan"), rs.getString("nama_layanan"), rs.getString("harga_layanan"), rs.getString("tgl_layanan")});
            }
            tabel_layanan1.setModel(table);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabel_layanan = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_layanan1 = new project.CustomTable();
        tambah_karyawan = new project.CustomButtons();
        kembali = new project.CustomButtons();

        tabel_layanan.setBackground(new java.awt.Color(255, 255, 255));
        tabel_layanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_layanan1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabel_layanan1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1010, 550));

        tambah_karyawan.setForeground(new java.awt.Color(255, 255, 255));
        tambah_karyawan.setText("Tambah");
        tambah_karyawan.setBackgroundColor(new java.awt.Color(255, 0, 51));
        tambah_karyawan.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tambah_karyawan.setName("Edit"); // NOI18N
        tambah_karyawan.setTextColor(java.awt.Color.white);
        tambah_karyawan.setTheText("Hapus");
        tambah_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_karyawanActionPerformed(evt);
            }
        });
        jPanel2.add(tambah_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, 110, 31));

        kembali.setForeground(new java.awt.Color(255, 255, 255));
        kembali.setBackgroundColor(new java.awt.Color(25, 192, 236));
        kembali.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        kembali.setLabel("Kembali");
        kembali.setName("Data jenis Laundry\n"); // NOI18N
        kembali.setTextColor(java.awt.Color.white);
        kembali.setTheText("Kembali");
        kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kembaliMouseClicked(evt);
            }
        });
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });
        jPanel2.add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 110, 31));

        tabel_layanan.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabel_layanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabel_layanan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        defaultform f = new defaultform(3, 0);
        f.setDimens(1086, 595);
        f.setComp(3, 0);
        login.showForm(f);
    }//GEN-LAST:event_kembaliActionPerformed

    private void kembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kembaliMouseClicked
        // TODO add your handling code here:
        //Transaksi db1 = new Transaksi(role);
        //db1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_kembaliMouseClicked

    private void tambah_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_karyawanActionPerformed
        // TODO add your handling code here:
        int row = tabel_layanan1.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda ingin hapus data ini?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                db.aksi("DELETE FROM data_layanan WHERE id_layanan = '" + tabel_layanan1.getValueAt(row, 0) + "'");
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                setRows();
            }
        }

    }//GEN-LAST:event_tambah_karyawanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private project.CustomButtons kembali;
    private javax.swing.JPanel tabel_layanan;
    private project.CustomTable tabel_layanan1;
    private project.CustomButtons tambah_karyawan;
    // End of variables declaration//GEN-END:variables
}
