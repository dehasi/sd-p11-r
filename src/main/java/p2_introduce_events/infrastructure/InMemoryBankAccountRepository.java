package p2_introduce_events.infrastructure;

import p2_introduce_events.domain.BankAccount;
import p2_introduce_events.domain.BankAccountRepository;
import p2_introduce_events.domain.events.DomainEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class InMemoryBankAccountRepository implements BankAccountRepository {

    private final Map<Integer, List<DomainEvent>> database = new HashMap<>();

    @Override public void append(int id, DomainEvent domainEvent) {
        database.putIfAbsent(id, new ArrayList<>());
        database.get(id).add(domainEvent);
    }

    @Override public BankAccount findById(int id) {
        List<DomainEvent> domainEvents = allEventsFor(id);
        return new BankAccount(domainEvents);
    }

    @Override public List<DomainEvent> allEventsFor(int id) {
        return  requireNonNull(database.get(id), "BankAccount with id " + id + " is not found.");
    }
}
