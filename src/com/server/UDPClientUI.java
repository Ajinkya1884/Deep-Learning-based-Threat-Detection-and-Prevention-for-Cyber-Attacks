package com.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientUI extends JFrame {

    private JTextField txtLocalIP;
    private JTextField txtServerIP;
    private JTextArea txtMessage;
    private JTextArea txtStatus;

    public UDPClientUI() {
        setTitle("Cyber Packet Sender");
        setSize(650, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🌈 MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(20, 25, 40));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);

        // 🔹 TOP PANEL
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        topPanel.setBackground(new Color(30, 35, 55));

        JLabel lblLocal = new JLabel("Local IP:");
        JLabel lblServer = new JLabel("Server IP:");

        lblLocal.setFont(titleFont);
        lblServer.setFont(titleFont);
        lblLocal.setForeground(Color.WHITE);
        lblServer.setForeground(Color.WHITE);

        txtLocalIP = new JTextField();
        txtLocalIP.setEditable(false);
        txtLocalIP.setFont(font);
        txtLocalIP.setBackground(new Color(10, 15, 30));
        txtLocalIP.setForeground(new Color(0, 255, 150));

        txtServerIP = new JTextField();
        txtServerIP.setFont(font);
        txtServerIP.setBackground(new Color(10, 15, 30));
        txtServerIP.setForeground(Color.WHITE);

        topPanel.add(lblLocal);
        topPanel.add(txtLocalIP);
        topPanel.add(lblServer);
        topPanel.add(txtServerIP);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // 🔹 CENTER PANEL (MESSAGE)
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(new Color(20, 25, 40));

        JLabel lblMsg = new JLabel("Packet Data:");
        lblMsg.setFont(titleFont);
        lblMsg.setForeground(Color.WHITE);

        txtMessage = new JTextArea(6, 30);
        txtMessage.setFont(font);
        txtMessage.setBackground(new Color(10, 15, 30));
        txtMessage.setForeground(new Color(0, 200, 255));
        txtMessage.setCaretColor(Color.WHITE);

        JScrollPane msgScroll = new JScrollPane(txtMessage);
        msgScroll.setBorder(BorderFactory.createLineBorder(new Color(60, 80, 120)));

        centerPanel.add(lblMsg, BorderLayout.NORTH);
        centerPanel.add(msgScroll, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 🔹 BOTTOM PANEL
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBackground(new Color(20, 25, 40));

        JButton btnSend = new JButton("Send Packet");
        btnSend.setFont(titleFont);
        btnSend.setBackground(new Color(0, 150, 136));
        btnSend.setForeground(Color.WHITE);
        btnSend.setFocusPainted(false);
        btnSend.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        txtStatus = new JTextArea(5, 30);
        txtStatus.setFont(font);
        txtStatus.setEditable(false);
        txtStatus.setBackground(new Color(10, 15, 30));
        txtStatus.setForeground(new Color(255, 215, 0));

        JScrollPane statusScroll = new JScrollPane(txtStatus);
        statusScroll.setBorder(BorderFactory.createLineBorder(new Color(60, 80, 120)));

        bottomPanel.add(btnSend, BorderLayout.NORTH);
        bottomPanel.add(statusScroll, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // 🔥 Load Local IP
        loadLocalIP();

        // 🔥 Button Action
        btnSend.addActionListener((ActionEvent e) -> sendPacket());

        setVisible(true);
    }

    private void loadLocalIP() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            txtLocalIP.setText(ip.getHostAddress());
        } catch (Exception e) {
            txtStatus.append("Error getting local IP\n");
        }
    }

    private void sendPacket() {
        try {
            String serverIP = txtServerIP.getText().trim();
            String message = txtMessage.getText().trim();

            if (serverIP.isEmpty() || message.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Server IP and Message!");
                return;
            }

            DatagramSocket socket = new DatagramSocket();

            byte[] data = message.getBytes();

            DatagramPacket packet = new DatagramPacket(
                    data,
                    data.length,
                    InetAddress.getByName(serverIP),
                    8000
            );

            socket.send(packet);

            txtStatus.append("Packet sent to " + serverIP + "\n");

            socket.close();

        } catch (Exception e) {
            txtStatus.append("❌ Error: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UDPClientUI());
    }
}