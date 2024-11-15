package Logic;

import java.util.ArrayList;

public class Loan {
    private int amount;
    private int loanDuration;
    private double interest;
    private int accessRequired;
    private int canPay;
    private int ID;
    private LoanStatus status;
    private LoanType type;

    public Loan(int amount, int loanDuration, double interest, int accessRequired, int canPay, int ID) {
        this.amount = amount;
        this.loanDuration = loanDuration;
        this.interest = interest;
        this.accessRequired = accessRequired;
        this.canPay = canPay;
        this.ID = ID;
        this.status = LoanStatus.NEW;
        this.type = determineType(amount);
    }

    private LoanType determineType(int amount) {
        if (amount < 10000) {
            return LoanType.SMALL;
        } else if (amount <= 50000) {
            return LoanType.MEDIUM;
        } else {
            return LoanType.LARGE;
        }
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

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "amount= " + amount + " CZK" +
                ", loanDuration= " + loanDuration + " months" +
                ", interest= " + interest + " %" +
                ", accessRequired= " + accessRequired +
                ", canPay= " + canPay +
                ",Loan ID= " + ID +
                '}';
    }
}
