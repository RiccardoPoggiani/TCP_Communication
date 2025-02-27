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
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";

    public Server(int porta){
        this.porta = porta;
        try{
            serverSocket = new ServerSocket(porta);
            System.out.println(BLUE + "1) SERVER IN ASCOLTO SULLA PORTA " + porta + RESET);
        } catch(BindException ex) {
            System.out.println(BLUE + "ERRORE: SERVER NON IN ASCOLTO PER PORTA OCCUPATA" + RESET);
        } catch(IllegalArgumentException ex) {
            System.out.println(BLUE + "ERRORE: SERVER NON IN ASCOLTO PER NUMERO DI PORTA NON VALIDO" + RESET);
        }
        catch(IOException ex){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(BLUE + "ERRORE DEL SERVER NELLA FASE DI BINDING" + RESET);
        }
    }
    
    
    public Socket attendi(){
        try {
            clientSocket = serverSocket.accept();
            System.out.println(BLUE + "2) CONNESSIONE CON IL CLIENT AVVENUTA E DATA SOCKET CREATO" + RESET);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(BLUE + "ERRORE: PROBLEMI DI CONNESSIONE CON IL CLIENT" + RESET);
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
            System.out.println(BLUE + "3) IL MESSAGGIO RICEVUTO E': " + str1 + RESET);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(BLUE + "ERRORE: MESSAGGIO NON RICEVUTO" + RESET);
        }
    }
        
    
    public void scrivi() {
        OutputStream o;
        BufferedWriter bw;
        String str2;
        try {
            o = clientSocket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(o));
            str2 = "Ciao Client!";
            bw.write(BLUE + str2 + "\n" + RESET);
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(BLUE + "ERRORE: MESSAGGIO NON SPEDITO" + RESET);
        }
    }
        
    
    public void chiudi() {
        try {
            clientSocket.close();
            System.out.println(BLUE + "5) CHIUSUSRA COMUNICAZIONE" + RESET);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    public void termina() {
        try {
            serverSocket.close();
            System.out.println(BLUE + "6) CHIUSURA SERVER" + RESET);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
