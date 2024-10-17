package com.pluralsight;

import java.util.ArrayList;
import java.util.Comparator;

public class LedgerScreen
{
    public static void displayScreen(){
        while(true){
            try{
                System.out.println("\nWhich previous transactions would you like to view?");
                System.out.println(" (A)- All");
                System.out.println(" (D)- Deposits");
                System.out.println(" (P)- Payments");
                System.out.println(" (R)- Reports");
                System.out.println(" (H)- Go to Home Screen");

                String selection = Console.PromptForString("Enter command:");

                HomeScreen.allTransactions.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());

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
                    ReportsScreen.reportsScreen();
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


        personalUtil.displayFormat("All Entries", HomeScreen.allTransactions);


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
