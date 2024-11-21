package Logic;

public abstract class LoanHandler {
    private LoanHandler nextHandler;

    public void setNextHandler(LoanHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleLoan(Loan loan);

    protected void passToNext(Loan loan) {
        if (nextHandler != null) {
            nextHandler.handleLoan(loan);
        }
    }
}

