package server;

import java.util.Scanner;

public class Main {
    static ProxyDatabase database = new ProxyDatabase();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            String action = scanner.next();

            if (action.equals("exit")) {return;}

            System.out.println(switch (action) {
                case "get" -> database.get(scanner.nextInt());
                case "set" -> database.set(scanner.nextInt(), scanner.nextLine());
                case "delete" -> database.delete(scanner.nextInt());
                default -> "ERROR";
            });
        }
    }
}
