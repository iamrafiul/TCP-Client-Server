//package lab02_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Rafiul Sabbir on 26/09/16.
 */

class TCPServer
{
    public String execute_shell_command(String command) {
        // execute shell command from java interface
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            System.out.println("Given Command: " + command + "\nOutput is:\n");
            String output = " ";
            while( (line = reader.readLine()) != null ) {
                output += line + "\n";
            }
            return output;

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static void main(String argv[]) throws Exception
    {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            ThreadHandler handler = new ThreadHandler(connectionSocket);
            handler.start();
        }

    }


}