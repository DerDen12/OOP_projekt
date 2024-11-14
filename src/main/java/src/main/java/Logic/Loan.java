package Logic;

public class Loan {
    private int amount;
    private int loanDuration;
    private double interest;
    private int accessRequired;
    private int canPay;
    private int ID;

    public Loan(int amount, int loanDuration, double interest, int accessRequired, int canPay, int ID) {
        this.amount = amount;
        this.loanDuration = loanDuration;
        this.interest = interest;
        this.accessRequired = accessRequired;
        this.canPay = canPay;
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

    public int getCanPay() {
        return canPay;
    }

    public void setCanPay(int canPay) {
        this.canPay = canPay;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "amount=" + amount +
                ", loanDuration=" + loanDuration +
                ", interest=" + interest +
                ", accessRequired=" + accessRequired +
                ", canPay=" + canPay +
                ", ID=" + ID +
                '}';
    }
}
