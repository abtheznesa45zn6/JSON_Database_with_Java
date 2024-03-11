package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    static DataInputStream input;
    static DataOutputStream output;

    @Parameter(names={"--type", "-t"})
    String type = "";
    @Parameter(names={"--index", "-i"})
    int index = -1;
    @Parameter(names={"--value", "-m"})
    String value = "";

    public static void main(String[] args) {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.start();
    }

    private void start(){
        String address = "127.0.0.1";
        int port = 23456;

        try (Socket socket = new Socket(InetAddress.getByName(address), port);
             var input = new DataInputStream(socket.getInputStream());
             var output = new DataOutputStream(socket.getOutputStream())){
            Client client = new Client(input, output, type, index, value);
            client.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

