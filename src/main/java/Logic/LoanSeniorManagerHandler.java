package Logic;

public class LoanSeniorManagerHandler extends LoanHandler{
    @Override
    public void handleLoan(Loan loan) {
        if (loan.getAmount() > 50000 && loan.getAmount() <= 100000 && loan.getStatus() == LoanStatus.REVIEWED) {
            loan.setStatus(LoanStatus.SENI_APPROVED);
            System.out.println("Large loan approved by senior manager");
        }
    }
}
