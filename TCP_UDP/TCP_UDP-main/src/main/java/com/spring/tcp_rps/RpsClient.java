package com.spring.tcp_rps;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class RpsClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to Rock-Paper-Scissors Server.");
            System.out.println("Enter rock, paper, scissors, or quit:");

            while (true) {
                String choice = scanner.nextLine();
                out.println(choice);

                if (choice.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting game.");
                    break;
                }

                String response = in.readLine();
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
