package p2_introduce_events.domain.events;

public class MoneyWithdrew implements DomainEvent{
    public final int amount;

    public MoneyWithdrew(int amount) {this.amount = amount;}
}
