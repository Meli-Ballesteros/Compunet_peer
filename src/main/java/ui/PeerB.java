package ui;

import util.UDPConnection;

public class PeerB {

    public static void main(String[] args) {
        UDPConnection connection = UDPConnection.getInstance();
        String msj = "Hola desde el PeerB";
        connection.setPort(5001);

        connection.sendDatagram(msj, "127.0.0.1", 5000);
        connection.start();
    }
}
