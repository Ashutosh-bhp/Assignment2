public interface AccountManagement {
    void transferFunds(BankingOperations targetAccount, double amount)throws InsufficientFundsException, InvalidAmountException;
    void displayAccountInfo();
    String getAccountType();
}
