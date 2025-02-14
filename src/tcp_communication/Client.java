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
    
    public Client(String nome, String colore){
        this.nome = nome;
        this.colore = colore;
    }
            
    public void connetti(String nomeServer, int porta){
        try {
            socket = new Socket(nomeServer, porta);
            System.out.println("1) CONNESSIONE AVVENUTA CON IL SERVER");
        } catch(ConnectException ex){
            System.out.println("ERRORE CONNESSIONE SERVER");
        } catch(UnknownHostException ex){
            System.out.println("ERRORE RISOLUZIONE DEL NOME");
        }catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRORE NELLA CONNESSIONE");
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
            System.out.println("IL MESSAGGIO RICEVUTO E': " + str1);
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
            bw.write(str2 + "\n");
            bw.flush();
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
