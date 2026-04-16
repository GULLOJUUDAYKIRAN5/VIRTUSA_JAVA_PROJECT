import java.util.*;
interface BankOps {
    void deposit(double amt);
    void withdraw(double amt);
    void checkBalance();
}
abstract class Account implements BankOps {
    private int accNo;
    private String password;
    protected double balance;
    protected ArrayList<String> transactions = new ArrayList<>();

    Account(int accNo, String password, double balance) {
        this.accNo = accNo;
        this.password = password;
        this.balance = balance;
    }
    int getAccNo() {
        return accNo;
    }
    public void checkBalance() {
        System.out.println("Balance amount is: " + balance);
    }
    boolean login(String password) {
        return this.password.equals(password);
    }

    void changePassword(String password) {
        this.password = password;
    }
    void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : transactions) {
                System.out.println(t);
            }
        }
    }
}
class SavingAccount extends Account {
    SavingAccount(int accNo, String password, double balance) {
        super(accNo, password, balance);
    }
    public void deposit(double amt) {
        balance += amt;
        transactions.add("Deposited: " + amt); 
        System.out.println("Amount successfully deposited: " + amt);
    }
    public void withdraw(double amt) {
        if (amt <= balance) {
            balance -= amt;
            transactions.add("Withdrawn: " + amt); 
            System.out.println("Withdrawn: " + amt);
        } else {
            System.out.println("Insufficient Funds");
        }
    }
}


class CurrentAccount extends Account {
    int limit = 20000;
    CurrentAccount(int accNo, String password, double balance) {
        super(accNo, password, balance);
    }
    public void deposit(double amt) {
        balance += amt;
        transactions.add("Deposited: " + amt); // 
        System.out.println("Amount successfully deposited: " + amt);
    }
    public void withdraw(double amt) {
        if (amt > limit) {
            System.out.println("Limit exceeded");
        } else if (amt <= balance) {
            balance -= amt;
            transactions.add("Withdrawn: " + amt); 
            System.out.println("Withdrawn: " + amt);
        } else {
            System.out.println("Insufficient Funds");
        }
    }
}
class Bank {
    static ArrayList<Account> accounts = new ArrayList<>();
    static Account find(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccNo() == accNo)
                return acc;
        }
        return null;
    }
    static int generateCaptcha() {
        return 1000 + new Random().nextInt(9000);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Existing User");
            System.out.println("2. New User");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            if (ch == 3) break;
            if (ch == 2) {
                System.out.print("Enter Account Number: ");
                int accNo = sc.nextInt();
                if (Bank.find(accNo) != null) {
                    System.out.println("Account already exists!");
                    continue;
                }
                sc.nextLine();
                System.out.print("Create Password: ");
                String password = sc.nextLine();
                System.out.print("Re-enter Password: ");
                String repassword = sc.nextLine();
                if (!password.equals(repassword)) {
                    System.out.println("Password mismatch!");
                    continue;
                }
                System.out.print("Initial Balance: ");
                double balance = sc.nextDouble();
                System.out.println("1. Savings Account");
                System.out.println("2. Current Account");
                int type = sc.nextInt();
                Account acc = null;
                if (type == 1) {
                    acc = new SavingAccount(accNo, password, balance);
                } else if (type == 2) {
                    acc = new CurrentAccount(accNo, password, balance);
                }

                Bank.accounts.add(acc);
                System.out.println("Account created successfully!");
            }

            if (ch == 1) {
                System.out.print("Enter Account Number: ");
                int accNo = sc.nextInt();

                Account acc = Bank.find(accNo);

                if (acc == null) {
                    System.out.println("Account not found!");
                    continue;
                }

                sc.nextLine();
                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                if (!acc.login(password)) {
                    System.out.println("Wrong password!");
                    continue;
                }

                int captcha = Bank.generateCaptcha();
                System.out.println("Enter CAPTCHA: " + captcha);
                int userCaptcha = sc.nextInt();

                if (captcha != userCaptcha) {
                    System.out.println("Captcha incorrect!");
                    continue;
                }
                while (true) {
                    System.out.println("\n1.Deposit 2.Withdraw 3.Balance 4.Transfer 5.History 6.Logout");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter amount: ");
                            acc.deposit(sc.nextDouble());
                            break;

                        case 2:
                            System.out.print("Enter amount: ");
                            acc.withdraw(sc.nextDouble());
                            break;

                        case 3:
                            acc.checkBalance();
                            break;

                        
                        case 4:
                            System.out.print("Enter receiver account number: ");
                            int toAccNo = sc.nextInt();
                            Account receiver = Bank.find(toAccNo);

                            if (receiver == null) {
                                System.out.println("Receiver not found!");
                                break;
                            }

                            System.out.print("Enter amount: ");
                            double amt = sc.nextDouble();

                            if (amt <= acc.balance) {
                                acc.withdraw(amt);
                                receiver.deposit(amt);
                                acc.transactions.add("Transferred: " + amt + " to " + toAccNo);
                                receiver.transactions.add("Received: " + amt + " from " + acc.getAccNo());
                                System.out.println("Transfer successful!");
                            } else {
                                System.out.println("Insufficient balance!");
                            }
                            break;

                        
                        case 5:
                            acc.showTransactions();
                            break;

                        case 6:
                            System.out.println("Logged out.");
                            break;
                    }

                    if (choice == 6) break;
                }
            }
        }
        sc.close();
    }
}