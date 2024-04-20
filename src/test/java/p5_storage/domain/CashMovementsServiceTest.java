package p5_storage.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CashMovementsServiceTest {

    private static final TestInfrastuctureConfiguration infrastructure = new TestInfrastuctureConfiguration();
    private static final DomainConfiguration domain = new DomainConfiguration();

    private final BankAccountRepository repository = infrastructure.sqLiteBankAccountRepository();
    private final BankAccountService bankAccountService = domain.bankAccountService(repository);
    private final CashMovementsService cashMovementsService = domain.cashMovementsService(repository);


    @BeforeEach void cleanupDatabase() {
        var jdbcTemplate = infrastructure.jdbcTemplate();
        jdbcTemplate.update("DELETE FROM events", Map.of());
    }

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