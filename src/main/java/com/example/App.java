package com.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Server avviato");
            ServerSocket server = new ServerSocket(3000);
            List<Client> clients = new ArrayList<Client>();

            for(;;){
                Socket s = server.accept();
                Client client = new Client(s, clients);
                client.start();
                System.out.println(s.getPort() + " si e' connesso");
                System.out.println("Totale client: " + clients.size() + 1);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server...");
        }
    }
}
