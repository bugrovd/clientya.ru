package clinet;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageParser {
    public String codeResponse;
    public String cookie;
    public String pageCode;
    private int posHeaderEnd=0;

    public PageParser(String pageCode) {
        this.pageCode=pageCode;
    }

    public boolean pageResponse () throws UnsupportedEncodingException {
        if (pageCode.length()>0) {//если в массиве что-то есть
            Pattern p = Pattern.compile("\r\n\r\n");
            Matcher m = p.matcher(pageCode);
            if (m.find()) {
                posHeaderEnd=m.start();
            }

            if (posHeaderEnd != 0) {
                String header = pageCode.substring(0,posHeaderEnd);
                String[] caseHeadParam = {"HTTP/1.","Cookie","Content-Type"};
                String[] headParams = new String[caseHeadParam.length];

                for (int i=0;i<caseHeadParam.length;i++) {
                    p = Pattern.compile(caseHeadParam[i]+".+");
                    m = p.matcher(header);
                    if (m.find()) {
                        headParams[i] = header.substring(m.start()+p.pattern().length(),m.end());
                        System.out.println(headParams[i]);
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
            if (codeResponse.equals("200 OK")) return true;
        }
        return false;
    }

/*    public int countPage () throws UnsupportedEncodingException {
        for (int i=0;i<pageCode.length;i++) {
            System.out.print((char)bufferData[i]);
        }
        return 0;
    }*/
}
