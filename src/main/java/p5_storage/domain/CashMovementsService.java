package p5_storage.domain;

import p5_storage.domain.events.*;

import java.util.ArrayList;
import java.util.List;

public class CashMovementsService {

    private final BankAccountRepository repository;

    public CashMovementsService(BankAccountRepository repository) {this.repository = repository;}

    public List<String> history(int id) {
        List<DomainEvent> events = repository.events(id);
        List<String> history = new ArrayList<>();
        for (DomainEvent event : events) {
            switch (event) {
                case AccountCreated e -> history.add("created, id:" + e.id());
                case MoneyDeposited e -> history.add("deposited: $" + e.amount());
                case MoneyWithdrew e -> history.add("withdrew: $" + e.amount());
                case FeeApplied e -> history.add("fee: $" + e.amount());
                default -> throw new IllegalStateException("Unexpected event: " + event.getClass().getSimpleName());
            }
        }
        return history;
    }

    public String summary(int id) {
        List<DomainEvent> events = repository.events(id);
        int deposit = 0, withdrawal = 0, fee = 0;
        for (DomainEvent event : events) {
            switch (event) {
                case AccountCreated e -> {} // skip
                case MoneyDeposited e -> deposit += e.amount();
                case MoneyWithdrew e -> withdrawal += e.amount();
                case FeeApplied e -> fee += e.amount();
                default -> throw new IllegalStateException("Unexpected event: " + event.getClass().getSimpleName());
            }
        }
        return String.format("id: %s\ntotal deposited: $%s\ntotal withdrew: $%s\ntotal fees: $%s",
                id, deposit, withdrawal, fee);
    }
}
