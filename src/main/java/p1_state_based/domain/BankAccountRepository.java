package p1_state_based.domain;

public interface BankAccountRepository {

    void save(BankAccount bankAccount);

    BankAccount findById(int id);
}
