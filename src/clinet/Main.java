package clinet;

import net.ClientSocket;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ClientSocket s = new ClientSocket();
        s.createSocket();
    }
}
