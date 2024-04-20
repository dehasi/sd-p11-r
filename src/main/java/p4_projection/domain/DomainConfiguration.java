package p4_projection.domain;

public class DomainConfiguration {

    public static BankAccountService bankAccountService(BankAccountRepository repository) {
        return new BankAccountService(repository);
    }

    public static CashMovementsService cashMovementsService(BankAccountRepository repository) {
        return new CashMovementsService(repository);
    }

}
