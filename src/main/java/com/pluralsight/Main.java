package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
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
                System.out.println(" (D) - Deposit");
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
                    System.out.println(selection);
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


    public static void displayDeposits(){

    }

    public static void displayPayments(){

    }

    public static void displayAll(){

    }
    /*
    This function will ask the user prompts to get the user deposit information
    after they have chose option D.
     */
    public static void getUserDepositInformation(){

    }

    public static void getUserPaymentInformation(){

    }

    public static void writeToFile(String Description, String vendor, double amount){
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();




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

