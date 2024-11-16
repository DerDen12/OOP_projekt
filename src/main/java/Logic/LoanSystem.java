package Logic;

import java.util.ArrayList;

public class LoanSystem implements LoanHandler, LoanCalculation {
    private Person person;
    private ArrayList<Loan> loans;
    private int loanDuration;
    public LoanSystem(Person person) {
        loans = new ArrayList<>();
        this.person = person;
    }
    @Override
    public int calculateAmountOfMonths(int amount, double interest, int canPay) {
        return (int) Math.ceil((amount * (1 + interest / 100)) / canPay);
    }
    @Override
    public void createLoan(int amount, double interest, int accessRequired, int canPay, int ID) {
        loanDuration = calculateAmountOfMonths(amount,interest,canPay);
        Loan newloan = new Loan(amount, loanDuration, interest,2,canPay, ID);
        loans.add(newloan);
        System.out.println(newloan);

    }
    @Override
    public void reviewLoan(Loan loan) {
        loan.setStatus(LoanStatus.REVIEWED);

    }

    @Override
    public void approveLoan(Loan loan) {
        loan.setStatus(LoanStatus.APPROVED);
    }

    @Override
    public void denyLoan(Loan loan) {
        loan.setStatus(LoanStatus.DENIED);
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

}
