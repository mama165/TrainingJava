package fr.coding.bankaccount.communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket("localhost", 1234);
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
