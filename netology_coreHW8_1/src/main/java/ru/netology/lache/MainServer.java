package ru.netology.lache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainServer {
    public static void main(String[] args) throws IOException {
        int port = 8095;

        List<String> listStr = new ArrayList<>();
        listStr.add("Просто так:");
        listStr.add("А у нас сегодня гость. А у вас?");
        listStr.add("А у нас в квартире газ! А у вас?");
        listStr.add("А из нашего окна площадь Красная видна! А из вашего окошка только улицы немножко.");

        System.out.println("SERVER");

        try (ServerSocket serverSocket = new ServerSocket(port); // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
             Socket clientSocket = serverSocket.accept();  // ждем подключения
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

            System.out.println("New connection accepted");
            String resp = "";
            int i = 0;
            while (resp != null) {
                out.println(listStr.get(i));
                System.out.println("       " + listStr.get(i));

                resp = in.readLine();
                System.out.println(resp);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}