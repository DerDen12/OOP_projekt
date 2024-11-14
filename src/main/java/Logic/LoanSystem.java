package Logic;

public class LoanSystem implements LoanHandler {
    private Person person;

    public LoanSystem(Person person) {
        this.person = person;
    }

    @Override
    public void createLoan(int amount, int loanDuration, double interest, int accessRequired, int canPay, int ID) {
        Loan newloan = new Loan(amount, loanDuration, interest,2,canPay, ID);
        System.out.println(newloan);

    }
    @Override
    public void reviewLoan(Loan loan) {
        loan.setStatus(Status.REVIEWED);

    }

    @Override
    public void approveLoan(Loan loan) {
        loan.setStatus(Status.APPROVED);
    }

    @Override
    public void denyLoan(Loan loan) {
        loan.setStatus(Status.DENIED);
    }

}
