package com.chipay.payments.util;

import java.io.*;
import java.net.Socket;

public class ClamAVScanner {

    private final String host;
    private final int port;

    public ClamAVScanner(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean scan(InputStream fileStream) throws IOException {
        try (Socket socket = new Socket(host, port);
             OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream()) {

            // INSTREAM command
            out.write("zINSTREAM\0".getBytes());
            out.flush();

            byte[] buffer = new byte[2048];
            int read;
            while ((read = fileStream.read(buffer)) != -1) {
                byte[] length = {
                        (byte) ((read >> 24) & 0xff),
                        (byte) ((read >> 16) & 0xff),
                        (byte) ((read >> 8) & 0xff),
                        (byte) (read & 0xff)
                };
                out.write(length);
                out.write(buffer, 0, read);
                out.flush();
            }

            // Send 0-length chunk to mark EOF
            out.write(new byte[]{0, 0, 0, 0});
            out.flush();

            // Read result
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String result = reader.readLine();
            return result != null && result.contains("OK");
        }
    }
}
