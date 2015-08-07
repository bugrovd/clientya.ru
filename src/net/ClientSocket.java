package net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

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

    public String recvTo () throws IOException {

        try {

            InputStream in = socket.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            //BufferedInputStream bin = new BufferedInputStream(in);
            StringBuilder sb =  new StringBuilder();
            //FileOutputStream fs = new FileOutputStream("page.txt");
            //byte[] buf = new byte[150*1024];
            //int countByte = 1;
            String tmp = null;
            while ((tmp=bin.readLine())!=null) {
                    sb.append(tmp+"\r\n");
                    if (tmp.equals("</html>")) {
                        break;
                    }
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

