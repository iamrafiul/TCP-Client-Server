//package lab02;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
///**
// * Author: Rafiul Sabbir
// * Contact: iamrafiul@gmail.com
// * Date Created: 9/18/16
// */
//public class ExecuteShellCommand{
//
//
//    public String execute_shell_command(String command) {
//        // execute shell command from java interface
//        Process p = null;
//        try {
//            p = Runtime.getRuntime().exec(command);
//            p.waitFor();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line = "";
////            String output = "Given Command: " + command + "\nOutput is:\n";
//            String output = "";
//            while( (line = reader.readLine()) != null ) {
//                output += line + "\n";
////                output += line + "\n";
////                output += line + "\n";
//            }
//            return output;
//
//        } catch (IOException e) {
//            return e.getMessage();
//        } catch (InterruptedException e) {
//            return e.getMessage();
//        }
////        return "";
//    }
//}
