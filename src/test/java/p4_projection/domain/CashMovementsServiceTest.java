package p4_projection.domain;

import org.junit.jupiter.api.Test;
import p4_projection.infrastructure.InMemoryBankAccountRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static p4_projection.domain.DomainConfiguration.bankAccountService;
import static p4_projection.domain.DomainConfiguration.cashMovementsService;

class CashMovementsServiceTest {

    final InMemoryBankAccountRepository repository = new InMemoryBankAccountRepository();
    final BankAccountService bankAccountService = bankAccountService(repository);
    final CashMovementsService cashMovementsService = cashMovementsService(repository);

    @Test void history() {
        int id = 42;
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

    @Test void summary() {
        int id = 42;
        bankAccountService.create(id);
        bankAccountService.deposit(id, 50);
        bankAccountService.withdraw(id, 9);
        bankAccountService.fee(id, 4);

        String summary = cashMovementsService.summary(id);

        assertThat(summary).contains("id: 42");
        assertThat(summary).contains("total deposited: $50");
        assertThat(summary).contains("total withdrew: $9");
        assertThat(summary).contains("total fees: $5");
    }
}