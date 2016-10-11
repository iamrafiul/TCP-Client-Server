//package lab02_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Rafiul Sabbir on 26/09/16.
 */

class ThreadHandler extends Thread {
    Socket connectionSocket;
    Logger log;
    String log_level;

//    ThreadHandler(Socket client) {
//        this.connectionSocket = client;
//    }

//    ThreadHandler(Socket client, String level) {
//        this.connectionSocket = client;
//        this.level = level;
//    }

    ThreadHandler(Socket client, String log_label) {
        this.connectionSocket = client;
        this.log_level = log_label;
    }

    public String execute_shell_command(String command) {
        // execute shell command from java interface
        Process p;
        log.info("Process created for command execution");
        log.fine("Process created for command execution");

        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();

            log.info("Command has been executed");
            log.fine("Command has been executed");

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            String output = " ";
            while( (line = reader.readLine()) != null ) {
                output += line + "\n";
            }

            log.info("Command execution output is ready");
            log.fine("Command execution output is ready");

            return output;

        } catch (Exception e) {
            log.warning("Exception caught: " + e.getMessage().toString());
            return e.getMessage();
        }
    }

    public void run() {

        try {
            FileHandler fh = new FileHandler("serv.log");
            log = Logger.getLogger("log_file");
            log.addHandler(fh);
        } catch (SecurityException e1) {
            e1.printStackTrace();
            return;
        } catch (IOException e2) {
            e2.printStackTrace();
            return;
        }
        Level level;
        if (this.log_level.contains("warning"))
            level = Level.WARNING;
        else if (this.log_level.contains("info"))
            level = Level.INFO;
        else if (this.log_level.contains("error"))
            level = Level.SEVERE;
        else if (this.log_level.contains("debug"))
            level = Level.FINE;
        else
        {
            System.out.println("Provide a logging level: error/warn/info/debug");
            System.exit(1);
            return;
        }
        log.setLevel(level);


        log.fine("TCPThread for the command execution has started!");
        log.info("TCPThread for the command execution has started!");
        try {
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()
                    )
            );
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            String clientData = inFromClient.readLine();

            log.fine("Received command: " + clientData + " from: " + connectionSocket.getInetAddress().getHostAddress() );
            log.info("Received command: " + clientData + " from: " + connectionSocket.getInetAddress().getHostAddress() );

            String output = execute_shell_command(clientData);

            System.out.println("Received command: " + clientData + " from: " + connectionSocket.getInetAddress().getHostAddress());

            for (String word : output.split("\n")) {
                outToClient.writeBytes(word + "\n");
                log.info("Sending the output of the command " + clientData + " to the client");
            }
            outToClient.flush();
            log.info("Output of the command " + clientData + " has been sent to the client");
            log.fine("Output of the command " + clientData + " has been sent to the client");

        } catch (Exception e) {
            System.err.println("Exception caught: client disconnected.");
            log.severe("Exception caught: " + e.getStackTrace().toString());
        } finally {
            try {
                connectionSocket.close();
                log.info("Connection socket has been closed for the client");
                log.fine("Connection socket has been closed for the client");
            } catch (Exception e) {
                log.warning("Exception caught: " + e.getStackTrace().toString());
            }
        }
        log.info("TCPThread for the command execution has ended!");
        log.fine("TCPThread for the command execution has ended!");
    }
}