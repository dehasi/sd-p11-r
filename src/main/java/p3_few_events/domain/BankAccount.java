package p3_few_events.domain;

import p3_few_events.domain.events.*;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    public int id;
    public int balance;
    public List<DomainEvent> events = new ArrayList<>();

    BankAccount(int id) {
        causes(new AccountCreated(id));
    }

    public BankAccount(List<DomainEvent> events) {
        for (var event : events) {
            apply(event);
        }
    }

    public void deposit(int amount) {
        assert amount > 0;
        causes(new MoneyDeposited(amount));
        if (amount > 25)
            causes(new FeeApplied(1));
    }

    public void withdraw(int amount) {
        assert amount > 0;
        causes(new MoneyWithdrew(amount));
    }

    public void fee(int amount) {
        assert amount > 0;
        causes(new FeeApplied(amount));
    }

    private void causes(DomainEvent event) {
        events.add(event);
        apply(event);
    }

    private void apply(DomainEvent event) {
        switch (event) {
            case AccountCreated e -> when(e);
            case MoneyDeposited e -> when(e);
            case MoneyWithdrew e -> when(e);
            case FeeApplied e -> when(e);
            default -> throw new IllegalStateException("Unexpected event: " + event.getClass().getSimpleName());
        }
    }

    private void when(FeeApplied event) {
        balance -= event.amount;
    }

    private void when(MoneyWithdrew event) {
        balance -= event.amount;
    }

    private void when(MoneyDeposited event) {
        balance += event.amount;
    }

    private void when(AccountCreated event) {
        id = event.id;
    }
}
