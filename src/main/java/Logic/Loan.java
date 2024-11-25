package Logic;

import java.util.ArrayList;

public class Loan {
    private int amount;
    private int loanDuration;
    private double interest;
    private int canPay;
    private int ID;
    private LoanStatus status;
    private LoanType type;

    public Loan(int amount, int loanDuration, double interest, int canPay, int ID) {
        this.amount = amount;
        this.loanDuration = loanDuration;
        this.interest = interest;
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
    public int getLoanDuration() {
        return loanDuration;
    }
    public double getInterest() {
        return interest;
    }
    public int getCanPay() {
        return canPay;
    }
    public int getID() {
        return ID;
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
    @Override
    public String toString() {
        return "Loan{" +
                "amount= " + amount + " CZK" +
                ", loanDuration= " + loanDuration + " months" +
                ", interest= " + interest + " %" +
                ", canPay= " + canPay +
                ",Loan ID= " + ID +
                '}';
    }
}
