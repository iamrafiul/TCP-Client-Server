package lab02_client;

/**
 * Author: Rafiul Sabbir
 * Contact: iamrafiul@gmail.com
 * Date Created: 9/14/16
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
//        LoopThread thread = new LoopThread();

        CommandLineInterface cmd_gui = new CommandLineInterface();
        cmd_gui.setVisible(true);

        EmptyLoop loop = new EmptyLoop();
        loop.itereate_loop();

//        thread.start();
    }
}
