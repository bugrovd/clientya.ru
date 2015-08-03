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
            //socket.setKeepAlive(true);
            log("Создали сокет. Host:" + host);
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
            sout.writeBytes(str);
            sout.flush();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public byte[] recvTo () throws IOException {

        try {
            InputStream in = socket.getInputStream();

            byte[] buf = new byte[256*1024];
            int coutReadByte=1;
            while (coutReadByte>0) {
                coutReadByte=in.read(buf,0,coutReadByte);
                    if (coutReadByte>0) {
                        return buf;
                        ///return new String(buf, "Windows-1251");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

