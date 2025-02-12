package tcp_communication;

import java.util.Scanner;

/**
 *
 * @author RP
 */

public class MainServer {
    public static void main(String[] args) {
       Server s = new Server(50005);
       int nClient;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Inserire il numero del client con il quale si vuole instaurare una comunicazione TCP: ");
            nClient = scanner.nextInt();
        }
       for(int i=0; i<nClient; i++) {
       s.attendi();
       //s.leggi();
       //s.scrivi();
       s.chiudi();
    }
      s.termina();
    }
    
}