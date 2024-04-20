package p4_projection.domain.events;

public class AccountCreated implements DomainEvent {
    public final int id;

    public AccountCreated(int id) {this.id = id;}
}
