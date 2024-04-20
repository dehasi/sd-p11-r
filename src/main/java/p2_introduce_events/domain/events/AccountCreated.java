package p2_introduce_events.domain.events;

public class AccountCreated implements DomainEvent {
    public final int id;

    public AccountCreated(int id) {this.id = id;}
}
