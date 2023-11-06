package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class Client extends Thread{

    private Socket s;
    private List<Client> clients;
    private Note Notes;

    
    public Client(Socket s, List<Client> clients, Note notes) {
        this.s = s;
        this.clients = clients;
        this.Notes = notes;
    }
    public Client(Socket s, List<Client> clients) {
        this.s = s;
        this.clients = clients;
        this.Notes = new Note();
    }


    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String codiceRicevuto = "";
            String notaRicevuta = "";
            do {
                codiceRicevuto = in.readLine();
                System.out.println(s.getPort() + " invia " + codiceRicevuto);
                if (codiceRicevuto.equals("1")) {
                    notaRicevuta = in.readLine();
                    System.out.println(s.getPort() + " Nota: " + notaRicevuta);
                    this.Notes.addNote(notaRicevuta);
                } else if (codiceRicevuto.equals("@")){
                    System.out.println(this.Notes.toString());
                    out.writeBytes(this.Notes.toString()+"\n");
                }
            } while (!codiceRicevuto.equals("0"));
            System.out.println(s.getPort() + " ha chiuso la connessione");
            s.close();
            clients.remove(this);
        } catch (Exception e) {
            System.out.println(e);
            clients.remove(this);
        }
    }
}
