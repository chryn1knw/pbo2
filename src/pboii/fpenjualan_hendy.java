package pboii;

import java.awt.Desktop;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.net.URL;

/**
 *
 * @author hensu
 */
public class fpenjualan_hendy extends javax.swing.JFrame {

    public long total;
    public long bayar;
    public long kembali;
    public Statement st;
    Connection con = koneksi.getKoneksi();
    private DefaultTableModel model;

    /**
     * Creates new form fpenjualan
     */
    public fpenjualan_hendy() {
        initComponents();
//        model = new DefaultTableModel();
        // Buat custom DefaultTableModel supaya tidak dapat edit kolom
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabel.setModel(model);
        model.addColumn("ID");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga Satuan");
        model.addColumn("Jumlah Beli");
        model.addColumn("Harga");
        loadData();
        nofaktur();
        tampilpilih();
    }

    public void FilterHuruf(KeyEvent a) {
        if (Character.isDigit(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "masukan huruf saja!", "peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void FilterAngka(KeyEvent a) {
        if (Character.isAlphabetic(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "masukan angka saja!", "peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public final void loadData() {
        btn_transaksi.setEnabled(false);
        btn_cetak.setEnabled(false);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tbl_hitung_jual";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[6];
                o[0] = r.getString("id_hitung");
                o[1] = r.getString("kd_barang");
                o[2] = r.getString("nama_barang");
                o[3] = r.getString("hsatuan");
                o[4] = r.getString("jumlah_jual");
                o[5] = r.getString("harga");

                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error loadData()");
        }
    }

    private void tampilpilih() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT nama_barang FROM tbl_barang WHERE jumlah_barang !='0'";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                pilihbarang.addItem(r.getString("nama_barang"));
            }
//            r.last();
//            int jumlahdata = r.getRow();
//            r.first();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void nofaktur() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tbl_penjualan ORDER by nofaktur desc";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String nofak = r.getString("nofaktur").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }
                faktur.setText("F" + Nol + AN);
            } else {
                faktur.setText("F0001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        kdbarang = new javax.swing.JTextField();
        hsatuan = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        faktur = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pilihbarang = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jumlah = new javax.swing.JTextField();
        text2 = new javax.swing.JTextField();
        ttotal = new javax.swing.JTextField();
        btn_hitung = new javax.swing.JButton();
        tjumlah = new javax.swing.JTextField();
        btn_cetak = new javax.swing.JButton();
        btn_transaksi = new javax.swing.JButton();
        txt_kembalian = new javax.swing.JTextField();
        txt_bayar = new javax.swing.JTextField();
        lbl_bayar = new javax.swing.JLabel();
        lbl_kembalian = new javax.swing.JLabel();
        btn_total = new javax.swing.JButton();
        adfsa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Penjualan");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Jumlah Jual");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("No.Faktur");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nama Barang");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Harga Satuan");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));
        getContentPane().add(kdbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 130, 30));
        getContentPane().add(hsatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 130, 30));

        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 350, 30));

        faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fakturActionPerformed(evt);
            }
        });
        getContentPane().add(faktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 130, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Kode Barang");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        pilihbarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih barang" }));
        pilihbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihbarangActionPerformed(evt);
            }
        });
        getContentPane().add(pilihbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 130, 30));

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));
        jPanel1.setForeground(new java.awt.Color(255, 204, 51));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(769, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton2)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 60));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 100, 40));

        text2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text2ActionPerformed(evt);
            }
        });
        jPanel3.add(text2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 100, 40));

        ttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttotalActionPerformed(evt);
            }
        });
        jPanel3.add(ttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 130, 30));

        btn_hitung.setText("Hitung");
        btn_hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hitungActionPerformed(evt);
            }
        });
        jPanel3.add(btn_hitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 80, 30));

        tjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tjumlahActionPerformed(evt);
            }
        });
        tjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjumlahKeyTyped(evt);
            }
        });
        jPanel3.add(tjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 130, 30));

        btn_cetak.setText("Cetak");
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
            }
        });
        jPanel3.add(btn_cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 200, -1));

        btn_transaksi.setText("Selesai Transaksi");
        btn_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transaksiActionPerformed(evt);
            }
        });
        jPanel3.add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, -1, -1));
        jPanel3.add(txt_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, 100, 30));

        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bayarKeyTyped(evt);
            }
        });
        jPanel3.add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 100, 30));

        lbl_bayar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bayar.setText("Bayar");
        jPanel3.add(lbl_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, -1, -1));

        lbl_kembalian.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_kembalian.setText("Kembalian");
        jPanel3.add(lbl_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, -1, -1));

        btn_total.setText("Total");
        btn_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_totalActionPerformed(evt);
            }
        });
        jPanel3.add(btn_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, -1, -1));

        adfsa.setEditable(false);
        adfsa.setBackground(new java.awt.Color(204, 204, 204));
        adfsa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        adfsa.setForeground(new java.awt.Color(51, 51, 51));
        adfsa.setText("Rp.");
        adfsa.setBorder(null);
        jPanel3.add(adfsa, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 30, 30));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, -1));

        tabel.setBackground(new java.awt.Color(255, 255, 204));
        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 620, 130));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 870, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fakturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fakturActionPerformed

    private void pilihbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihbarangActionPerformed
        // TODO add your handling code here:
        if (pilihbarang.getSelectedItem().equals("pilih barang")) {
            kdbarang.setText("");
            hsatuan.setText("");
        } else {
            try {
                Connection c = koneksi.getKoneksi();
                Statement s = c.createStatement();
                String sql = "SELECT kd_barang, jumlah_barang FROM tbl_barang WHERE nama_barang ='"
                        + pilihbarang.getSelectedItem() + "'";
                ResultSet r = s.executeQuery(sql);
                while (r.next()) {
                    kdbarang.setText(r.getString("kd_barang"));
                    jumlah.setText(r.getString("jumlah_barang"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            try {
                Connection c = koneksi.getKoneksi();
                Statement s = c.createStatement();
                String sql = "SELECT harga_jual FROM tbl_barang WHERE nama_barang ='"
                        + pilihbarang.getSelectedItem() + "'";
                ResultSet r = s.executeQuery(sql);
                while (r.next()) {
                    hsatuan.setText(r.getString("harga_jual"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_pilihbarangActionPerformed

    private void btn_hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hitungActionPerformed
        // TODO add your handling code here:
        if (faktur.getText().equals("") || kdbarang.getText().equals("")
                || pilihbarang.getSelectedItem().equals("") || hsatuan.getText().equals("") || tjumlah.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Aplikasi Penjualan",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {
            String a = tjumlah.getText();
            int aa = Integer.parseInt(a);

            String b = jumlah.getText();
            int bb = Integer.parseInt(b);
            if (aa > bb) {
                JOptionPane.showMessageDialog(null, "jumlah melebihi stok", "Aplikasi Penjualan",
                        JOptionPane.INFORMATION_MESSAGE);
                tjumlah.setText("");
            } else {

                if (tjumlah.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "ISI JUMLAH BELI !");
                } else {
                    int jumlah, harga, total;

                    jumlah = Integer.parseInt(tjumlah.getText().toString());
                    harga = Integer.parseInt(hsatuan.getText().toString());
                    total = jumlah * harga;

                    ttotal.setText(Integer.toString(total));

                }
            }
        }
    }//GEN-LAST:event_btn_hitungActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        if (faktur.getText().equals("") || kdbarang.getText().equals("")
                || pilihbarang.getSelectedItem().equals("") || hsatuan.getText().equals("") || tjumlah.getText().equals("")
                || ttotal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Aplikasi Penjualan",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {
            String kdbarangg = kdbarang.getText();
            String pilihbarangg = (String) pilihbarang.getSelectedItem();
            String hsatuann = hsatuan.getText();
            String tjumlahh = tjumlah.getText();
            String totall = ttotal.getText();

            try {
                Connection c = koneksi.getKoneksi();

                String sql = "INSERT INTO tbl_hitung_jual VALUES (?, ?, ?, ?, ?, ?)";

                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, null);
                p.setString(2, kdbarangg);
                p.setString(3, pilihbarangg);
                p.setString(4, hsatuann);
                p.setString(5, tjumlahh);
                p.setString(6, totall);

                p.executeUpdate();
                p.close();
            } catch (SQLException e) {
                System.out.println("Terjadi Error btn_tambah");
            } finally {
                nofaktur();
                kdbarang.setText("");
                pilihbarang.setSelectedItem("");
                hsatuan.setText("");
                tjumlah.setText("");
                ttotal.setText("");
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Aplikasi Penjualan",
                        JOptionPane.INFORMATION_MESSAGE);
                loadData();
            }
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        int jawaban;
        if ((jawaban = JOptionPane.showConfirmDialog(null, "Yakin batal?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION)) == 0) {
            try {
                int i = tabel.getSelectedRow();
                if (i == -1) {
                    return;
                }
                String id = (String) model.getValueAt(i, 0);
                st = con.createStatement();
                st.executeUpdate("delete from tbl_hitung_jual where id_hitung = '" + id + "'");
                nofaktur();
                loadData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tabelMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        fmenu_hendy fb = new fmenu_hendy();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_totalActionPerformed
        // TODO add your handling code here:
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT SUM(`harga`) AS total FROM tbl_hitung_jual";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                jLabel10.setText(r.getString("" + "total"));

            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error btn_total");
        }
    }//GEN-LAST:event_btn_totalActionPerformed

    private void btn_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transaksiActionPerformed
        // TODO add your handling code here:
        if (txt_bayar.getText().equals("") || txt_kembalian.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Aplikasi Penjualan",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {
            String a = txt_kembalian.getText();
            int ab = Integer.parseInt(String.valueOf(txt_kembalian.getText()));
            if (ab < 0) {
                JOptionPane.showMessageDialog(null, "Uang anda kurang", "Aplikasi Penjualan",
                        JOptionPane.INFORMATION_MESSAGE);
                txt_bayar.setText("");
                txt_kembalian.setText("");
            } else {
                try {
                    Connection c = koneksi.getKoneksi();
                    Statement s = c.createStatement();
                    String sql = "SELECT * FROM tbl_hitung_jual";
                    ResultSet r = s.executeQuery(sql);
                    while (r.next()) {
                        long millis = System.currentTimeMillis();
                        java.sql.Date date = new java.sql.Date(millis);
                        System.out.println(date);
                        String tgl = date.toString();
                        String sqla = "INSERT INTO tbl_penjualan VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement p = c.prepareStatement(sqla);
                        p.setString(1, faktur.getText());
                        p.setString(2, r.getString("kd_barang"));
                        p.setString(3, r.getString("nama_barang"));
                        p.setString(4, r.getString("hsatuan"));
                        p.setString(5, r.getString("jumlah_jual"));
                        p.setString(6, r.getString("harga"));
                        p.setString(7, txt_bayar.getText());
                        p.setString(8, txt_kembalian.getText());
                        p.setString(9, tgl);

                        p.executeUpdate();
                        p.close();

                    }
                    r.close();
                    s.close();
                } catch (SQLException e) {
                    System.out.println("Terjadi Error transaksi");
                } finally {
                    try {
                        String sqla = "TRUNCATE tbl_hitung_jual";
                        java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sqla);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "TRANSAKSI SELESAI", "Aplikasi Penjualan",
                                JOptionPane.INFORMATION_MESSAGE);
//                        loadData();
                        text2.setText(faktur.getText());
                        txt_bayar.setText("");
                        txt_kembalian.setText("");
                        jLabel10.setText("");
                        nofaktur();
                        btn_cetak.setEnabled(true);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_transaksiActionPerformed

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
//         TODO add your handling code here:
                try {
                    Desktop.getDesktop().browse(new URL("http://localhost/PenjualanBarang/invoice.php?lap&fk=" + faktur.getText() + "").toURI());
                } catch (Exception e) {
                    System.out.println("Failed to open browser: " + e);
                    
                    System.out.println("Opening URL in console: http://localhost/PenjualanBarang/invoice.php?lap&fk=" + faktur.getText());
                }
    }//GEN-LAST:event_btn_cetakActionPerformed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
        bayar = Integer.parseInt(String.valueOf(txt_bayar.getText()));
        total = Integer.parseInt(String.valueOf(jLabel10.getText()));
        kembali = bayar - total;

        txt_kembalian.setText(Long.toString(kembali));
        btn_transaksi.setEnabled(true);
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void txt_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyTyped
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_txt_bayarKeyTyped

    private void tjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyTyped
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_tjumlahKeyTyped

    private void ttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttotalActionPerformed

    private void text2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text2ActionPerformed

    private void tjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tjumlahActionPerformed

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
            java.util.logging.Logger.getLogger(fpenjualan_hendy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fpenjualan_hendy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fpenjualan_hendy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fpenjualan_hendy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new fpenjualan_hendy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adfsa;
    private javax.swing.JButton btn_cetak;
    private javax.swing.JButton btn_hitung;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_total;
    private javax.swing.JButton btn_transaksi;
    private javax.swing.JTextField faktur;
    private javax.swing.JTextField hsatuan;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField kdbarang;
    private javax.swing.JLabel lbl_bayar;
    private javax.swing.JLabel lbl_kembalian;
    private javax.swing.JComboBox<String> pilihbarang;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField text2;
    private javax.swing.JTextField tjumlah;
    private javax.swing.JTextField ttotal;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_kembalian;
    // End of variables declaration//GEN-END:variables
}
