package p4_projection.domain;

import p4_projection.domain.events.DomainEvent;

import java.util.List;

public interface BankAccountRepository {

    void save(BankAccount bankAccount);

    BankAccount findById(int id);

    List<DomainEvent> events(int id);
}
