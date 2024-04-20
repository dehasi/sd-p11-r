package p5_storage.application;

import p5_storage.domain.BankAccountRepository;
import p5_storage.domain.BankAccountService;
import p5_storage.domain.CashMovementsService;
import p5_storage.domain.DomainConfiguration;
import p5_storage.infrastructure.InfrastructureConfiguration;

public class ApplicationConfiguration {
    private static final InfrastructureConfiguration infrastructure = new InfrastructureConfiguration();
    private static final DomainConfiguration domain = new DomainConfiguration();

    public static UserInputController userInputService() {
        BankAccountRepository repository = infrastructure.sqLiteBankAccountRepository();

        BankAccountService bankAccountService = domain.bankAccountService(repository);
        CashMovementsService cashMovementsService = domain.cashMovementsService(repository);

        return new UserInputController(bankAccountService, cashMovementsService);
    }
}
