package ru.netology.lache;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainClient {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8095;
        List<String> listStr = new ArrayList<>();
        listStr.add("А у меня в кармане гвоздь! А у вас?");
        listStr.add("А у нас сегодня кошка родила вчера котят. Котята выросли немножко, но есть из блюдца не хотят!");
        listStr.add("А у нас водопровод! Вот!");

        System.out.println("CLIENT");
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String resp = "";
            int i = 0;
            while ((resp = in.readLine()) != null) {
                System.out.println(resp);

                System.out.println("     " + listStr.get(i));
                out.println(listStr.get(i));
                i++;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
