/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pboii;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hensu
 */
public class flaporan_stok_hendy extends javax.swing.JFrame {

    private DefaultTableModel model;
    private DefaultTableModel modela;

    /**
     * Creates new form flaporan_stok
     */
    public flaporan_stok_hendy() {
        initComponents();
        // Buat custom DefaultTableModel supaya tidak dapat edit kolom
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modela = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelBarang.setModel(model);
        tabelStatus.setModel(modela);

        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah Barang");
        model.addColumn("Harga Beli");
        model.addColumn("Harga Jual");
        model.addColumn("Tanggal Masuk");

        loadData();
        loadstok();
    }

    private final void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection connection = koneksi.getKoneksi();
            Statement statement = connection.createStatement();
            String query = "SELECT kd_barang AS 'Kode Barang', nama_barang AS 'Nama Barang', jumlah_barang AS 'Jumlah Barang', harga_beli AS 'Harga Beli', harga_jual AS 'Harga Jual', tanggal_masuk AS 'Tanggal Masuk' FROM tbl_barang";
            ResultSet resultSet = statement.executeQuery(query);

            // Menambahkan data ke model tabel
            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getString("Kode Barang"),
                    resultSet.getString("Nama Barang"),
                    resultSet.getInt("Jumlah Barang"),
                    resultSet.getInt("Harga Beli"),
                    resultSet.getInt("Harga Jual"),
                    resultSet.getDate("Tanggal Masuk")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving data from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//
//    private final void loadstok() {
//        modela.getDataVector().removeAllElements();
//        modela.fireTableDataChanged();
//
//        // Menambahkan kolom untuk status stok (masuk atau keluar)
//        modela.setColumnCount(0);
//        modela.addColumn("Tanggal");
//        modela.addColumn("Status");
//        modela.addColumn("Jumlah");
//
//        try {
//            Connection connection = koneksi.getKoneksi();
//            Statement statement = connection.createStatement();
//
//            // Query untuk mengambil data stok masuk dari tbl_barang
//            String queryStokMasuk = "SELECT tanggal_masuk AS Tanggal, nama_barang AS NamaBarang, 'Masuk' AS Status, jumlah_barang AS Jumlah FROM tbl_barang";
//            ResultSet resultSetMasuk = statement.executeQuery(queryStokMasuk);
//
//            // Menambahkan data stok masuk ke model tabel
//            while (resultSetMasuk.next()) {
//                modela.addRow(new Object[]{
//                    resultSetMasuk.getDate("Tanggal"),
//                    resultSetMasuk.getString("NamaBarang"),
//                    resultSetMasuk.getString("Status"),
//                    resultSetMasuk.getInt("Jumlah")
//                });
//            }
//
//            // Query untuk mengambil data stok keluar dari tbl_penjualan
//            String queryStokKeluar = "SELECT tanggal AS Tanggal, nama_barang AS NamaBarang, 'Keluar' AS Status, jumlah_jual AS Jumlah FROM tbl_penjualan";
//            ResultSet resultSetKeluar = statement.executeQuery(queryStokKeluar);
//
//            // Menambahkan data stok keluar ke model tabel
//            while (resultSetKeluar.next()) {
//                modela.addRow(new Object[]{
//                    resultSetKeluar.getDate("Tanggal"),
//                    resultSetKeluar.getString("NamaBarang"),
//                    resultSetKeluar.getString("Status"),
//                    resultSetKeluar.getInt("Jumlah")
//                });
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error retrieving data from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private final void loadData() {
//        model.getDataVector().removeAllElements();
//        model.fireTableDataChanged();
//        try {
//            Connection connection = koneksi.getKoneksi();
//            Statement statement = connection.createStatement();
//            String query = "SELECT kd_barang AS 'Kode Barang', nama_barang AS 'Nama Barang', jumlah_barang AS 'Jumlah Barang', harga_beli AS 'Harga Beli', harga_jual AS 'Harga Jual', tanggal_masuk AS 'Tanggal Masuk' FROM tbl_barang";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            // Menambahkan data ke model tabel
//            while (resultSet.next()) {
//                model.addRow(new Object[]{
//                    resultSet.getString("Kode Barang"),
//                    resultSet.getString("Nama Barang"),
//                    resultSet.getInt("Jumlah Barang"),
//                    resultSet.getInt("Harga Beli"),
//                    resultSet.getInt("Harga Jual"),
//                    resultSet.getDate("Tanggal Masuk")
//                });
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error retrieving data from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    private final void loadstok() {
        modela.getDataVector().removeAllElements();
        modela.fireTableDataChanged();

        // Menambahkan kolom untuk status stok (masuk atau keluar)
        modela.setColumnCount(0);
        modela.addColumn("Tanggal");
        modela.addColumn("Nama Barang");
        modela.addColumn("Status");
        modela.addColumn("Jumlah");

        try {
            Connection connection = koneksi.getKoneksi();
            Statement statement = connection.createStatement();

            // Query untuk mengambil data stok masuk dari tbl_barang
            String queryStokMasuk = "SELECT tanggal_masuk AS Tanggal, nama_barang AS NamaBarang, 'Masuk' AS Status, jumlah_barang AS Jumlah FROM tbl_barang";
            ResultSet resultSetMasuk = statement.executeQuery(queryStokMasuk);

            // Menambahkan data stok masuk ke model tabel
            while (resultSetMasuk.next()) {
                modela.addRow(new Object[]{
                    resultSetMasuk.getDate("Tanggal"),
                    resultSetMasuk.getString("NamaBarang"),
                    resultSetMasuk.getString("Status"),
                    resultSetMasuk.getInt("Jumlah")
                });
            }

            // Query untuk mengambil data stok keluar dari tbl_penjualan
            String queryStokKeluar = "SELECT tanggal AS Tanggal, nama_barang AS NamaBarang, 'Keluar' AS Status, jumlah_jual AS Jumlah FROM tbl_penjualan";
            ResultSet resultSetKeluar = statement.executeQuery(queryStokKeluar);

            // Menambahkan data stok keluar ke model tabel
            while (resultSetKeluar.next()) {
                modela.addRow(new Object[]{
                    resultSetKeluar.getDate("Tanggal"),
                    resultSetKeluar.getString("NamaBarang"),
                    resultSetKeluar.getString("Status"),
                    resultSetKeluar.getInt("Jumlah")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving data from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
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
        btn_back = new javax.swing.JButton();
        lbl_title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelStatus = new javax.swing.JTable();
        lbl_status = new javax.swing.JLabel();
        lbl_stok = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Laporan Stok Barang");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_back.setBackground(new java.awt.Color(255, 255, 255));
        btn_back.setForeground(new java.awt.Color(0, 0, 0));
        btn_back.setText("back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel1.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, -1, -1));

        lbl_title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(255, 255, 255));
        lbl_title.setText("Laporan Stok Barang");
        jPanel1.add(lbl_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelStatus.setAutoCreateRowSorter(true);
        tabelStatus.setBackground(new java.awt.Color(255, 255, 204));
        tabelStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabelStatus);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 520, 180));

        lbl_status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_status.setForeground(new java.awt.Color(255, 255, 255));
        lbl_status.setText("Tabel Status");
        jPanel2.add(lbl_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, -1, -1));

        lbl_stok.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_stok.setForeground(new java.awt.Color(255, 255, 255));
        lbl_stok.setText("Tabel Stok");
        jPanel2.add(lbl_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        tabelBarang.setAutoCreateRowSorter(true);
        tabelBarang.setBackground(new java.awt.Color(255, 255, 204));
        tabelBarang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tabelBarang);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 520, 180));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 770, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        fmenu_hendy fb = new fmenu_hendy();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_backActionPerformed

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
            java.util.logging.Logger.getLogger(flaporan_stok_hendy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(flaporan_stok_hendy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(flaporan_stok_hendy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(flaporan_stok_hendy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new flaporan_stok_hendy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel lbl_stok;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTable tabelStatus;
    // End of variables declaration//GEN-END:variables
}
