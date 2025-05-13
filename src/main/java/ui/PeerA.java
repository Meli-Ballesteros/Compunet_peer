package ui;

import util.UDPConnection;

public class PeerA {
    public static void main(String[] args) {
        UDPConnection connection = UDPConnection.getInstance();
        connection.setPort(5000);
        connection.start(); // Inicia recepción
        connection.startSendingLoop("192.168.131.200", 5001); // Inicia envío
    }
}


