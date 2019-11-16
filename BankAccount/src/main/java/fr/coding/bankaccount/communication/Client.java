package fr.coding.bankaccount.communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 1234;
    private static final String ADRESS = "localhost";

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket(ADRESS, PORT);
        System.out.println("Enter a value for the deposit : ");
        final Scanner scanner = new Scanner(System.in);
        final Scanner in = new Scanner(socket.getInputStream());

        final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        while (scanner.hasNextLine()) {
            out.println(scanner.nextLine());
            System.out.println(in.nextLine());
        }
    }
}
