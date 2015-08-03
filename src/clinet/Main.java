package clinet;

import net.ClientSocket;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String host = "bazarpnz.ru";
        ClientSocket s = new ClientSocket();
        RequestGenerator r = new RequestGenerator();
        if (s.createSocket(host)) {
            if (s.sendTo(r.getRequest(host,""))) {
                s.recvTo();
                s.log(r.getRequest(host,""));
            }
        }
    }
}
