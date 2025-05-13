package ui;

import util.UDPConnection;

public class PeerB {
    public static void main(String[] args) {
        UDPConnection connection = UDPConnection.getInstance();
        connection.setPort(5001);
        connection.start(); // Inicia recepción
        connection.startSendingLoop("192.168.1.15", 5000); // Inicia envío
    }
}

