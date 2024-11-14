package Logic;

public class Loan {
    private int amount;
    private int loanDuration;
    private double interest;
    private int accessRequired;
    private int ID;

    public Loan(int amount, int loanDuration, double interest, int accessRequired, int ID) {
        this.amount = amount;
        this.loanDuration = loanDuration;
        this.interest = interest;
        this.accessRequired = accessRequired;
        this.ID = ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getAccessRequired() {
        return accessRequired;
    }

    public void setAccessRequired(int accessRequired) {
        this.accessRequired = accessRequired;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
