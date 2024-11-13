package Logic;

public class System implements LoanHandler {
    private Person person;

    public System(Person person) {
        this.person = person;
    }

    @Override
    public void processLoan(Loan loan) {
        if(person.getAccess() ==

    }

}
