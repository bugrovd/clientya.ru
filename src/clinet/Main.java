package clinet;

import net.ClientSocket;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        String host = "bazarpnz.ru";
        ClientSocket s = new ClientSocket();
        RequestGenerator r = new RequestGenerator();
        if (s.createSocket(host))
            if (s.sendTo(r.getRequest(host, null))) {
                PageParser response = new PageParser(s.recvTo());
               // System.out.println(response.getCountPage());
                response.getList();
            }
    }
}
