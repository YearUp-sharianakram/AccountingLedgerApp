
# Accounting Ledger Application

This Java Command Line Interface (CLI) application is designed to track financial transactions for both business and personal use. It allows users to efficiently manage their expenses by recording thier deposits and payments. The application features a 'Ledger' system where users can view all their financial entries with the newest entries displayed first. Users can also generate various reports based on predefined timeframes, such as month-to-date or year-to-date, and even search transactions by vendor.


## Authors

- [@sharianakram](https://www.github.com/sharianakram)


## Screenshots

![Screenshot1](https://github.com/YearUp-sharianakram/AccountingLedgerApp/blob/main/ScreenshotsForCapstone1/Screenshot1.png)

From the Ledger Screen, the user is able to view all previous transaction that are received from the CSV file.

![Screenshot2](https://github.com/YearUp-sharianakram/AccountingLedgerApp/blob/main/ScreenshotsForCapstone1/Screenshot2.png)

From the Home Screen, the user is able to enter deposits or payments. You will be able to view these new entries from the Ledger Screen.

![Screenshot3](https://github.com/YearUp-sharianakram/AccountingLedgerApp/blob/main/ScreenshotsForCapstone1/Screenshot3.png)

From the Reports Screen, the user is able to view reports from different timefreames and search a transaction by vendor. 

## New Feature - Custom Search Functionality

The application now includes a custom search feature that allows users to filter transactions based on various criteria, making it easier to find specific entries. Users can filter their search by start date, end date, description, vendor, minimum amount, or maximum amount.


![Screenshot4](https://github.com/YearUp-sharianakram/AccountingLedgerApp/blob/main/ScreenshotsForCapstone1/Screenshot4.png)

## Interesting Piece of Code

While developing the deposit and payment functionality for this application, I encountered a challenge with rounding double values to two decimal places. Since the built-in Math.round function only rounds to the nearest whole number, I decided to create my own rounding function. This custom solution not only addresses the issue of handling amounts with more than two decimal places but will also serve as a valuable utility for future projects.