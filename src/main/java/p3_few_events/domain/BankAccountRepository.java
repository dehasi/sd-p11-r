package p3_few_events.domain;

import p3_few_events.domain.events.DomainEvent;

import java.util.List;

public interface BankAccountRepository {

    void save(BankAccount bankAccount);

    BankAccount findById(int id);

    List<DomainEvent> allEventsFor(int id);
}
