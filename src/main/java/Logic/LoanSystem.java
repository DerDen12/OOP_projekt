package Logic;

import java.util.ArrayList;

public class LoanSystem implements LoanHandler {
    private Person person;
    private ArrayList<Loan> loans;

    public LoanSystem(Person person) {
        loans = new ArrayList<>();
        this.person = person;
    }

    @Override
    public void createLoan(int amount, int loanDuration, double interest, int accessRequired, int canPay, int ID) {
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
