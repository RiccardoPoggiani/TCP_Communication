package tcp_communication;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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
        this.porta = porta;
        try{
            serverSocket = new ServerSocket(porta);
            System.out.println("1) SERVER IN ASCOLTO SULLA PORTA " + porta);
        } catch(BindException ex) {
            System.out.println("ERRORE: SERVER NON IN ASCOLTO PER PORTA OCCUPATA");
        } catch(IllegalArgumentException ex) {
            System.out.println("ERRORE: SERVER NON IN ASCOLTO PER NUMERO DI PORTA NON VALIDO");
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
        BufferedReader br;
        String str1;
        try {
            i = clientSocket.getInputStream();
            br = new BufferedReader(new InputStreamReader(i));
            str1 = br.readLine();
            System.out.println("IL MESSAGGIO RICEVUTO E': " + str1);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("MESSAGGIO NON RICEVUTO");
        }
    }
        
    
    public void scrivi() {
        OutputStream o;
        BufferedWriter bw;
        String str2;
        try {
            o = clientSocket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(o));
            str2 = "CIAO CLIENT, TI ASPETTAVO!";
            bw.write(str2);
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("MESSAGGIO NON SPEDITO");
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
