package com.example.frontendpage.networkutils;

import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

public class NetworkUtils {
    public static void sendFile(File file) throws IOException {
        final String url = "10.0.2.2:8080/upload";
        final String charset = "UTF-8";
        final String param = "value";

//        File textFile = new File("/path/to/file.txt");
//        File binaryFile = new File("/path/to/file.bin");

        final String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
        final String CRLF = "\r\n"; // Line separator required by multipart/form-data.

        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (
            OutputStream output = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
            ) {
            //Send a file
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"").append(CRLF);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName())).append(CRLF);
            writer.append("Content-Transfer-Encoding: binary").append(CRLF);
            writer.append(CRLF).flush();
            Files.copy(file.toPath(), output);
            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.


            // End of multipart/form-data.
            writer.append("--" + boundary + "--").append(CRLF).flush();
        }

        // Request is lazily fired whenever you need to obtain information about response.
        int responseCode = ((HttpURLConnection) connection).getResponseCode();
        Log.d("RESPONSE", "response code: " + String.valueOf(responseCode)); // Should be 200
    }
}
