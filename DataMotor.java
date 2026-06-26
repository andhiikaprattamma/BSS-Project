package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import config.Koneksi;

public class DataMotor extends JFrame {
    private JTable tabel = new JTable();
    private DefaultTableModel model = new DefaultTableModel();
    private JTextField txtPlat = new JTextField(10);
    private JTextField txtMerk = new JTextField(10);
    private JButton btnTambah = new JButton("Tambah Data");

    public DataMotor() {
        setTitle("Data Motor");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Header Tabel
        model.addColumn("ID Motor");
        model.addColumn("Plat Nomor");
        model.addColumn("Merk/Tipe");
        tabel.setModel(model);

        // Input Panel
        JPanel panelInput = new JPanel();
        panelInput.add(new JLabel("Plat:")); panelInput.add(txtPlat);
        panelInput.add(new JLabel("Merk:")); panelInput.add(txtMerk);
        panelInput.add(btnTambah);

        add(new JScrollPane(tabel), BorderLayout.CENTER);
        add(panelInput, BorderLayout.SOUTH);

        loadData();
        btnTambah.addActionListener(e -> tambahMotor());
        setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            Connection conn = Koneksi.configDB();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM motor");
            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_motor"), 
                    rs.getString("plat_nomor"), 
                    rs.getString("merk_tipe")
                });
            }
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
    }

    private void tambahMotor() {
        try {
            Connection conn = Koneksi.configDB();
            String sql = "INSERT INTO motor (plat_nomor, merk_tipe) VALUES (?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtPlat.getText());
            pst.setString(2, txtMerk.getText());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Motor berhasil ditambahkan!");
            loadData(); // Refresh tabel
            txtPlat.setText(""); txtMerk.setText("");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
    }
}