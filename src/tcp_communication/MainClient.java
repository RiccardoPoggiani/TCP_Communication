package tcp_communication;

/**
 *
 * @author RP
 */
public class MainClient {
    public static void main(String[] args) {
        Client c = new Client("Riccardo", "verde");
        c.connetti("localhost", 60000);
        c.scrivi();
        c.leggi();
        c.chiudi();
    }
    
}
