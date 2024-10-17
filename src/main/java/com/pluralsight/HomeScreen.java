package com.pluralsight;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class HomeScreen {
    public final static String dataFileName = "transactions.csv";
    public static ArrayList<Transaction> allTransactions = getTransactions();

    public static void main(String[] args) {
       homeScreen();

//        displayAll();
//        displayPayments();
//        displayDeposits();
//        reportsSearchByVendor();
//        reportsPreviousYear();
//        reportsYearToDate();
//        reportsPreviousMonth();
//        reportsMonthToDate();

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
                System.out.println("Welcome to the Accounting Ledger App!\n");
                System.out.println("How may we help you today?");
                System.out.println(" (D)- Make a Deposit");
                System.out.println(" (P)- Make a Payment");
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
                    LedgerScreen.displayScreen();
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



    /*
    This function will ask the user prompts to get the user deposit information
    after they have chose option D.
     */
    public static void getUserDepositInformation(){
        System.out.println("Please fill out the following information to continue with your deposit: ");
        String description  = Console.PromptForString("Please give a Description: ");
        String vendor = Console.PromptForString("Who is the vendor?");
        double amount;
        do {
            amount = personalUtil.roundDouble(Console.PromptForDouble("How much?"), 2);
        }while(amount == 0);

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = (LocalTime.now()).withNano(0);

        Transaction transaction = new Transaction(currentDate, currentTime, description, vendor, amount);
        allTransactions.add(transaction);
        writeToFile(transaction);

    }

    /*
    This will receive the payment information from the user, it will make sure that the payment is negative
     */
    public static void getUserPaymentInformation(){

        String description  = Console.PromptForString("Please give a Description: ");
        String vendor = Console.PromptForString("Who is the vendor?");
        double amount;
        do {
            amount = personalUtil.roundDouble(Console.PromptForDouble("How much?"), 2);
        }while(amount == 0);

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now().withNano(0);

        Transaction transaction = new Transaction(currentDate, currentTime, description, vendor, amount);
        allTransactions.add(transaction);
        writeToFile(transaction);
    }

    /*
    This function will be used to write to the csv file whenever the user wants to deposit or make a payment
     */
    public static void writeToFile(Transaction transaction){
        try{
            FileWriter fileWriter = new FileWriter(dataFileName, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write("\n" + transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() +
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
        }
        return transactions;
    }

}




