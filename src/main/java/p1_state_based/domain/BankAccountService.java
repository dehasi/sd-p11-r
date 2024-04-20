package p1_state_based.domain;

public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {this.repository = repository;}

    public void create(int id) {
        BankAccount bankAccount = new BankAccount(id, 0);
        repository.save(bankAccount);
    }

    public int balance(int id) {
        return repository.findById(id).balance;
    }

    public void deposit(int id, int amount) {
        BankAccount account = repository.findById(id);

        BankAccount newAccount = account.deposit(amount);

        repository.save(newAccount);
    }

    public void withdraw(int id, int amount) {
        BankAccount account = repository.findById(id);

        BankAccount newAccount = account.withdraw(amount);

        repository.save(newAccount);
    }

    public void fee(int id, int amount) {
        withdraw(id, amount);
    }
}
