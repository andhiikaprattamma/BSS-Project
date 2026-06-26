package views;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private JPanel sidebar = new JPanel();
    private JButton btnPelanggan = new JButton("Data Pelanggan");
    private JButton btnMotor = new JButton("Data Motor");
    private JButton btnBaterai = new JButton("Data Baterai");
    private JButton btnTarif = new JButton("Data Tarif");
    private JButton btnTransaksi = new JButton("Transaksi Swap");
    private JButton btnHistory = new JButton("History Transaksi");

    public Dashboard() {
        setTitle("Battery Swapping Station - Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar Styling
        sidebar.setLayout(new GridLayout(10, 1, 10, 10));
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(new Color(44, 62, 80)); // Biru Gelap Profesional
        sidebar.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        sidebar.add(btnPelanggan);
        sidebar.add(btnMotor);
        sidebar.add(btnBaterai);
        sidebar.add(btnTarif);
        sidebar.add(btnTransaksi);
        sidebar.add(btnHistory);

        add(sidebar, BorderLayout.WEST);
        setupDashboard();

        // Aksi tombol
        btnPelanggan.addActionListener(e -> new DataPelanggan());
        btnMotor.addActionListener(e -> new DataMotor());
        btnBaterai.addActionListener(e -> new DataBaterai());
        btnTarif.addActionListener(e -> new DataTarif());
        btnTransaksi.addActionListener(e -> new TransaksiSwap());
        btnHistory.addActionListener(e -> new HistoryTransaksi());
        
        setVisible(true);
    }

    private void setupDashboard() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(236, 240, 245)); // Warna latar abu-kebiruan

        JLabel title = new JLabel("BSS Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(44, 62, 80));
        title.setBorder(BorderFactory.createEmptyBorder(25, 0, 20, 0));
        centerPanel.add(title, BorderLayout.NORTH);

        JPanel panelStatistik = new JPanel(new GridLayout(1, 4, 20, 20));
        panelStatistik.setBorder(BorderFactory.createEmptyBorder(20, 20, 40, 20));
        panelStatistik.setBackground(new Color(236, 240, 245));

        // Menambahkan kartu dengan warna aksen berbeda
        panelStatistik.add(createCard("Pelanggan", "150", new Color(52, 152, 219))); // Biru
        panelStatistik.add(createCard("Motor", "98", new Color(46, 204, 113)));      // Hijau
        panelStatistik.add(createCard("Baterai", "240", new Color(241, 196, 15)));   // Kuning
        panelStatistik.add(createCard("Transaksi", "23", new Color(155, 89, 182)));  // Ungu

        centerPanel.add(panelStatistik, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createCard(String title, String count, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        // Garis tebal di bawah sebagai aksen warna
        card.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 0, color));
        
        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTitle.setForeground(Color.GRAY);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        
        JLabel lblCount = new JLabel(count, SwingConstants.CENTER);
        lblCount.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblCount.setForeground(color);
        
        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblCount, BorderLayout.CENTER);
        return card;
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}