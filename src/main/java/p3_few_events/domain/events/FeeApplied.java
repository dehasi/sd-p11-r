package p3_few_events.domain.events;

public class FeeApplied implements DomainEvent {
    public final int amount;

    public FeeApplied(int amount) {
        this.amount = amount;
    }
}
