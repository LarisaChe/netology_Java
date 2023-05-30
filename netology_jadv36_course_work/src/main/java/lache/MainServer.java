package lache;


import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MainServer {

    private static final String settingsServerFileName = "settingsServer.txt";
    public static List<ThreadsSockets> threadsSockets = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Settings.checkSettingsFile(settingsServerFileName, "Server");
        Map<String, String> settings = Settings.getSettings(settingsServerFileName);
        int port = Integer.valueOf(settings.get("port"));

        System.out.println("SERVER ждет клиентов ");
        Log log = Log.getInstance();
        log.log("Server", "", "SERVER ждет клиентов ");

        ServerSocket serverSocket = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();  // ждем подключения
                try {
                    threadsSockets.add(new ThreadsSockets(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            log.log("Server", "", "SERVER завершил работу ");
            serverSocket.close();
        }
    }

}