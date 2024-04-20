package p1_state_based.application;

import p1_state_based.domain.BankAccountService;
import p1_state_based.infrastructure.InMemoryBankAccountRepository;

import static p1_state_based.domain.DomainConfiguration.bankAccountService;

public class ApplicationConfiguration {

    public static UserInputController userInputService() {
        BankAccountService bankAccountService = bankAccountService(new InMemoryBankAccountRepository());

        return new UserInputController(bankAccountService);
    }
}
