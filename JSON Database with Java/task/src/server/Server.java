package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Server {
    DataInputStream input;
    DataOutputStream output;
    ProxyDatabase database;

    public void start(Socket socket, ProxyDatabase database) {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            this.database = database;

            communicate();

        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void communicate() {

            var incomingMessage = read().split(" ");

            String action = incomingMessage[0];

            int index = -1;
            if (incomingMessage.length > 1) {
                index = Integer.parseInt(incomingMessage[1]);
            }

            String text = "";
            if (action.equals("set")) {
               text = Arrays.stream(incomingMessage).skip(2).collect(Collectors.joining(" "));
            }

            if (action.equals("exit")) {
                Main.alive = false;
                return;
            }

            write(switch (action) {
                case "get" -> database.get(index);
                case "set" -> database.set(index, text);
                case "delete" -> database.delete(index);
                default -> "ERROR";
            });
    }

    private void write(String toWrite) {
        try {
            output.writeUTF(toWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sent: "+toWrite);
    }

    private String read() {
        String text;
        try {
            text = input.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Received: "+text);
        return text;
    }
}

