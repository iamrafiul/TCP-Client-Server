package lab02_client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

/**
 * Author: Rafiul Sabbir
 * Contact: iamrafiul@gmail.com
 * Date Created: 9/14/16
 */

public class CommandLineInterface extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private static final int port = 45893;

    private JTextField input01;
    private JButton button01;
    private JTextArea output01;
    private JPanel cmd_gui;

    Font font = new Font("Times new Roman", Font.BOLD, 14);

    public CommandLineInterface() {
        setTitle("Command GUI");
        setSize(WIDTH, HEIGHT);

        Container contentPane = getContentPane();
        contentPane.setPreferredSize(new Dimension(400, 400));

        cmd_gui = new JPanel();
        cmd_gui.setSize(400, 400);


        button01 = new JButton("Execute");
        button01.setPreferredSize( new Dimension(100, 30));

        input01 = new JTextField();
        input01.setPreferredSize( new Dimension(300, 30));

        output01 = new JTextArea(20, 45);
        JScrollPane scrollPane = new JScrollPane(output01);


        cmd_gui.add(input01);
        cmd_gui.add(button01);
        cmd_gui.add(scrollPane);

        contentPane.add(cmd_gui);

        button01.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                final String data = input01.getText();
                input01.setText("");

                SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                    @Override
                    protected String doInBackground() throws Exception {
                        try {
                            TCPClient clientServer = new TCPClient();
                            String output = clientServer.getOutputFromServer(data);

                            return output;

                        } catch(Exception e) {
                            return e.getStackTrace().toString();
                        }
                    }

                    // Can safely update the GUI from this method.
                    protected void done() {

                        String status;
                        try {
                            // Retrieve the return value of doInBackground.
                            status = get();
                            output01.append("Input Command: " + data  + "\n\n");
                            output01.append("Completed with status: \n" + status  + "\n***********************************************************************\n");
                        } catch (InterruptedException e) {
                            // This is thrown if the thread's interrupted.
                        } catch (ExecutionException e) {
                            // This is thrown if we throw an exception
                            // from doInBackground.
                        }
                    }

                };

                worker.execute();
            }
        });
    }
}
