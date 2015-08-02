package net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by home on 01.08.2015.
 */
public class ClientSocket {
    private String IP = "213.180.204.3";
    private Socket socket;

    public boolean createSocket() throws IOException {

        try {
            InetAddress ipAddress = InetAddress.getByName(IP);
            socket = new Socket(ipAddress, 80);
            log("Создали сокет");

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(sout);

            out.writeUTF("GET");
            out.flush();
            log("Отправили Hello");
            log("Ждём ответа от сервера");
            byte[] buf = new byte[64*1024];
            int r=1;
            String s="";
            while (r>0) {
                r=sin.read(buf);
                if (r>0) {
                    s = new String(buf,"UTF8");
                }
            }
            log(s);

            return true;
        }

        catch(IOException e){
            e.printStackTrace();
        }

        return false;
    }

    public void log (String s) {
        System.out.println(s);
    }
}

