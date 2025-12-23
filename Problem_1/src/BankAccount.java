class BankAccount implements BankingOperations, CustomerOperations, AccountManagement{
    private String accountNumber;
    private String customerName;
    private String customerEmail;
    double balance;
    private String accountType;
    public BankAccount(String accountNumber, String customerName, String customerEmail, double initialBalance, String accountType) throws InvalidAmountException{
        if(initialBalance<0){
            throw new InvalidAmountException("Initial balance cannot be negative");
        }
        this.accountNumber=accountNumber;
        this.customerName=customerName;
        this.customerEmail=customerEmail;
        this.balance=initialBalance;
        this.accountType=accountType;
    }

    @Override
    public void deposit(double Amount) throws InvalidAmountException {
        if(Amount<=0){
            throw new InvalidAmountException("Deposit Amount must be positive");
        }
        balance+=Amount;
        System.out.println("Deposited: Rs."+ Amount+"| New Balance: Rs. "+balance);
    }

    @Override
    public void withdraw(double Amount) throws InsufficientFundsException, InvalidAmountException {
        if(Amount<=0){
            throw new InvalidAmountException("Withdrawal Amount must be positive");
        }
        if(Amount>balance){
            throw new InsufficientFundsException("Insufficient funds. Available balance : Rs."+balance+"Requested amount: "+Amount);
        }
        balance-=Amount;
        System.out.println("Withdrawn Rs."+Amount+"New Balance: Rs."+balance);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public void updateCustomerName(String name) {
        this.customerName=name;
        System.out.println("Customer name updated to: "+name);
    }

    @Override
    public String getCustomerEmail() {
        return customerEmail;
    }

    @Override
    public void updateCustomerEmail(String email) {
        this.customerEmail=email;
        System.out.println("Customer Email updated to: "+email);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("\n====Account Information====");
        System.out.println("Account Number: "+accountNumber);
        System.out.println("Account Type: "+accountType);
        System.out.println("Customer Name: "+customerName);
        System.out.println("Customer Email: "+customerEmail);
        System.out.println("Current Balance: "+balance);
        System.out.println("==========================");
    }

    @Override
    public void transferFunds(BankingOperations targetAccount, double amount) throws InsufficientFundsException, InvalidAmountException {
        System.out.println("\n----Transfer initiated----");
        this.withdraw(amount);
        targetAccount.deposit(amount);
        System.out.println("Transfer Successful: Rs."+amount+"to account "+targetAccount.getAccountNumber());

    }

    @Override
    public String getAccountType() {
        return accountType;
    }
}
