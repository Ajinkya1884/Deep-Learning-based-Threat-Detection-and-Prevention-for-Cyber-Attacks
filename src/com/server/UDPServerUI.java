package com.server;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

public class UDPServerUI extends JFrame {

    private JTextArea logArea;
    private JTextArea blockedArea;
    private JLabel statusLabel;

    private static final int PORT = 8000;
    private static final Set<String> blockedIPs = new HashSet<>();

    public UDPServerUI() {
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
setUndecorated(false); // keep title bar (set true for kiosk mode)
        setTitle("Cyber Attack Prevention Server");
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);

        // 🌈 MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(20, 25, 40));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 🔹 TITLE
        JLabel title = new JLabel("UDP Server Dashboard", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        mainPanel.add(title, BorderLayout.NORTH);

        // 🔹 LOG AREA
        logArea = new JTextArea();
        logArea.setFont(font);
        logArea.setEditable(false);
        logArea.setBackground(new Color(10, 15, 30));
        logArea.setForeground(new Color(0, 200, 255));

        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("Live Logs"));

        mainPanel.add(logScroll, BorderLayout.CENTER);

        // 🔹 BLOCKED IP PANEL
        blockedArea = new JTextArea();
        blockedArea.setFont(font);
        blockedArea.setEditable(false);
        blockedArea.setBackground(new Color(10, 15, 30));
        blockedArea.setForeground(new Color(255, 80, 80));

        JScrollPane blockScroll = new JScrollPane(blockedArea);
        blockScroll.setBorder(BorderFactory.createTitledBorder("Blocked IPs"));

        mainPanel.add(blockScroll, BorderLayout.EAST);

        // 🔥 STATUS LABEL (Color Indicator)
        statusLabel = new JLabel("Status: WAITING...", JLabel.CENTER);
        statusLabel.setFont(titleFont);
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.GRAY);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        // 🔥 Start server thread
        new Thread(this::startServer).start();
    }

    // Log method
    private void log(String msg) {
        SwingUtilities.invokeLater(() -> logArea.append(msg + "\n"));
    }

    // Add blocked IP
    private void addBlockedIP(String ip) {
        SwingUtilities.invokeLater(() -> blockedArea.append(ip + "\n"));
    }

    // Update Status Color
    private void updateStatus(boolean isAttack) {
        SwingUtilities.invokeLater(() -> {
            if (isAttack) {
                statusLabel.setText("ATTACK DETECTED");
                statusLabel.setBackground(new Color(180, 0, 0)); // RED
            } else {
                statusLabel.setText("NORMAL TRAFFIC");
                statusLabel.setBackground(new Color(0, 150, 0)); // GREEN
            }
        });
    }

    // Server Logic
    private void startServer() {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            byte[] buffer = new byte[4096];

            log("Server started on port " + PORT);

            while (true) {

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String clientIP = packet.getAddress().getHostAddress();

                // 🚫 Block check
                if (blockedIPs.contains(clientIP)) {
                    log("BLOCKED IP tried access: " + clientIP);
                    String msg="BLOCKED IP tried access: " + clientIP;
                    String toEmail = "ajinkyaransubhe7@gmail.com";
                    Newemail.mailsend(msg, toEmail);
                    continue;
                }

                String data = new String(packet.getData(), 0, packet.getLength()).trim();

                log("\n===============================");
                log("Packet received");
                log("Time: " + new Date());
                log("Client IP: " + clientIP);
                log("Data: " + data);

                // 🔍 Prediction
                double[] probs;
                int result;
                boolean isAnomaly;
                //  boolean isAnomaly = InputPredictor.predictSingle(data);

                probs = InputPredictor.predictSingle(data);

                // get prediction
                result = (probs[1] > probs[0]) ? 1 : 0;
                isAnomaly = (result == 1);

                // SHOW MODEL OUTPUT IN UI
                log("===== PREDICTION RESULT =====");
                if (isAnomaly) {
                    log("ATTACK DETECTED");
                    updateStatus(true);

                    blockedIPs.add(clientIP);
                    addBlockedIP(clientIP);

                    log("IP BLOCKED: " + clientIP);
                    String msg="IP Address BLOCKED: " + clientIP;
                    String toEmail = "ajinkyaransubhe7@gmail.com";
                    Newemail.mailsend(msg, toEmail);

                } else {
                    log("NORMAL TRAFFIC");
                    updateStatus(false);
                }

                log("----------------------------------");
                log("MODEL OUTPUT");
                log("Normal: " + String.format("%.2f", probs[0] * 100) + "%");
                log("Attack: " + String.format("%.2f", probs[1] * 100) + "%");
                log("----------------------------------");

            }

        } catch (Exception e) {
            log("Server Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UDPServerUI::new);
    }
}
