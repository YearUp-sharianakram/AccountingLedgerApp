package com.pluralsight;

import java.util.ArrayList;

public class personalUtil {
    public static void displayFormat(String header, ArrayList<Transaction> transactionsList){

        System.out.println();
        System.out.println(header + ":");
        System.out.printf("| %-12s | %-10s | %-45s | %-20s | %-8s  |\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("|" + "-".repeat(14) + "|" + "-".repeat(12) + "|" + "-".repeat(47) + "|" + "-".repeat(22) + "|" + "-".repeat(11) + "|");


        for (Transaction currTransaction : transactionsList){
            System.out.printf("| %-12s | %-10s | %-45s | %-20s | $%-8.2f |\n", currTransaction.getDate(), currTransaction.getTime(), currTransaction.getDescription(), currTransaction.getVendor(), currTransaction.getAmount());
        }
        System.out.println();
    }

    public static double roundDouble(double input, int numberOfPlaces){
        double multiplier = Math.pow(10, numberOfPlaces);
        double output = Math.round( input * multiplier);
        output = output / multiplier;
        return output;
    }
}
