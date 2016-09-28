package lab02_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Rafiul Sabbir on 26/09/16.
 */

class TCPClient
{
    public String getOutputFromServer(String data, String IP_Address) throws Exception
    {
        try {
            String result = "";
            String line;

//            Socket clientSocket = new Socket("localhost", 6789);
            Socket clientSocket = new Socket(IP_Address, 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread.sleep(4000);

            outToServer.writeBytes(data + '\n');
            while ((line = inFromServer.readLine()) != null) {
                result += line + "\n";
            }
            System.out.println("FROM SERVER: " + result);
            clientSocket.close();
            return result;
        } catch (Exception e) {
            return e.getStackTrace().toString();
        }
    }
}