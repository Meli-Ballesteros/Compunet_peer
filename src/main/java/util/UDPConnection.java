package util;


import java.io.IOException;
import java.net.*;

public class UDPConnection extends Thread {
    private static UDPConnection instance;

    private DatagramSocket socket;

    private UDPConnection(){}

    public static UDPConnection getInstance(){
        if(instance == null){
            instance = new UDPConnection();
        }
        return instance;
    }

    public void setPort(int port){
        try {
            this.socket = new DatagramSocket(port);
        }catch (SocketException e){
            e.printStackTrace();
        }
    }

    public void close(){
        socket.close();
    }

    @Override
    public void run(){
        try {
            // se define una estructura para guardar la información
            byte[] buffer = new byte[1024];

            // definen el paquete donde se guarda el mensaje recivido
            DatagramPacket packet = new DatagramPacket(buffer, 1024);

            System.out.println("Waiting for a a message ... ");

            // El socket establce la conexión con el sender
            // Almacena el packet del sender en un packet
            // de recepción
            socket.receive(packet);
            System.out.println("Messsaje from sender: ");

            // decodificamos de bytes a string
            String msj = new String(packet.getData()).trim();

            System.out.println("\n" +
                    "message from: "+ packet.getAddress() + ": " + msj );

            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendDatagram(String msj, String ipDest, int portDest){
       new Thread(
               () -> {
                   try {
                      InetAddress ipAddress = InetAddress.getByName(ipDest);

                       DatagramPacket packet = new DatagramPacket(
                               msj.getBytes(), // Message
                               msj.length()    // length message
                       );

                       //					dest. ip     | dest. port
                       socket.connect(ipAddress, portDest);
                       // se empaqueta y se le pasa al socket
                       socket.send(packet);
                   }
                   catch (IOException e){
                        e.printStackTrace();
                   }
               }
       ).start();
    }

}
