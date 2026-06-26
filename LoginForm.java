package views;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.Koneksi;

public class LoginForm extends JFrame {
    private JTextField txtUser = new JTextField();
    private JPasswordField txtPass = new JPasswordField();
    private JButton btnLogin = new JButton("LOGIN");

    public LoginForm() {
        setTitle("BSS Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10)); // Grid 3 baris, 2 kolom
        
        add(new JLabel(" Username:")); add(txtUser);
        add(new JLabel(" Password:")); add(txtPass);
        add(new JLabel("")); // Spacer
        add(btnLogin);

        btnLogin.addActionListener(e -> prosesLogin());
        setVisible(true);
    }

    private void prosesLogin() {
        try {
            Connection conn = Koneksi.configDB();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Koneksi database null! Cek config.");
                return;
            }

            String sql = "SELECT * FROM petugas WHERE username=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtUser.getText().trim());
            pst.setString(2, new String(txtPass.getPassword()).trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Berhasil!");
                new Dashboard(); // Panggil Dashboard
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password Salah!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}