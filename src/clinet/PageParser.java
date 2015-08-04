package clinet;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageParser {
    public String codeResponse;
    public String cookie;
    public byte[] bufferData;

    public PageParser(byte[] data) {
        this.bufferData=data;
    }

    public String pageResponse () throws UnsupportedEncodingException {
        if (bufferData[0] != 0x00) {//если в массиве что-то есть
            String str = new String(bufferData,"Windows-1251");
            int posHeaderEnd=0;
            for (int i=0;i<3000;i++) {
                if (str.charAt(i) == "\r".charAt(0) && str.charAt(i+1) == "\n".charAt(0) &&
                        str.charAt(i+2) == "\r".charAt(0) && str.charAt(i+3) == "\n".charAt(0)) posHeaderEnd=i;
            }

            if (posHeaderEnd != 0) {
                String header = str.substring(0,posHeaderEnd);
                String[] caseHeadParam = {"HTTP/1.","Cookie","Content-Type"};
                String[] headParams = new String[caseHeadParam.length];

                for (int i=0;i<caseHeadParam.length;i++) {
                    Pattern p = Pattern.compile(caseHeadParam[i]+".+");
                    Matcher m = p.matcher(header);
                    if (m.find()) {
                        headParams[i] = header.substring(m.start()+p.pattern().length(),m.end());
                    }
                }

                for (int i=0;i<headParams.length;i++) {
                    switch (i) {
                        case 0: {
                            codeResponse = headParams[i];
                            break;
                        }
                        case 1: {
                            cookie = headParams[i];
                            break;
                        }
                    }
                }
            }
        }
        return null;
    }
}
