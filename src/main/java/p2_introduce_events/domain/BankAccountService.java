package p2_introduce_events.domain;

import p2_introduce_events.domain.events.AccountCreated;
import p2_introduce_events.domain.events.DomainEvent;

public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {this.repository = repository;}

    public void create(int id) {
        repository.append(id, new AccountCreated(id));
    }

    public int balance(int id) {
        return repository.findById(id).balance;
    }

    public void deposit(int id, int amount) {
        BankAccount account = repository.findById(id);

        DomainEvent event = account.deposit(amount);

        repository.append(id, event);
    }

    public void withdraw(int id, int amount) {
        BankAccount account = repository.findById(id);

        DomainEvent event = account.withdraw(amount);

        repository.append(id, event);
    }

    public void fee(int id, int amount) {
        BankAccount account = repository.findById(id);

        DomainEvent event = account.fee(amount);

        repository.append(id, event);
    }
}
