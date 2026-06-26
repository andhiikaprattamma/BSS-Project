package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import config.Koneksi;

public class DataPelanggan extends JFrame {
    private JTable tabel = new JTable();
    private DefaultTableModel model = new DefaultTableModel();
    private JButton btnTambah = new JButton("Tambah");
    private JButton btnHapus = new JButton("Hapus");

    public DataPelanggan() {
        setTitle("Data Pelanggan");
        setSize(600, 400);
        setLayout(new BorderLayout());
        
        model.addColumn("ID");
        model.addColumn("NIK");
        model.addColumn("Nama");
        tabel.setModel(model);
        
        add(new JScrollPane(tabel), BorderLayout.CENTER);
        JPanel panelBtn = new JPanel();
        panelBtn.add(btnTambah);
        panelBtn.add(btnHapus);
        add(panelBtn, BorderLayout.SOUTH);
        
        loadData();
        btnTambah.addActionListener(e -> tambahData());
        setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0); // Bersihkan tabel
        try {
            ResultSet rs = Koneksi.configDB().createStatement().executeQuery("SELECT * FROM pelanggan");
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void tambahData() {
        // Contoh input sederhana pakai JOptionPane
        String nama = JOptionPane.showInputDialog("Masukkan Nama:");
        if(nama != null) {
            try {
                PreparedStatement pst = Koneksi.configDB().prepareStatement("INSERT INTO pelanggan (nama) VALUES (?)");
                pst.setString(1, nama);
                pst.executeUpdate();
                loadData(); // Refresh table
            } catch (Exception e) { JOptionPane.showMessageDialog(this, "Gagal!"); }
        }
    }
}