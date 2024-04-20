package p1_state_based.domain;

public class BankAccount {
    public final int id;
    public final int balance;

    public BankAccount(int id, int balance) {
        assert balance >= 0;
        this.id = id;
        this.balance = balance;
    }

    public BankAccount deposit(int amount) {
        assert amount > 0;
        return new BankAccount(id, balance + amount);
    }

    public BankAccount withdraw(int amount) {
        assert amount > 0;
        return new BankAccount(id, balance - amount);
    }
}
