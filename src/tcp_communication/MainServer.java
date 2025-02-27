package tcp_communication;

import java.util.Scanner;

/**
 *
 * @author RP
 */

public class MainServer {
    public static void main(String[] args) {
       Server s = new Server(60000);
       s.attendi();
    }
}