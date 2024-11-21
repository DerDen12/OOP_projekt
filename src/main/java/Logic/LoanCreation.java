package Logic;

public interface LoanCreation {
    Loan createLoan(int amount, double interest, int accessRequired, int monthlyPayment, int id);
}