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
                System.out.print(response.pageResponse());
                //response.countPage();
                //r.cookie = response.cookie;
                //response.pageResponse();
            }
/*        String s = "HTTP/1.1 200 OK HTTP\r\n" +
                "Cookies:csfsdxsxsds dse2 111s=scs;\r\n";
        Pattern p = Pattern.compile("HTTP");
        Matcher m = p.matcher(s);
        m.find();
        System.out.println(m.group());*/

    }
}
