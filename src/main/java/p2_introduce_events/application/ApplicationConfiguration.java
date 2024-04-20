package p2_introduce_events.application;

import p2_introduce_events.domain.BankAccountRepository;
import p2_introduce_events.domain.BankAccountService;
import p2_introduce_events.domain.CashMovementsService;
import p2_introduce_events.infrastructure.InMemoryBankAccountRepository;

import static p2_introduce_events.domain.DomainConfiguration.bankAccountService;
import static p2_introduce_events.domain.DomainConfiguration.cashMovementsService;

public class ApplicationConfiguration {

    public static UserInputController userInputService() {
        BankAccountRepository repository = new InMemoryBankAccountRepository();

        BankAccountService bankAccountService = bankAccountService(repository);
        CashMovementsService cashMovementsService = cashMovementsService(repository);

        return new UserInputController(bankAccountService, cashMovementsService);
    }
}
