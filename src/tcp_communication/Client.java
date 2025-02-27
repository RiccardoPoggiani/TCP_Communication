package tcp_communication;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
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
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public Client(String nome, String colore){
        this.nome = nome;
        this.colore = colore;
    }
            
    public void connetti(String nomeServer, int porta){
        try {
            socket = new Socket(nomeServer, porta);
            System.out.println(GREEN + "1) CONNESSIONE AVVENUTA CON IL SERVER" + RESET);
        } catch(ConnectException ex){
            System.out.println(GREEN + "ERRORE CONNESSIONE SERVER" + RESET);
        } catch(UnknownHostException ex){
            System.out.println(GREEN + "ERRORE RISOLUZIONE DEL NOME" + RESET);
        }catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(GREEN + "ERRORE NELLA CONNESSIONE" + RESET);
        }
    }

    public void leggi() {
        String messaggioServer;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            messaggioServer = br.readLine();
            System.out.println(GREEN + "3) IL MESSAGGIO RICEVUTO E': " + messaggioServer + RESET);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scrivi() {
        System.out.println(GREEN + "INSERISCI IL MESSAGGIO DA INVIARE AL CLIENT ('end' PER SPEGNERE SERVER): " + RESET);
        Scanner in = new Scanner(System.in);
        String messaggioClient = in.nextLine();

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(GREEN + messaggioClient + "\n" + RESET);
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void chiudi() {
        if(socket != null) {
            try {
                socket.close();
                System.out.println(GREEN + "4) CHIUSURA CONNESSIONE CON IL SERVER" + RESET);
            } catch(ConnectException ex){
                System.out.println(GREEN + "ERRORE CONNESSIONE SERVER" + RESET);
            } catch (IOException ex){
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
