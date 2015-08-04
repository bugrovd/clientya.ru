package net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {
    private Socket socket;

    public boolean createSocket(String host) throws IOException {

        try {
            InetAddress ipAddress = InetAddress.getByName(host);
            socket = new Socket(ipAddress, 80);
            log("Создали сокет. Host:" + host);
            return true;
        } catch(IOException e){
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
            if (str == null || str=="") return false;
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
            BufferedInputStream bin = new BufferedInputStream(in);

            byte[] buf = new byte[256*1024];
            //int coutReadByte=1;
            //FileOutputStream f = new FileOutputStream("1.txt");
            //while (coutReadByte>0) {
                //coutReadByte=in.read(buf,0,buf.length);
                bin.read(buf,0,buf.length);
                //if (coutReadByte>0) {
                    //f.write(buf,0,coutReadByte);
                //}
           // }
            return buf;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

