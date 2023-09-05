package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import com.sun.net.httpserver.HttpExchange;

public class SendResponse {
    public static void header(PrintWriter writer, int status, String contentType) throws IOException {
        if(writer==null) {
            System.out.println("writer = null");
        } else {
            System.out.println("writer = notnull");
        }
        writer.println("HTTP/1.1 "+status+" OK");
        writer.println("Date: " + getDate());
        writer.println("Server: Henacat");
        writer.println("Connection: close");
        writer.println("Content-type: " + contentType);
        writer.println("");
    }

    public static String getDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.JAPANESE);
        format.setTimeZone(calendar.getTimeZone());
        return format.format(calendar.getTime()) + " GMT";
    }

    public static void sendResponseHeader(HttpExchange exchange) throws IOException{
        System.out.println("Qb agfdgdgsdgsdfgsdfgdsf");
        exchange.sendResponseHeaders(200, 12);
    }
}
