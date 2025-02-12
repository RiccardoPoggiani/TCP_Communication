package tcp_communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RP
 */

public class Server {
    ServerSocket serverSocket;
    Socket clientSocket;
    int porta;
    
    
    public Server(int porta){
        this.porta=porta;
        try{
            serverSocket = new ServerSocket(porta);
            System.out.println("1) SERVER IN ASCOLTO");
        } catch(BindException ex) {
            System.out.println("PORTA OCCUPATA");
        } catch(IllegalArgumentException ex) {
            System.out.println("NUMERO DI PORTA NON VALIDO");
        }
        catch(IOException ex){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ERRORE DEL SERVER NELLA FASE DI BINDING");
        }
    }
    
    
    public Socket attendi(){
        try {
            clientSocket = serverSocket.accept();
            System.out.println("2) CONNESSIONE CON IL CLIENT AVVENUTA E DATA SOCKET CREATO");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("PROBLEMI DI CONNESSIONE CON IL CLIENT");
        }
        return clientSocket;
    }
        
    
    public void leggi() {
        InputStream i;
        try {
            i = clientSocket.getInputStream();
            i.read();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    public void scrivi() {
        OutputStream o;
        try {
            o = clientSocket.getOutputStream();
            o.write(1);
            o.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    public void chiudi() {
        try {
            clientSocket.close();
            System.out.println("5) CHIUSUSRA COMUNICAZIONE");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    public void termina() {
        try {
            serverSocket.close();
            System.out.println("6) CHIUSURA SERVER");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
