//package lab02_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Rafiul Sabbir on 26/09/16.
 */

class ThreadHandler extends Thread {
    Socket connectionSocket;

    ThreadHandler(Socket client) {
        this.connectionSocket = client;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()
                    )
            );
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            String clientData = inFromClient.readLine();

            TCPServer thisServer = new TCPServer();
            String output = thisServer.execute_shell_command(clientData);

            System.out.println("Received command: " + clientData + " from: " + connectionSocket.getInetAddress().getHostAddress());

            for (String word : output.split("\n")) {
                outToClient.writeBytes(word + "\n");

            }
            outToClient.flush();
        } catch (Exception e) {
            System.err.println("Exception caught: client disconnected.");
        } finally {
            try {
                connectionSocket.close();
            } catch (Exception e) { ; }
        }
    }
}