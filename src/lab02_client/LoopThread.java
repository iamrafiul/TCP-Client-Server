package TCP-Client-Server;

/**
 * Author: Rafiul Sabbir
 * Contact: iamrafiul@gmail.com
 * Date Created: 9/21/16
 */
public class LoopThread implements Runnable {
    private Thread t;

    LoopThread(){
        System.out.println("Thread Started");
    }

    public void run() {
        try {
            EmptyLoop loop = new EmptyLoop();
            loop.itereate_loop();

        } catch (Exception e) {
            System.out.println("Thread interupted!");
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}
