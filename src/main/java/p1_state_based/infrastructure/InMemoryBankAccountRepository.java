package p1_state_based.infrastructure;

import p1_state_based.domain.BankAccount;
import p1_state_based.domain.BankAccountRepository;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class InMemoryBankAccountRepository implements BankAccountRepository {

    private final Map<Integer, BankAccount> database = new HashMap<>();

    @Override public void save(BankAccount bankAccount) {
        database.put(bankAccount.id, bankAccount);
    }

    @Override public BankAccount findById(int id) {
        return requireNonNull(database.get(id), "BankAccount with id " + id + " is not found.");
    }
}
