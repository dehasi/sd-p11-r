package p3_few_events.domain.events;

public class MoneyWithdrew implements DomainEvent {
    public final int amount;

    public MoneyWithdrew(int amount) {this.amount = amount;}
}
