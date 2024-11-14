package Logic;

public interface LoanHandler {
    void createLoan(Loan loan);
    void reviewLoan(Loan loan);
    void processLoan(Loan loan);
    void denyLoan(Loan loan);
}