package fr.coding.bankaccount.communication;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.exceptions.NotEnoughMoneyOnAccountException;
import fr.coding.bankaccount.implementations.BeneficiaryOperationImpl;
import fr.coding.bankaccount.implementations.OperationRepositoryImpl;
import fr.coding.bankaccount.exceptions.AccountService;

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
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static final AccountService accountService = new AccountService(new OperationRepositoryImpl(), new BeneficiaryOperationImpl(), new AccountRepositoryImpl() , null);

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
                    final String value = in.nextLine().toUpperCase();
                    out.println(value);
                    final long accountID = 0L;
                    accountService.withdraw(accountID, value);
                    System.out.println("The client made : " + atomicInteger.incrementAndGet() + " request(s)");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error:" + socket);
            } catch (NotEnoughMoneyOnAccountException | AccountNotFoundException | AmountNegativeException e) {
                System.out.println(e.getMessage());
            } finally {
                try { socket.close(); } catch (IOException e) {
                    System.exit(1);
                }
                System.out.println("Closed: " + socket);
            }
        }
    }
}