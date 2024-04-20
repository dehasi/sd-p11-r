package p2_introduce_events.domain;

import p2_introduce_events.domain.events.*;

import java.util.List;

public class BankAccount {
    public int id;
    public int balance;

    public BankAccount(List<DomainEvent> events) {
        for (var event : events) {
            apply(event);
        }
    }

    public DomainEvent deposit(int amount) {
        assert amount > 0;
        return new MoneyDeposited(amount);
    }

    public DomainEvent withdraw(int amount) {
        assert amount > 0;
        return new MoneyWithdrew(amount);
    }

    public DomainEvent fee(int amount) {
        assert amount > 0;
        return new FeeApplied(amount);
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
