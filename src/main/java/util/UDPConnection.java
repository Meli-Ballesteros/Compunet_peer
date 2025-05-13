package util;


import java.io.IOException;
import java.net.*;
import java.util.Scanner;

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
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Esperando mensajes...");

            while (true) {
                socket.receive(packet);
                String msj = new String(packet.getData(), 0, packet.getLength()).trim();
                System.out.println("Mensaje recibido de " + packet.getAddress() + ": " + msj);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void startSendingLoop(String ipDest, int portDest) {
        new Thread(() -> {
            try {
                InetAddress ipAddress = InetAddress.getByName(ipDest);
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.print("Escribe un mensaje: ");
                    String msj = scanner.nextLine();
                    DatagramPacket packet = new DatagramPacket(
                            msj.getBytes(), msj.length(), ipAddress, portDest
                    );
                    socket.send(packet);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
