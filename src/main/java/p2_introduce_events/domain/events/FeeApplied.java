package p2_introduce_events.domain.events;

public class FeeApplied implements DomainEvent {
    public final int amount;

    public FeeApplied(int amount) {
        this.amount = amount;
    }
}
