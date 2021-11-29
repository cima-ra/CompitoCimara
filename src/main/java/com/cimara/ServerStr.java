package com.cimara;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class ServerStr {
    private int porta;

    private ServerSocket server;
    private Socket client;

    private BufferedReader inDalClient;
    private DataOutputStream outNelClient;

    private ArrayList<String> lista = new ArrayList<>();

    public ServerStr(int porta) {
        this.porta = porta;

        try {
            server = new ServerSocket(porta);
        } catch (Exception e) {
            System.out.println("Erorre nell'inizzializzazione del server \n");
            System.err.println(e.getMessage());
        }
    }

    public void connetti() {

        try {
            client = server.accept();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outNelClient = new DataOutputStream(client.getOutputStream());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void comunica() {

        String messaggio = "";
        System.out.println("Inizio comunicazione");

        try {
            while (true) {

                messaggio = inDalClient.readLine();

                if (messaggio.equalsIgnoreCase("chiudi")) {
                    break;
                }

                if (messaggio.equalsIgnoreCase("lista")) {
                    outNelClient.writeBytes("La lista contiene: ");

                    for (int i = 0; i < lista.size(); i++) {
                        outNelClient.writeBytes(lista.get(i));
                    }

                } else {
                }

                outNelClient.writeBytes(
                        "Inserisci un prodotto nella lista o digita LISTA per vedere la lista con i prodotti gia inseriti\n");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            chiudi();
        }

        chiudi();

    }

    public void chiudi() {
        try {

            inDalClient.close();
            outNelClient.close();
            client.close();
            server.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
