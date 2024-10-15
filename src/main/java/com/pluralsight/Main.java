package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public final static String dataFileName = "transactions.csv";
    public static ArrayList<Transaction> allTransactions = getTransactions();

    public static void main(String[] args) {
        homeScreen();
    }


    /* This function will be used to prompt the user whether they would like to deposit, make Payment, or display Ledger
        (D) or Add Deposit - will lead to function to deposit information and write to csv
        (P) or Make Payment - will lead to function to debit information and save to cse file
        (L) Ledger - lead to a function to Display all date
        (X) Exit - print a quote informing the user that they have exited the program
     */
    public static void homeScreen(){
        while(true){
            try{

                System.out.println("What do you want to do?");
                System.out.println(" (D)- Deposit");
                System.out.println(" (P)- Payment");
                System.out.println(" (L)- Ledger Display");
                System.out.println(" (X)- Exit Application");

                String selection = Console.PromptForString("Enter command:");

                if(selection.equalsIgnoreCase("D")){
                    getUserDepositInformation();
                }
                else if (selection.equalsIgnoreCase("P")){
                    getUserPaymentInformation();
                }
                else if (selection.equalsIgnoreCase("L")){
                    LedgerDisplayScreen();
                }
                else if (selection.equalsIgnoreCase("X")){
                    System.out.println("Thank you for using the Accounting Ledger App! :D");
                    return;
                }
                else{
                    System.out.println("Invalid Command");
                }
            }
            catch(Exception e){
                System.out.println("Invalid Command");
            }
        }

    }


    public static void LedgerDisplayScreen(){
        while(true){
            try{
                System.out.println("Which previous transactions would you like to view?");
                System.out.println(" (A)- All");
                System.out.println(" (D)- Deposits");
                System.out.println(" (P)- Payments");
                System.out.println(" (R)- Reports");
                System.out.println(" (H)- Go to Home Screen");

                String selection = Console.PromptForString("Enter command:");

                if(selection.equalsIgnoreCase("A")){
                    displayAll();
                }
                else if (selection.equalsIgnoreCase("D")){
                    displayDeposits();
                }
                else if (selection.equalsIgnoreCase("P")){
                    displayPayments();
                }
                else if (selection.equalsIgnoreCase("R")){
                    reportsScreen();
                }
                else if (selection.equalsIgnoreCase("H")){
                    return;
                }
                else{
                    System.out.println("Invalid Command");
                }
            }
            catch(Exception e){
                System.out.println("Invalid Command");
            }
        }

    }

    public static void reportsScreen(){

        while(true){
            try{

                System.out.println("Which reports would you like to view?");
                System.out.println(" (1)- Month to Date");
                System.out.println(" (2)- Previous Month");
                System.out.println(" (3)- Year To Date");
                System.out.println(" (4)- Previous Year");
                System.out.println(" (5)- Exit Application");
                System.out.println(" (0)- Exit Application");

                String selection = Console.PromptForString("Enter command:");

                if(selection.equalsIgnoreCase("D")){
                    getUserDepositInformation();
                }
                else if (selection.equalsIgnoreCase("P")){
                    getUserPaymentInformation();
                }
                else if (selection.equalsIgnoreCase("L")){
                    LedgerDisplayScreen();
                }
                else if (selection.equalsIgnoreCase("B")){
                    System.out.println("Going Back to Reports Screen");
                    return;
                }
                else{
                    System.out.println("Invalid Command");
                }
            }
            catch(Exception e){
                System.out.println("Invalid Command");
            }
        }

    }

    public static void displayDeposits(){
        for (Transaction currTransaction : allTransactions){
            if(currTransaction.getAmount() > 0) {
                currTransaction.displayTransaction();
            }
        }
    }

    public static void displayPayments(){
        for (Transaction currTransaction : allTransactions){
            if(currTransaction.getAmount() < 0) {
                currTransaction.displayTransaction();
            }
        }
    }

    public static void displayAll(){
        for (Transaction currTransaction : allTransactions){
            currTransaction.displayTransaction();
        }
    }
    /*
    This function will ask the user prompts to get the user deposit information
    after they have chose option D.
     */
    public static void getUserDepositInformation(){
        String description  = Console.PromptForString("Please give a Description: ");
        String vendor = Console.PromptForString("Who is the vendor?");
        double amount  = Console.PromptForDouble("How much?");
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        Transaction transaction = new Transaction(currentDate,currentTime, description,vendor,amount);
        allTransactions.add(transaction);
        writeToFile(transaction);

    }

    public static void getUserPaymentInformation(){


        String description  = Console.PromptForString("Please give a Description: ");
        String vendor = Console.PromptForString("Who is the vendor?");
        double amount  = Console.PromptForDouble("How much?");

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        Transaction transaction = new Transaction(currentDate,currentTime, description,vendor,amount);
        allTransactions.add(transaction);
        writeToFile(transaction);

    }

    public static void writeToFile(Transaction transaction){


        try{
            FileWriter fileWriter = new FileWriter(dataFileName, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            String currentTime = (transaction.getTime()).format(DateTimeFormatter.ofPattern("HH:mm:ss"));



            bw.write("\n" + transaction.getDate() + "|" + currentTime + "|" + transaction.getDescription() +
                    "|" + transaction.getVendor() + "|" + transaction.getAmount());



            bw.close();
        } catch (Exception e) {
            System.out.println("FILE WRITE ERROR");
        }

    }


    public static ArrayList<Transaction> getTransactions(){
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try{
            BufferedReader br = new BufferedReader( new FileReader(dataFileName));

            String input;
            br.readLine();
            while( (input = br.readLine()) != null){
                String[] tokens = input.split(Pattern.quote("|"));
                LocalDate date =  LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1]);
                String description = tokens[2];
                String vendor  =  tokens[3];
                double amount = Double.parseDouble(tokens[4]);
                Transaction current = new Transaction(date,time,description,vendor,amount);
                transactions.add(current);

            }
            br.close();

        }
        catch(Exception e){
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
        return transactions;
    }

}




