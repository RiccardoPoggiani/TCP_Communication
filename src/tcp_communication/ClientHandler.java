package tcp_communication;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Server server;  // Aggiunto riferimento al server

    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";

    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            String messaggioClient;
            while ((messaggioClient = br.readLine()) != null) {
                System.out.println(BLUE + "MESSAGGIO RICEVUTO DAL CLIENT: " + RESET + messaggioClient);

                String risposta = "Ciao Client, ho ricevuto il tuo messaggio!";
                bw.write(risposta + "\n");
                bw.flush();

                clientSocket.close();


                if (messaggioClient == "end") {
                    System.out.println(BLUE + "Ricevuto comando di chiusura dal client." + RESET);
                    server.termina();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ERRORE GESTIONE CLIENT");
        }
    }
}
