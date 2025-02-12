package tcp_communication;

/**
 *
 * @author RP
 */
public class MainClient {
    public static void main(String[] args) {
        Client c = new Client("localhost");
        c.connetti("localhost", 50005);
       // c.leggi();
        //c.scrivi();
        c.chiudi();
    }
    
}
