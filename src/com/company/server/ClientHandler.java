package com.company.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket client;

    public ClientHandler(Socket socket) {

        this.client = socket;

    }

    @Override
    public void run() {

        try {
            Thread.sleep(20000);

            //Receive the data from client
            InputStream inputStream = client.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());

            //Read continuously data from client
            String inputData;
            while ((inputData = bufferedReader.readLine()) != null) {

                System.out.println("Client says: " + inputData);

                //send data to client

                switch (inputData) {
                    case "Hello from the Client" :
                        outputStream.writeBytes("Hello from the server...\n");
                        break;
                    case "How are you" :
                        outputStream.writeBytes("I'm fine, how are you?\n");
                        break;
                    case "I'm fine":
                        outputStream.writeBytes("Okay good to know\n");
                        break;
                    case "Thank you":
                        outputStream.writeBytes("You are Welcome!\n");
                        break;
                    default:
                        outputStream.writeBytes("I didn't understand that.\n");
                }



                if(inputData.equals("exit")) {
                    break;
                }

            }

            this.client.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

}
