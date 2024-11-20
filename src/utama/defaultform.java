package utama;

import lala.Laporan;
import lala.Beranda;
import lala.Transaksi;
import lala.data_barang1;
import lala.data_karyawan;
import lala.edit_barang;
import lala.nambah_karyawan;
import lala.nambah_layanan;
import lala.pengeluaran;
import lala.tabel_layanan;
import lala.tabel_pengeluaran;
import lala.tambah_barang;


public class defaultform extends javax.swing.JPanel {

    int width;
    int height;
    private String[][] menuitems = new String[][]{
        {"Dashboard"},
        {"Data Barang"},
        {"Data Karyawan"},
        {"Transaksi"},
        {"Laporan", "Pemasukan", "Pengeluaran"}

    };
    
    public defaultform(int index, int subIndex) {
        initComponents();
    }
    
    public void setDimens(int width, int height) {
        this.width = width;
        this.height = height;
        System.out.println(width + " " + height);
    }
    
    public void setComp(int index, String id) {
        if(index == 10) {
            edit_barang l = new edit_barang(id);
            l.setBounds(0, 0, width, height);
            this.add(l);
        }
       
    }
    
    
    public void setComp(int index, int subIndex) {
        if(index == 0) {
            Beranda l = new Beranda();
            l.setBounds(0, 0, width, height);
            this.add(l);
        } else if(index == 1) {
            data_barang1 l = new data_barang1();
            l.setBounds(0, 0, width, height);
            this.add(l);
        }else if(index == 2) {
            data_karyawan l= new data_karyawan();
            l.setBounds(0, 0, width, height);
            this.add(l);
            }else if(index == 3) {
            Transaksi l= new Transaksi();
            l.setBounds(0, 0, width, height);
            this.add(l);
        } else if(index == 4 && subIndex == 1) {
            Laporan l = new Laporan();
            l.setBounds(0, 0, width, height);
            this.add(l);
        } else if(index == 4 && subIndex == 2) {
            tabel_pengeluaran l = new tabel_pengeluaran();
            l.setBounds(0, 0, width, height);
            this.add(l);
        }
        
        
        if(index == 10) {
            tambah_barang l = new tambah_barang();
            l.setBounds(0, 0, width, height);
            this.add(l);
        }
         if(index == 13) {
            nambah_layanan l = new nambah_layanan();
            l.setBounds(0, 0, width, height);
            this.add(l);
        }
        if(index == 12) {
            nambah_karyawan l = new nambah_karyawan();
            l.setBounds(0, 0, width, height);
            this.add(l);
        }
        if(index == 14) {
            tabel_layanan l = new tabel_layanan();
            l.setBounds(0, 0, width, height);
            this.add(l);
        }
        if(index == 15) {
            pengeluaran l = new pengeluaran();
            l.setBounds(0, 0, width, height);
            this.add(l);
        }
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(153, 51, 255));
        setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
