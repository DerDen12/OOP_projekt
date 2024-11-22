package Logic;

public class LoanManagerHandler extends LoanHandler {
    @Override
    public void handleLoan(Loan loan) {
        if (loan.getAmount() > 10000 && loan.getAmount() <= 50000 && loan.getStatus() == LoanStatus.REVIEWED) {
            loan.setStatus(LoanStatus.APPROVED);
            System.out.println("Medium loan approved");
        } else  {
            passToNext(loan);
        }
    }
}

