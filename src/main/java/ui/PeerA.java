package ui;

import util.UDPConnection;

public class PeerA {
    public static void main(String[] args) {
        UDPConnection connection = UDPConnection.getInstance();
        connection.setPort(5000);
        connection.start();

        String msj = "Hola desde el Peer A";
        connection.sendDatagram(msj, "127.0.0.1", 5001);
    }
}
