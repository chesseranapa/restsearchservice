package ru.avevdokimov.springboot.test.restsearchservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avevdokimov.springboot.test.restsearchservice.context.ApplicationContextHolder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class HelperForConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelperForConnection.class);
    private static final String DELIMITER = "?";
    private static final String EQUAL = "=";
    private static final String AMPERSAND = "&";
    private static final String PREFIX = "https://";
    private static final String PATH = "/2.2/search";
    private static final String ZIP_TYPE = "gzip";


    public static void setRequestMetod(HttpURLConnection con, String metod) throws ProtocolException {
        try {
            con.setRequestMethod(metod);
        } catch (ProtocolException e) {
            LOGGER.error(e.getMessage());
            throw new ProtocolException(metod);
        }
    }

    public static String getURLWithParametrs(String prefix, String host, String path, List<Params> params) {
        StringBuffer url = new StringBuffer(prefix);
        url.append(host);
        url.append(path);
        url.append(DELIMITER);
        for (int i = 0; i < params.size(); i++) {
            url.append(params.get(i).getKey()+ EQUAL + (params.get(i).getValue()));
            if (i < (params.size()-1)) {
                url.append(AMPERSAND);
            }
        }

        return url.toString();
    }

    public static String getSearchURL(List<Params> params) {
        AppSettings appSettings = ApplicationContextHolder.getApplicationContext().getBean(AppSettings.class);
        return getURLWithParametrs(PREFIX, appSettings.baseHostUrl, PATH, params);
    }


    public static void setHeaders(HttpURLConnection con) throws ProtocolException{
        AppSettings appSettings = ApplicationContextHolder.getApplicationContext().getBean(AppSettings.class);
        con.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        con.setRequestProperty("Host", appSettings.baseHostUrl);
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Cache-Control", "no-cache");
    }

    public static String getResultForGetReguest(String url)  {
        HttpURLConnection con = null;
        URL obj = null;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            setHeaders(con);
            setRequestMetod(con,  "GET");
            String type = con.getHeaderField("Content-Encoding");
            if (type.equals(ZIP_TYPE)) {
                return getZipResponse(con);
            }
            return getResponse(con);

        } catch (MalformedURLException | ProtocolException  e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

    public static String getZipResponse(HttpURLConnection con){
        try(DataInputStream pr = new DataInputStream(con.getInputStream())) {
            GZIPInputStream gis = new GZIPInputStream(pr);
            BufferedReader reader = new BufferedReader(new InputStreamReader(gis, Charset.forName("utf-8")));

            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String getResponse(HttpURLConnection con){
        try(DataInputStream pr = new DataInputStream(con.getInputStream())) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr, Charset.forName("utf-8")));

            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
