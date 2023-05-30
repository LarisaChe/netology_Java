package lache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Map;
import java.util.Scanner;

public class MainClient {
    private static final String settingsClientFileName = "settings.txt";

    public static void main(String[] args) throws IOException {

        Settings.checkSettingsFile(settingsClientFileName, "Client");
        Map<String, String> settings = Settings.getSettings(settingsClientFileName);
        String host = settings.get("host");
        int port = Integer.valueOf(settings.get("port"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Представьтесь, пожалуйста: ");
        prompt();
        String login = scanner.nextLine();
        Log log = Log.getInstance();
        log.log(login, "", "Старт");

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println(login);

            Thread threadChat = new Thread(() -> {
                String fromChat = "";
                //while (true) {
                while (!fromChat.equals(Commands.CONNECT_CLOSE.getCommand())) {
                    try {
                        fromChat = in.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println();
                    System.out.println(fromChat);
                    prompt();
                    try {
                        log.log(login, "From Server", fromChat);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            Thread threadInput = new Thread(() -> {
                String msg = "";
                //while (true) {
                while (!msg.equals(Commands.FINISH_WORK.getCommand())) {
                    prompt();
                    msg = scanner.nextLine();
                    out.println(msg);
                    try {
                        log.log(login, login, msg);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    log.log(login, "", "Вы вышли из чата");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            threadChat.start();
            threadInput.start();

            threadInput.join();
            threadChat.join();

        } catch (Exception e) {
            e.getStackTrace();
        }
        log.log(login, "", "Финиш");
    }

    private static void prompt() {
        System.out.print("> ");
    }
}
