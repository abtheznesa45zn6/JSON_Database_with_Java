package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Main {
    static ProxyDatabase database = new ProxyDatabase();
    public static volatile boolean alive = true;

    public static void main(String[] args) {
        System.out.println("Server started!");

        String address = "127.0.0.1";
        int port = 23456;


        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));) {
            while (alive){
                Server s = new Server();
                s.start(server.accept(), database);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}