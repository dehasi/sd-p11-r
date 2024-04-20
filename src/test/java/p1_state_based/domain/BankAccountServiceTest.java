package p1_state_based.domain;

import org.junit.jupiter.api.Test;
import p1_state_based.infrastructure.InMemoryBankAccountRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static p1_state_based.domain.DomainConfiguration.bankAccountService;

class BankAccountServiceTest {

    final BankAccountService bankAccountService = bankAccountService(new InMemoryBankAccountRepository());

    @Test void create_createsAccountWithZeroBalance() {
        int id = 42;
        // assert an account does not exist
        assertThatThrownBy(() -> bankAccountService.balance(id)).hasMessage("BankAccount with id %s is not found.", id);

        bankAccountService.create(id);

        int balance = bankAccountService.balance(id);
        assertThat(balance).isZero();

    }

    @Test void deposit_addsAmountToBalance() {
        int id = 42;
        bankAccountService.create(id);
        int balance = bankAccountService.balance(id);

        int amount = 5;
        bankAccountService.deposit(id, amount);

        int newBalance = bankAccountService.balance(id);
        assertThat(newBalance).isEqualTo(balance + amount);
    }

    @Test void withdraw() {
        int id = 42;
        bankAccountService.create(id);
        bankAccountService.deposit(id, randomAmountAbove(5));
        int balance = bankAccountService.balance(id);

        int amount = 5;
        bankAccountService.withdraw(id, amount);

        int newBalance = bankAccountService.balance(id);
        assertThat(newBalance).isEqualTo(balance - amount);
    }

    @Test void fee() {
        int id = 42;
        bankAccountService.create(id);
        bankAccountService.deposit(id, randomAmountAbove(5));
        int balance = bankAccountService.balance(id);

        int amount = 5;
        bankAccountService.fee(id, amount);

        int newBalance = bankAccountService.balance(id);
        assertThat(newBalance).isEqualTo(balance - amount);
    }

    private static int randomAmountAbove(int threshold) {
        return threshold + 4;
    }
}
