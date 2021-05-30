package com.company.client;

import java.io.*;
import java.net.Socket;

public class Client {

    private DataOutputStream outputStream;
    private BufferedReader bufferedReader;

    public void start() throws IOException {
        System.out.println("Client is running...");
        Socket socket = new Socket("localhost", 6000);
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        InputStream inputStream = socket.getInputStream();
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    }

    public void sendMessage(String message) throws IOException {
        System.out.println("Client says: "+ message);
        this.outputStream.writeBytes(message + "\n");
        String dataFromSServer = this.bufferedReader.readLine();
        System.out.println("Server says: "+ dataFromSServer);
    }

}
