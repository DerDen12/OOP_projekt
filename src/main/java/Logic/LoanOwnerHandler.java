package Logic;

public class LoanOwnerHandler extends LoanHandler{
    @Override
    public void handleLoan(Loan loan) {
        if (loan.getAmount() > 50000 && loan.getStatus() == LoanStatus.REVIEWED) {
            loan.setStatus(LoanStatus.APPROVED);
            System.out.println("Large loan approved");
        }
    }
}
