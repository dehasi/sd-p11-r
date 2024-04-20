package p5_storage.domain;

public class DomainConfiguration {

    public BankAccountService bankAccountService(BankAccountRepository repository) {
        return new BankAccountService(repository);
    }

    public CashMovementsService cashMovementsService(BankAccountRepository repository) {
        return new CashMovementsService(repository);
    }

}
