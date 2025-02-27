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


    public void attendi() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println(BLUE + "NUOVO CLIENT CONNESSO: " + clientSocket.getInetAddress() + RESET);

                ClientHandler clientHandler = new ClientHandler(clientSocket, this); // Passo il riferimento al server
                new Thread(clientHandler).start();

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println(BLUE + "ERRORE: PROBLEMI DI CONNESSIONE CON IL CLIENT" + RESET);
                break;
            }
        }
    }


    public void chiudi() {
        try {
            clientSocket.close();
            System.out.println(BLUE + "5) CHIUSUSRA COMUNICAZIONE CON IL CLIENT" + RESET);
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
