package clinet;

public class RequestGenerator {
    public String host = "";
    public String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
    public String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64)";
    public String acceptEncoding = "gzip, deflate, lzma, sdch";
    public String acceptLanguage = "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4";
    public String cookie = "";
    public String cacheControl = "no-cache";
    public String connection = "keep-alive";

    public String getRequest(String host,String param) {
        if (host!="") {
            this.host = host;
        } else {
            log("Get запрос пустой!!!");
            return "";
        }
        if (param == null) param="";
        return "GET http://"+host+"/"+param+" HTTP/1.1\n"+
                "Host: "+host+"\n"+
                //"Connection: "+connection+"\n"+
                "Cache-Control:" +cacheControl+"\n"+
                "Accept: "+accept+"\n"+
                "User-Agent: "+userAgent+"\n"+
                //"Accept-Encoding: "+acceptEncoding+"\n"+
                "Accept-Language: "+acceptLanguage+"\n"+
                "Cookie: "+cookie+"\n\n";
    }

    public void log (String s) {
        System.out.println(s);
    }
}
