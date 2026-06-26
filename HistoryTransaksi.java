package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import config.Koneksi;

public class HistoryTransaksi extends JFrame {
    private JTable tabelHistory = new JTable();
    private DefaultTableModel model = new DefaultTableModel();

    public HistoryTransaksi() {
        setTitle("History Transaksi Swap");
        setSize(700, 400);
        setLocationRelativeTo(null);

        // Header Tabel
        model.addColumn("ID Swap");
        model.addColumn("Pelanggan");
        model.addColumn("Baterai Lama");
        model.addColumn("Baterai Baru");
        model.addColumn("Tanggal");
        tabelHistory.setModel(model);

        add(new JScrollPane(tabelHistory));
        loadData();
        setVisible(true);
    }

    private void loadData() {
        try {
            Connection conn = Koneksi.configDB();
            String sql = "SELECT ts.id_swap, p.nama, b1.kode_baterai AS lama, b2.kode_baterai AS baru, ts.tanggal " +
                         "FROM transaksi_swap ts " +
                         "JOIN pelanggan p ON ts.id_pelanggan = p.id_pelanggan " +
                         "JOIN baterai b1 ON ts.baterai_lama = b1.id_baterai " +
                         "JOIN baterai b2 ON ts.baterai_baru = b2.id_baterai";
            
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_swap"),
                    rs.getString("nama"),
                    rs.getString("lama"),
                    rs.getString("baru"),
                    rs.getString("tanggal")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Load History: " + e.getMessage());
        }
    }
}