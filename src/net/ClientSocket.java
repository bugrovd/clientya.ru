package net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by home on 01.08.2015.
 */
public class ClientSocket {
    private Socket socket;

    public boolean createSocket(String host) throws IOException {

        try {
            InetAddress ipAddress = InetAddress.getByName(host);
            socket = new Socket(ipAddress, 80);
            log("Создали сокет. Host:" + host);

           /* InputStream sin = socket.getInputStream();
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
            log(s);*/
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

    public boolean sendTo (String str) throws IOException{
        try {
            OutputStream out = socket.getOutputStream();//поток отправки
            DataOutputStream sout = new DataOutputStream(out);
            if (str == "") return false;
            //byte[] buf = str.getBytes();//буфер отправки
            sout.writeUTF(str);
            sout.flush();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String recvTo () throws IOException {
        try {
            InputStream in = socket.getInputStream();
            byte[] buf = new byte[64*1024];
            int coutReadByte=1;
            while (coutReadByte>0) {
                coutReadByte=in.read(buf);
                if (coutReadByte>0) return new String(buf, "UTF8");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

