#🏦 Banking System Simulation (Java)
📌 Project Overview

This project is a Java-based Banking System Simulation that demonstrates core banking operations such as account creation, deposit, withdrawal, money transfer, and transaction tracking.

The system is designed using Object-Oriented Programming (OOP) principles and provides a simple console-based interface for users.

🎯 Objectives
Simulate real-world banking operations
Implement secure user authentication
Manage multiple user accounts
Track transaction history
⚙️ Features
👤 Account Management
Create new account (Savings / Current)
Unique account number for each user
🔐 Authentication
Login using account number and password
CAPTCHA verification for added security
💰 Banking Operations
Deposit money
Withdraw money
Transfer money between accounts
Balance inquiry
📜 Transaction History
Stores all transactions:
Deposits
Withdrawals
Transfers (sent/received)
🧱 Technologies Used
Java (Core Java)
OOP Concepts
Inheritance
Abstraction
Encapsulation
Interfaces
Collections Framework
ArrayList
🏗️ Project Structure
            BankOps (Interface)
               ↓
            Account (Abstract Class)
               ↓
            SavingAccount   CurrentAccount
                    ↓
            Bank (Manages Accounts)
                    ↓
            Main (User Interaction)
🧠 Key Concepts Used
🔹 Interface

Defines banking operations:

deposit()
withdraw()
checkBalance()

🔹 Abstract Class
Common properties like account number, balance, password
Shared functionality across account types
🔹 Inheritance
SavingAccount and CurrentAccount extend Account
🔹 Encapsulation
Sensitive data (password) is private
Access controlled via methods
▶️ How to Run the Program
1. Save the file
Main.java
2. Compile
javac Main.java
3. Run
java Main
