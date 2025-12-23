public interface BankingOperations {
    void deposit(double Amount) throws InvalidAmountException;
    void withdraw(double Amount) throws InsufficientFundsException, InvalidAmountException;
    double getBalance();
    String getAccountNumber();
}
