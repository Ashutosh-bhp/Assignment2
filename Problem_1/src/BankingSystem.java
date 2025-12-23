import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingSystem {
    private static Map<String, BankAccount> accounts=new HashMap<>();
    private static Scanner sc= new Scanner(System.in);
    private static int accountCounter=1000;
    public static void main(String[] args) {

        System.out.println("\n====WELCOME TO THE SECURE BANKING SYSTEM====");
        boolean running=true;
        while(running){
            displayMainMenu();
            try {
                int choice= Integer.parseInt(sc.nextLine());
                switch (choice){
                    case 1:
                        createBankAccount();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        checkBalance();
                        break;
                    case 5:
                        transferFunds();
                        break;
                    case 6:
                        viewAccountInfo();
                        break;
                    case 7:
                        updateCustomerInfo();
                        break;
                    case 8:
                        listAllAccounts();
                        break;
                    case 9:
                        System.out.println("\n Thank you for using Secure Banking System");
                        System.out.println("  Goodbye!\n");
                        running=false;
                        break;
                    default:
                        System.out.println("\n X invalid input, please try again");
                }
            }catch (Exception e){
                System.out.println("Invalid input!");
            }
        }
    }

    private static void listAllAccounts() {
        System.out.println("\n--- ALL ACCOUNTS ---");

        if (accounts.isEmpty()) {
            System.out.println("No accounts found in the system.");
            return;
        }

        System.out.println("\nTotal Accounts: " + accounts.size());
        System.out.println("─────────────────────────────────────────────────────────────");

        for (BankAccount account : accounts.values()) {
            System.out.printf("%-12s | %-20s | %-15s | $%.2f%n",
                    account.getAccountNumber(),
                    account.getCustomerName(),
                    account.getAccountType(),
                    account.getBalance());
        }
        System.out.println("─────────────────────────────────────────────────────────────");
    }

    private static void updateCustomerInfo() {
        System.out.println("\n--- UPDATE CUSTOMER INFORMATION ---");

        try {
            System.out.print("Enter account number: ");
            String accountNumber = sc.nextLine();

            BankAccount account = getAccount(accountNumber);

            System.out.println("\nWhat would you like to update?");
            System.out.println("1. Customer Name");
            System.out.println("2. Customer Email");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter new customer name: ");
                    String newName = sc.nextLine();
                    account.updateCustomerName(newName);
                    break;
                case 2:
                    System.out.print("Enter new customer email: ");
                    String newEmail = sc.nextLine();
                    account.updateCustomerEmail(newEmail);
                    break;
                default:
                    System.out.println("\n✗ Invalid choice.");
            }

        } catch (AccountNotFoundException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Invalid input. Please enter a number.");
        }
    }

    private static void viewAccountInfo() {
        System.out.println("\n--- VIEW ACCOUNT INFORMATION ---");

        try {
            System.out.print("Enter account number: ");
            String accountNumber = sc.nextLine();

            BankAccount account = getAccount(accountNumber);
            account.displayAccountInfo();

        } catch (AccountNotFoundException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        }
    }

    private static void transferFunds() {
        System.out.println("\n--- TRANSFER FUNDS ---");

        try {
            System.out.print("Enter your account number (from): ");
            String fromAccount = sc.nextLine();

            BankAccount sourceAccount = getAccount(fromAccount);

            System.out.print("Enter recipient account number (to): ");
            String toAccount = sc.nextLine();

            BankAccount targetAccount = getAccount(toAccount);

            System.out.print("Enter transfer amount: $");
            double amount = Double.parseDouble(sc.nextLine());

            sourceAccount.transferFunds(targetAccount, amount);

        } catch (AccountNotFoundException | InsufficientFundsException |
                 InvalidAmountException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Invalid amount format. Please enter a valid number.");
        }
    }

    private static void checkBalance() {
        System.out.println("\n--- CHECK BALANCE ---");

        try {
            System.out.print("Enter account number: ");
            String accountNumber = sc.nextLine();

            BankAccount account = getAccount(accountNumber);

            System.out.println("\nAccount: " + accountNumber);
            System.out.println("Customer: " + account.getCustomerName());
            System.out.println("Current Balance: $" + String.format("%.2f", account.getBalance()));

        } catch (AccountNotFoundException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        }
    }

    private static void withdraw() {
        System.out.println("\n--- WITHDRAW MONEY ---");

        try {
            System.out.print("Enter account number: ");
            String accountNumber = sc.nextLine();

            BankAccount account = getAccount(accountNumber);

            System.out.print("Enter withdrawal amount: $");
            double amount = Double.parseDouble(sc.nextLine());

            account.withdraw(amount);

        } catch (AccountNotFoundException | InsufficientFundsException |
                 InvalidAmountException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Invalid amount format. Please enter a valid number.");
        }
    }

    private static void deposit() {
       try {
           System.out.println("Enter your account number: ");
           String accountNumber=sc.nextLine();
           BankAccount account= getAccount(accountNumber);
           System.out.println("Enter deposit amount: ");
           double amount=Double.parseDouble(sc.nextLine());
           account.deposit(amount);
       } catch (AccountNotFoundException | InvalidAmountException e) {
           System.out.println("\n✗ Error: " + e.getMessage());
       } catch (NumberFormatException e) {
           System.out.println("\n✗ Invalid amount format. Please enter a valid number.");
       }

    }

    private static BankAccount getAccount(String accountNumber) throws AccountNotFoundException{
        BankAccount account= accounts.get(accountNumber);
        if (account==null){
            throw new AccountNotFoundException("Account not Found: "+accountNumber);
        }
        return account;
    }

    private static void createBankAccount() {
        System.out.println("\n====CREATE NEW BANK ACCOUNT====");
        try {
            System.out.println("Enter customer name: ");
            String name=sc.nextLine();
            System.out.println("Enter customer Email: ");
            String email=sc.nextLine();
            System.out.println("Enter account type(Savings/Checking/Business): ");
            String accountType=sc.nextLine();
            System.out.println("Enter initial deposit amount: Rs.");
            double initialAmount=Double.parseDouble(sc.nextLine());
            String accountNumber="ACC"+(accountCounter++);
            BankAccount account=new BankAccount(accountNumber,name,email,initialAmount,accountType);
            accounts.put(accountNumber, account);
            System.out.println("Account created successfully.");
            System.out.println("Your account number is: "+accountNumber);
            System.out.println("Your initial balance is: Rs."+ initialAmount);
        }catch (NumberFormatException e){
            System.out.println("\n X Invalid amount format. Please enter a valid number");
        }
        catch (InvalidAmountException e){
            System.out.println("\n X Error: "+ e.getMessage());
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║            MAIN MENU                      ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ 1. Create New Account                     ║");
        System.out.println("║ 2. Deposit Money                          ║");
        System.out.println("║ 3. Withdraw Money                         ║");
        System.out.println("║ 4. Check Balance                          ║");
        System.out.println("║ 5. Transfer Funds                         ║");
        System.out.println("║ 6. View Account Information               ║");
        System.out.println("║ 7. Update Customer Information            ║");
        System.out.println("║ 8. List All Accounts                      ║");
        System.out.println("║ 9. Exit                                   ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.print("\nEnter your choice: ");
    }

}
