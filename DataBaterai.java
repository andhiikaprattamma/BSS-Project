package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import config.Koneksi;

public class DataBaterai extends JFrame {
    private JTable tabel = new JTable();
    private DefaultTableModel model = new DefaultTableModel();

    public DataBaterai() {
        setTitle("Data Baterai");
        setSize(800, 400); // Lebarin dikit biar kolomnya muat
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header sesuai tabel database
        model.addColumn("ID");
        model.addColumn("Kode");
        model.addColumn("Kapasitas");
        model.addColumn("Persentase");
        model.addColumn("Status");
        model.addColumn("Lokasi");
        tabel.setModel(model);

        add(new JScrollPane(tabel), BorderLayout.CENTER);
        
        loadData();
        setVisible(true);
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            Connection conn = Koneksi.configDB();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM baterai");
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_baterai"),
                    rs.getString("kode_baterai"),
                    rs.getString("kapasitas"),
                    rs.getString("persentase"),
                    rs.getString("status"),
                    rs.getString("lokasi")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}