package lache.store;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        CommunicationWithUser.greeting(null);
        Scanner scanner = new Scanner(System.in);

        String userName = scanner.nextLine();
        User user = CommunicationWithUser.greeting(userName);

        CommunicationWithUser.MENU.printBriefMenu();
        boolean flag = true;
        int prevMenuItem = 0;
        while (flag) {
            String s = scanner.nextLine().trim();
            flag = CommunicationWithUser.selectMenu(s, prevMenuItem, user);
            System.out.println(user);
            prevMenuItem = Integer.valueOf(s.substring(0, 1));
        }
        CommunicationWithUser.farewell(userName);
    }
}