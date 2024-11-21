package Logic;

public class LoanOwnerHandler extends LoanHandler{
    @Override
    public void handleLoan(Loan loan) {
        System.out.println("Owner Handling loan with amount: " + loan.getAmount() + " and status: " + loan.getStatus());
        if (loan.getAmount() > 50000 && loan.getStatus() == LoanStatus.REVIEWED) {
            loan.setStatus(LoanStatus.APPROVED);
            System.out.println("Large loan approved");
        } else {
            System.out.println("požadavek nedosažen");
        }
    }
}
