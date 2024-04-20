package p1_state_based.domain;

public class DomainConfiguration {

    public static BankAccountService bankAccountService(BankAccountRepository repository) {
        return new BankAccountService(repository);
    }
}
