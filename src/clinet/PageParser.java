package clinet;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
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
    } //парсинг Resopnse

    public int getCountPage () throws UnsupportedEncodingException {
            Pattern p = Pattern.compile("topage.+.&nbsp;&nbsp;");
            Matcher m = p.matcher(pageCode);
            if (m.find()) {
                String str = m.group();
                p = Pattern.compile("p=[0-9]{1,6}");
                m = p.matcher(str);
                int maxPage=0;
                while (m.find()) {
                    str = m.group();
                    str = str.replaceAll("p=", "");
                    if (maxPage<Integer.valueOf(str)) maxPage = Integer.valueOf(str);
                }
                return maxPage/50;
            }
        return 0;
    } //количество страниц

    public String[] getList () { //полный набор объявлений
        Pattern p = Pattern.compile("<table class=list>");
        Matcher m = p.matcher(pageCode);
        if (m.find()) {
            String str = pageCode.substring(m.start(),pageCode.length());
            p = Pattern.compile("</table>");
            m = p.matcher(str);
            if (m.find()) {
                str = str.substring(0, m.start());
                p = Pattern.compile("<td class=\"date\".+\r\n.+");
                m = p.matcher(str);
                while (m.find()) {
                    System.out.println(m.group());
                }
            }
/*
                p = Pattern.compile("<div class=\"vdatext\".+\r\n.+\r\n.+");
                m = p.matcher(str);
                while (m.find()) {
                    System.out.println(m.group());
                }*/
/*                p = Pattern.compile("<span class=\"price\".+");
                m = p.matcher(str);
                while (m.find()) {
                    System.out.println(m.group());
                    }
                }*/
        }
        return new String[0];
    }
}
