package tcp_communication;

import java.io.*;
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
<<<<<<< HEAD
=======
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
>>>>>>> 3104c82 (Colori messaggi Client/Server)
    
    public Client(String nome, String colore){
        this.nome = nome;
        this.colore = colore;
    }
            
    public void connetti(String nomeServer, int porta){
        try {
            socket = new Socket(nomeServer, porta);
<<<<<<< HEAD
            System.out.println("1) CONNESSIONE AVVENUTA CON IL SERVER");
        } catch(ConnectException ex){
            System.out.println("ERRORE CONNESSIONE SERVER");
        } catch(UnknownHostException ex){
            System.out.println("ERRORE RISOLUZIONE DEL NOME");
        }catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRORE NELLA CONNESSIONE");
=======
            System.out.println(GREEN + "1) CONNESSIONE AVVENUTA CON IL SERVER" + RESET);
        } catch(ConnectException ex){
            System.out.println(GREEN + "ERRORE CONNESSIONE SERVER" + RESET);
        } catch(UnknownHostException ex){
            System.out.println(GREEN + "ERRORE RISOLUZIONE DEL NOME" + RESET);
        }catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(GREEN + "ERRORE NELLA CONNESSIONE" + RESET);
>>>>>>> 3104c82 (Colori messaggi Client/Server)
        }
    }

    public void leggi() {
        InputStream i;
        BufferedReader br;
        String str1;
        try {
            i = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(i));
            str1 = br.readLine();
<<<<<<< HEAD
            System.out.println("IL MESSAGGIO RICEVUTO E': " + str1);
=======
            System.out.println(GREEN + "3) IL MESSAGGIO RICEVUTO E': " + str1 + RESET);
>>>>>>> 3104c82 (Colori messaggi Client/Server)
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scrivi() {
        OutputStream o;
        BufferedWriter bw;
        String str2 = "Ciao Server!";
        try {
            o = socket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(o));
<<<<<<< HEAD
            bw.write(str2 + "\n");
=======
            bw.write(GREEN + str2 + "\n" + RESET);
>>>>>>> 3104c82 (Colori messaggi Client/Server)
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void chiudi() {
<<<<<<< HEAD
        if(socket != null) 
            try {
                socket.close();
                System.out.println("4) CHIUSURA CONNESSIONE CON IL SERVER");
        } catch (IOException ex) {
           Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
=======
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

>>>>>>> 3104c82 (Colori messaggi Client/Server)
}
