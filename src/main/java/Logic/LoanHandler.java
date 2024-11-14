package Logic;

public interface LoanHandler {
    void createLoan(int amount, int loanDuration, double interest, int accessRequired, int monthlyPayment, int id);
    void reviewLoan(Loan loan);
    void approveLoan(Loan loan);
    void denyLoan(Loan loan);
}