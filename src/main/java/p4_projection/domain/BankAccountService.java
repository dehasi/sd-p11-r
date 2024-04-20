package p4_projection.domain;

public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {this.repository = repository;}

    public void create(int id) {
        repository.save(new BankAccount(id));
    }

    public int balance(int id) {
        return repository.findById(id).balance;
    }

    public void deposit(int id, int amount) {
        BankAccount account = repository.findById(id);

        account.deposit(amount);

        repository.save(account);
    }

    public void withdraw(int id, int amount) {
        BankAccount account = repository.findById(id);

        account.withdraw(amount);

        repository.save(account);
    }

    public void fee(int id, int amount) {
        BankAccount account = repository.findById(id);

        account.fee(amount);

        repository.save(account);
    }
}
