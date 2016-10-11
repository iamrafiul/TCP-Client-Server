package lab02_client;


import java.lang.Thread;

/**
 * Author: Rafiul Sabbir
 * Contact: iamrafiul@gmail.com
 * Date Created: 9/14/16
 */


public class EmptyLoop {

    public EmptyLoop(){
    }

    public void itereate_loop() throws InterruptedException {
        for (int i = 0; ; i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
    }
}
