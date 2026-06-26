package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import config.Koneksi;

public class DataTarif extends JFrame {
    private JTable tabel = new JTable();
    private DefaultTableModel model = new DefaultTableModel();
    private JTextField txtJenis = new JTextField(10);
    private JTextField txtHarga = new JTextField(10);
    private JButton btnTambah = new JButton("Tambah Tarif");

    public DataTarif() {
        setTitle("Data Tarif");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Header Tabel (Sesuaikan dengan kolom di database kamu)
        model.addColumn("ID Tarif");
        model.addColumn("Jenis Tarif");
        model.addColumn("Harga (Rp)");
        tabel.setModel(model);

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInput.add(new JLabel(" Jenis Tarif:")); panelInput.add(txtJenis);
        panelInput.add(new JLabel(" Harga:")); panelInput.add(txtHarga);
        panelInput.add(new JLabel("")); panelInput.add(btnTambah);

        add(new JScrollPane(tabel), BorderLayout.CENTER);
        add(panelInput, BorderLayout.SOUTH);

        loadData();
        btnTambah.addActionListener(e -> tambahTarif());
        setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            Connection conn = Koneksi.configDB();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM tarif");
            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_tarif"), 
                    rs.getString("jenis_tarif"), 
                    rs.getString("harga")
                });
            }
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage()); 
        }
    }

    private void tambahTarif() {
        try {
            Connection conn = Koneksi.configDB();
            String sql = "INSERT INTO tarif (jenis_tarif, harga) VALUES (?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtJenis.getText());
            pst.setString(2, txtHarga.getText());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Tarif berhasil ditambahkan!");
            loadData();
            txtJenis.setText(""); txtHarga.setText("");
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Gagal Tambah: " + e.getMessage()); 
        }
    }
}