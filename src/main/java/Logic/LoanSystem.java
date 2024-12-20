package Logic;

import java.util.ArrayList;

public class LoanSystem implements LoanCreation, LoanCalculation {
    private ArrayList<Loan> loans;
    private int loanDuration;
    private LoanHandler loanHandler;
    public LoanSystem() {
        loans = new ArrayList<>();


    }
    @Override
    public int calculateAmountOfMonths(int amount, double interest, int canPay) {
        return (int) Math.ceil((amount * (1 + interest / 100)) / canPay);
    }
    public Loan findLoanByID(int loanID) {
        for (Loan loan : loans) {
            if (loan.getID() == loanID) {
                return loan;
            }
        }
        return null;
    }
    @Override
    public Loan createLoan(int amount, double interest, int accessRequired, int canPay, int ID) {
        loanDuration = calculateAmountOfMonths(amount,interest,canPay);
        Loan newloan = new Loan(amount, loanDuration, interest,canPay, ID);
        loans.add(newloan);
        System.out.println(newloan);

        if (loanHandler == null) {
            System.out.println("loanHandler is null");
        } else {
            System.out.println("Handling loan");
            loanHandler.handleLoan(newloan);
        }

        return newloan;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

}
