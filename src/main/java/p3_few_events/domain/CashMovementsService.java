package p3_few_events.domain;

import p3_few_events.domain.BankAccountRepository;
import p3_few_events.domain.events.*;

import java.util.ArrayList;
import java.util.List;

public class CashMovementsService {

    private final BankAccountRepository repository;

    public CashMovementsService(BankAccountRepository repository) {this.repository = repository;}

    public List<String> history(int id) {
        List<DomainEvent> events = repository.allEventsFor(id);
        List<String> history = new ArrayList<>();
        for (DomainEvent event : events) {
            switch (event) {
                case AccountCreated e -> history.add("created, id:" + e.id);
                case MoneyDeposited e -> history.add("deposited: $" + e.amount);
                case MoneyWithdrew e -> history.add("withdrew: $" + e.amount);
                case FeeApplied e -> history.add("fee: $" + e.amount);
                default -> throw new IllegalStateException("Unexpected event: " + event.getClass().getSimpleName());
            }
        }
        return history;
    }
}
