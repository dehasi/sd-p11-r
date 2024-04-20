package p5_storage.infrastructure;

import p5_storage.domain.BankAccount;
import p5_storage.domain.BankAccountRepository;
import p5_storage.domain.events.DomainEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class InMemoryBankAccountRepository implements BankAccountRepository {

    private final Map<Integer, List<DomainEvent>> database = new HashMap<>();

    @Override public void save(BankAccount bankAccount) {
        database.putIfAbsent(bankAccount.id, new ArrayList<>());
        database.get(bankAccount.id).addAll(bankAccount.events);
    }

    @Override public BankAccount findById(int id) {
        List<DomainEvent> domainEvents = events(id);
        return new BankAccount(domainEvents);
    }

    @Override public List<DomainEvent> events(int id) {
        return requireNonNull(database.get(id), "BankAccount with id " + id + " is not found.");
    }
}
