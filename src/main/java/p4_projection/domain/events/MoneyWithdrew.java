package p4_projection.domain.events;

public class MoneyWithdrew implements DomainEvent {
    public final int amount;

    public MoneyWithdrew(int amount) {this.amount = amount;}
}
