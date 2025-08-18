package com.spring;

import com.spring.tcp_rps.RpsClient;
import com.spring.tcp_rps.RpsServer;
import com.spring.udp_guess.GuessClient;
import com.spring.udp_guess.GuessServer;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== Socket Games Project ======");
        System.out.println("اختر اللعبة:");
        System.out.println("1) Rock-Paper-Scissors (TCP)");
        System.out.println("2) Guess the Number (UDP)");
        System.out.print("اختيارك: ");
        int gameChoice = scanner.nextInt();

        System.out.println("\nاختر الوضع:");
        System.out.println("1) Server");
        System.out.println("2) Client");
        System.out.print("اختيارك: ");
        int modeChoice = scanner.nextInt();

        switch (gameChoice) {
            case 1:
                if (modeChoice == 1) {
                    RpsServer.main(null);
                } else {
                    RpsClient.main(null);
                }
                break;
            case 2:
                if (modeChoice == 1) {
                    GuessServer.main(null);
                } else {
                    GuessClient.main(null);
                }
                break;
            default:
                System.out.println("اختيار غير صحيح!");
        }
    }
}
