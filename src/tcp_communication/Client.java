package tcp_communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RP
 */

public class Client {
    String nome;
    String colore;
    Socket socket;
    
    public Client(String nome){
        this.nome = nome;
    }
            
    public void connetti(String nomeServer, int porta){
        try {
            socket = new Socket(nomeServer, porta);
            System.out.println("1) CONNESSIONE AVVENUTA CON IL SERVER");
        } catch(ConnectException ex){
            System.out.println("ERRORE CEONNESSIONE SERVER");
        } catch(UnknownHostException ex){
            System.out.println("ERRORE RISOLUZIONE DEL NOME");
        }catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRORE NELLA CONNESSIONE");
        }
  }
    public void leggi() {
       InputStream i;
        try {
            i = socket.getInputStream();
            i.read();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scrivi() {
         try {
            OutputStream o = socket.getOutputStream();
            o.write(1);
            o.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void chiudi() {
        if(socket != null) 
            try {
                socket.close();
                System.out.println("4) CHIUSURA CONNESSIONE CON IL SERVER");
        } catch (IOException ex) {
           Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
