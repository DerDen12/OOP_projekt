public class Customer extends Person implements LoanHandler{
    public Customer(int access, String name) {
        super(access, name);
    }

    @Override
    public void handleLoan(Loan loan) {

    }
}
