//package lab02_server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Rafiul Sabbir on 26/09/16.
 */

class TCPServer
{

    public static void main(String argv[]) throws Exception
    {
        String log_level;
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            try {
                log_level = argv[0];
            } catch (Exception e) {
                log_level = "";
            }


            ThreadHandler handler = new ThreadHandler(connectionSocket, log_level);
//            ThreadHandler handler = new ThreadHandler(connectionSocket);
            handler.start();
        }

    }


}