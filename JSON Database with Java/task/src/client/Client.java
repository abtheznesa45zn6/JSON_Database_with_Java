package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

public class Client {
    DataInputStream input;
    DataOutputStream output;
    String type;
    int index;
    String value;

    public Client(DataInputStream input, DataOutputStream output, String type, int index, String value) {
        this.input = input;
        this.output = output;
        this.type = type;
        this.index = index;
        this.value = value;
    }

    public void start() {
        System.out.println("Client started!");
        communicate();
    }

    private void communicate() {
        write(type + " " + index + " " + value);
        read();
        write("exit");
    }

    private void write(String toWrite) {
        try {
            output.writeUTF(toWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sent: "+toWrite);
    }

    private void read() {
        String text;
        try {
            text = input.readUTF();
        } catch (EOFException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Received: "+text);
    }
}
