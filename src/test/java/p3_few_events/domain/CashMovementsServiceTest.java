package p3_few_events.domain;

import org.junit.jupiter.api.Test;
import p3_few_events.infrastructure.InMemoryBankAccountRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static p3_few_events.domain.DomainConfiguration.bankAccountService;
import static p3_few_events.domain.DomainConfiguration.cashMovementsService;

class CashMovementsServiceTest {

    final InMemoryBankAccountRepository repository = new InMemoryBankAccountRepository();
    final BankAccountService bankAccountService = bankAccountService(repository);
    final CashMovementsService cashMovementsService = cashMovementsService(repository);

    @Test void history() {
        final int id = 42;
        bankAccountService.create(id);
        bankAccountService.deposit(id, 50);
        bankAccountService.withdraw(id, 9);
        bankAccountService.fee(id, 4);

        List<String> history = cashMovementsService.history(id);

        assertThat(history).containsExactly(
                "created, id:42",
                "deposited: $50",
                "fee: $1",
                "withdrew: $9",
                "fee: $4");
    }
}
