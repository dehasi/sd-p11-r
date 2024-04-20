package p4_projection.domain.events;

public class MoneyDeposited implements DomainEvent {
    public final int amount;

    public MoneyDeposited(int amount) {this.amount = amount;}
}
