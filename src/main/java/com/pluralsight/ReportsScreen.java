package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class ReportsScreen {
    public static void reportsScreen(){

        while(true){
            try{
                System.out.println("\nWhich reports would you like to view?");
                System.out.println(" (1)- Month to Date");
                System.out.println(" (2)- Previous Month");
                System.out.println(" (3)- Year To Date");
                System.out.println(" (4)- Previous Year");
                System.out.println(" (5)- Search by Vendor");
                System.out.println(" (0)- Go back to Ledger Screen");

                String selection = Console.PromptForString("Enter command:");
                HomeScreen.allTransactions.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());
                if(selection.equals("1")){
                    reportsMonthToDate();
                }
                else if (selection.equals("2")){
                    reportsPreviousMonth();
                }
                else if (selection.equals("3")){
                    reportsYearToDate();
                }
                else if (selection.equals("4")){
                    reportsPreviousYear();
                }
                else if (selection.equals("5")){
                    reportsSearchByVendor();
                }
                else if (selection.equalsIgnoreCase("0")){
                    System.out.println("Going Back to Ledger Screen");
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

    //  This function will display all transaction from the current month so far
    public static void reportsMonthToDate(){
        ArrayList<Transaction> currList = new ArrayList<Transaction>();

        int currentMonthNumber = LocalDate.now().getMonthValue();
        for(Transaction transaction : HomeScreen.allTransactions){
            LocalDate transactionDate = transaction.getDate();
            if (currentMonthNumber == transactionDate.getMonthValue()){
                currList.add(transaction);
            }
        }
        personalUtil.displayFormat("Current Month Report", currList);
    }
    //  This function will display all transactions from the previous month
    public static void reportsPreviousMonth(){
        ArrayList<Transaction> currList = new ArrayList<Transaction>();

        LocalDate current = LocalDate.now();
        int previousMonthNumber = (current.getMonthValue()) - 1;
        for(Transaction transaction : HomeScreen.allTransactions){
            LocalDate transactionDate = transaction.getDate();
            if ((previousMonthNumber == transactionDate.getMonthValue()) && (current.getYear() == transactionDate.getYear())){
                currList.add(transaction);
            }
        }
        personalUtil.displayFormat("Previous Month Reports", currList);
    }
    //  This function will display all transaction from the current year so far
    public static void reportsYearToDate(){
        ArrayList<Transaction> currList = new ArrayList<Transaction>();

        int currentYearNumber = (LocalDate.now().getYear()) ;
        for(Transaction transaction : HomeScreen.allTransactions){
            LocalDate transactionDate = transaction.getDate();
            if (currentYearNumber == transactionDate.getYear()){
                currList.add(transaction);
            }
        }
        personalUtil.displayFormat("Current Year Report", currList);
    }
    //  This function will display all transactions from the previous year
    public static void reportsPreviousYear(){
        ArrayList<Transaction> currList = new ArrayList<Transaction>();

        int previousYearNumber = (LocalDate.now().getYear()) - 1;
        for(Transaction transaction : HomeScreen.allTransactions){
            LocalDate transactionDate = transaction.getDate();
            if (previousYearNumber == transactionDate.getYear()){
                currList.add(transaction);
            }
        }
        personalUtil.displayFormat("Previous Year Report", currList);
    }
    //  This function will display all transactions from the vendor that the user request
    public static void reportsSearchByVendor(){
        ArrayList<Transaction> currList = new ArrayList<Transaction>();

        String vendorName = Console.PromptForString("Name of Vendor: ");
        for(Transaction transaction : HomeScreen.allTransactions){
            if (vendorName.equals(transaction.getVendor())){
                currList.add(transaction);
            }
        }
        personalUtil.displayFormat("Reports from " + vendorName, currList);
    }




}
