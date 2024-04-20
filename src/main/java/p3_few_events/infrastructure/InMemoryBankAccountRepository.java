package p3_few_events.infrastructure;

import p3_few_events.domain.BankAccount;
import p3_few_events.domain.BankAccountRepository;
import p3_few_events.domain.events.DomainEvent;

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
        List<DomainEvent> domainEvents = allEventsFor(id);
        return new BankAccount(domainEvents);
    }

    @Override public List<DomainEvent> allEventsFor(int id) {
        return requireNonNull(database.get(id), "BankAccount with id " + id + " is not found.");
    }
}
