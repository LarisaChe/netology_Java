package ru.netology.lache;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8090;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            //out.println("GET / HTTP1.1 \n" + "Host: local \n\n\n");
            out.println("Larisa");
            String resp = in.readLine();
            System.out.println(resp);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
