package fr.coding.bankaccount.communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final int  NUMBER_OF_THREADS  = 20;

    public static void main(String[] args) throws IOException {
        try (
                ServerSocket listener = new ServerSocket(1234)) {
            System.out.println("Server running at " + new Date());
            final ExecutorService pool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
            while (true) {
                pool.execute(new ATMOperationHandler(listener.accept()));
            }
        }
    }

    private static class ATMOperationHandler implements Runnable {
        private final Socket socket;
        private static AtomicInteger atomicInteger = new AtomicInteger(0);

        public ATMOperationHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Connected: " + socket);
            try {
                final Scanner in = new Scanner(socket.getInputStream());
                final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                while (in.hasNextLine()) {
                    // Server operations !
                    out.println(in.nextLine().toUpperCase());
                    System.out.println("The client made : " + atomicInteger.incrementAndGet() + " request(s)");
                }
            } catch (Exception e) {
                System.out.println("Error:" + socket);
            } finally {
                try { socket.close(); } catch (IOException e) {
                    System.exit(1);
                }
                System.out.println("Closed: " + socket);
            }
        }
    }
}