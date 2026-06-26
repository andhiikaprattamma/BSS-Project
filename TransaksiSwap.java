package views;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import config.Koneksi;

public class TransaksiSwap extends JFrame {
    private JComboBox<String> comboPelanggan = new JComboBox<>();
    private JComboBox<String> comboBateraiLama = new JComboBox<>();
    private JComboBox<String> comboBateraiBaru = new JComboBox<>();
    private JButton btnSimpan = new JButton("Simpan Transaksi");

    public TransaksiSwap() {
        setTitle("Form Transaksi Swap");
        setSize(400, 350);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel(" Pelanggan:")); add(comboPelanggan);
        add(new JLabel(" Baterai Lama:")); add(comboBateraiLama);
        add(new JLabel(" Baterai Baru:")); add(comboBateraiBaru);
        add(new JLabel("")); add(btnSimpan);

        loadDataCombo();
        
        btnSimpan.addActionListener(e -> simpanTransaksi());
        setVisible(true);
    }

    private void loadDataCombo() {
        try {
            Connection conn = Koneksi.configDB();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Koneksi ke database gagal!");
                return;
            }
            
            // Clear items dulu supaya nggak double pas di-refresh
            comboPelanggan.removeAllItems();
            comboBateraiLama.removeAllItems();
            comboBateraiBaru.removeAllItems();

            Statement st = conn.createStatement();
            
            // Load Pelanggan
            ResultSet rs = st.executeQuery("SELECT id_pelanggan, nama FROM pelanggan");
            while(rs.next()) comboPelanggan.addItem(rs.getString("id_pelanggan") + " - " + rs.getString("nama"));
            
            // Load Baterai (Tersedia)
            ResultSet rsBat = st.executeQuery("SELECT id_baterai, kode_baterai FROM baterai WHERE status='Tersedia'");
            while(rsBat.next()) {
                String item = rsBat.getString("id_baterai") + " - " + rsBat.getString("kode_baterai");
                comboBateraiLama.addItem(item);
                comboBateraiBaru.addItem(item);
            }
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage()); 
        }
    }

    private void simpanTransaksi() {
        // Cek apakah combo box kosong (data belum dipilih)
        if (comboPelanggan.getSelectedItem() == null || comboBateraiLama.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Data belum lengkap!");
            return;
        }

        try {
            String p = comboPelanggan.getSelectedItem().toString().split(" - ")[0];
            String bL = comboBateraiLama.getSelectedItem().toString().split(" - ")[0];
            String bB = comboBateraiBaru.getSelectedItem().toString().split(" - ")[0];

            Connection conn = Koneksi.configDB();
            
            // 1. Simpan ke Transaksi
            String sql = "INSERT INTO transaksi_swap (id_pelanggan, baterai_lama, baterai_baru) VALUES (?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p);
            pst.setString(2, bL);
            pst.setString(3, bB);
            pst.executeUpdate();
            
            // 2. Update Status Baterai
            PreparedStatement pstLama = conn.prepareStatement("UPDATE baterai SET status='Rusak' WHERE id_baterai=?");
            pstLama.setString(1, bL);
            pstLama.executeUpdate();
            
            PreparedStatement pstBaru = conn.prepareStatement("UPDATE baterai SET status='Dipakai' WHERE id_baterai=?");
            pstBaru.setString(1, bB);
            pstBaru.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Transaksi Berhasil!");
            loadDataCombo(); // Refresh data
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); 
        }
    }
}