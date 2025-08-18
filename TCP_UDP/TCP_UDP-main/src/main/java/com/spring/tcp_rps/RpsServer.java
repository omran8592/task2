package com.spring.tcp_rps;




import java.io.*;
import java.net.*;
import java.util.Random;

public class RpsServer {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("RPS Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new Thread(() -> handleClient(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        String[] choices = {"rock", "paper", "scissors"};
        Random random = new Random();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String clientChoice;
            while ((clientChoice = in.readLine()) != null) {
                if (clientChoice.equalsIgnoreCase("quit")) {
                    System.out.println("Client disconnected: " + socket.getInetAddress());
                    break;
                }

                String serverChoice = choices[random.nextInt(3)];
                String result;

                if (clientChoice.equalsIgnoreCase(serverChoice)) {
                    result = "Draw!";
                } else if ((clientChoice.equalsIgnoreCase("rock") && serverChoice.equals("scissors")) ||
                        (clientChoice.equalsIgnoreCase("paper") && serverChoice.equals("rock")) ||
                        (clientChoice.equalsIgnoreCase("scissors") && serverChoice.equals("paper"))) {
                    result = "You win!";
                } else {
                    result = "You lose!";
                }

                out.println("Server choice: " + serverChoice + " | " + result);
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
