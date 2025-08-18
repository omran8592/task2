package com.spring.udp_guess;


import java.net.*;
import java.util.Random;

public class GuessServer {
    public static void main(String[] args) {
        int port = 6000;
        Random random = new Random();
        int numberToGuess = random.nextInt(10) + 1;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Guess Server started on port " + port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                System.out.println("Received packet from: " + packet.getAddress() + ":" + packet.getPort());

                String guessStr = new String(packet.getData(), 0, packet.getLength());
                int guess = Integer.parseInt(guessStr);
                String response;

                if (guess > numberToGuess) {
                    response = "Too high!";
                } else if (guess < numberToGuess) {
                    response = "Too low!";
                } else {
                    response = "Correct! You win!";

                    numberToGuess = new Random().nextInt(10) + 1;
                    System.out.println("Client guessed correctly! New number generated.");
                }

                DatagramPacket responsePacket = new DatagramPacket(
                        response.getBytes(),
                        response.getBytes().length,
                        packet.getAddress(),
                        packet.getPort()
                );
                socket.send(responsePacket);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

