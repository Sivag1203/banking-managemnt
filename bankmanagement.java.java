import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your customer ID: ");
        String customerId = sc.nextLine();
        BankAccount account = new BankAccount(name, customerId);
        while (true) {
            showMenu();
            char option = sc.next().charAt(0);
            switch (option) {
                case 'a':
                    System.out.println("Your balance is " + account.getBalance());
                    break;
                case 'b':
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 'c':
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Withdrawal failed. Insufficient balance.");
                    }
                    break;
                case 'd':
                    System.out.println("Previous transaction: " + account.getPreviousTransaction());
                    break;
                case 'e':
                    System.out.println("Thank you for using our banking services.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("Welcome to our Bank.");
        System.out.println("a. Check Balance");
        System.out.println("b. Deposit");
        System.out.println("c. Withdraw");
        System.out.println("d. Previous transaction");
        System.out.println("e. Exit");
        System.out.print("Please choose an option: ");
    }
}

class BankAccount {
    private double balance;
    private double previousTransaction;
    private String customerName;
    private String customerId;

    public BankAccount(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            previousTransaction = amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            previousTransaction = -amount;
            return true;
        }
        return false;
    }

    public String getPreviousTransaction() {
        if (previousTransaction > 0) {
            return "Deposited: " + previousTransaction;
        } else if (previousTransaction < 0) {
            return "Withdrawn: " + Math.abs(previousTransaction);
        } else {
            return "No transaction occurred.";
        }
    }
}
