package p2_introduce_events.domain;

import p2_introduce_events.domain.events.DomainEvent;

import java.util.List;

public interface BankAccountRepository {

    void append(int id, DomainEvent domainEvent);

    BankAccount findById(int id);

    List<DomainEvent> allEventsFor(int id);
}
