package p5_storage.domain;

import p5_storage.domain.events.DomainEvent;

import java.util.List;

public interface BankAccountRepository {

    void save(BankAccount bankAccount);

    BankAccount findById(int id);

    List<DomainEvent> events(int id);
}
