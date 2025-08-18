package com.spring.udp_guess;


import java.net.*;
import java.util.Scanner;

public class GuessClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 6000;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to Guess-the-Number Server.");
            while (true) {
                System.out.print("Enter your guess (1-10): ");
                String guess = scanner.nextLine();

                DatagramPacket packet = new DatagramPacket(
                        guess.getBytes(),
                        guess.getBytes().length,
                        InetAddress.getByName(host),
                        port
                );
                socket.send(packet);

                byte[] buffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);

                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("Server: " + response);

                if (response.contains("Correct")) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
