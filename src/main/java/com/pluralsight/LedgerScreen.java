package com.pluralsight;

import java.util.ArrayList;

public class LedgerScreen
{
    public static void displayScreen(){
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

    /*
This function will display all entries from the Ledger screen if user enters A
 */
    public static void displayAll(){

        ArrayList<Transaction> currList = new ArrayList<Transaction>();

        for (Transaction currTransaction : HomeScreen.allTransactions){
            currList.add(currTransaction);
        }
        personalUtil.displayFormat("All Entries", currList);


    }

        /*
    This function will display all payment entries from the Ledger screen if user enters P
     */

    public static void displayPayments(){
        ArrayList<Transaction> currList = new ArrayList<Transaction>();

        for (Transaction currTransaction : HomeScreen.allTransactions){
            if(currTransaction.getAmount() < 0) {
                currList.add(currTransaction);
            }
        }
        personalUtil.displayFormat("All Payments", currList);
    }

    /*
This function will display all deposit entries from the Ledger screen if user enters D
 */
    public static void displayDeposits(){
        ArrayList<Transaction> currList = new ArrayList<Transaction>();

        for (Transaction currTransaction : HomeScreen.allTransactions){
            if(currTransaction.getAmount() > 0) {
                currList.add(currTransaction);
            }
        }
        personalUtil.displayFormat("All Deposits", currList);
    }

}
