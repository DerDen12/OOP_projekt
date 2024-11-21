package Logic;

public class LoanOfficerHandler extends LoanHandler{
    @Override
    public void handleLoan(Loan loan) {
        if (loan.getAmount() < 10000 && loan.getStatus() == LoanStatus.NEW) {
            loan.setStatus(LoanStatus.APPROVED);
            System.out.println("Small loan approved");
        }   else if (loan.getAmount() >= 10000 || loan.getStatus() == LoanStatus.REVIEWED) {
            passToNext(loan);
        }
    }
}
