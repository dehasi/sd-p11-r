package p4_projection.application;

import p4_projection.domain.BankAccountService;
import p4_projection.domain.CashMovementsService;

import java.util.List;

public class UserInputController {
    private final BankAccountService bankAccountService;
    public final CashMovementsService cashMovementsService;

    public UserInputController(BankAccountService bankAccountService, CashMovementsService cashMovementsService) {
        this.bankAccountService = bankAccountService;
        this.cashMovementsService = cashMovementsService;
    }

    public String exec(String input) {
        assert input != null; // Input validators can be separate application services
        List<String> split = List.of(input.split("\\s+"));
        String command = split.get(0);

        switch (command) {
            case "create":
            case "c": {
                int id = Integer.parseInt(split.get(1));
                bankAccountService.create(id);
                return "created account, id " + id + ", balance $0";
            }
            case "balance":
            case "b": {
                int id = Integer.parseInt(split.get(1));
                int balance = bankAccountService.balance(id);
                return "account " + id + ", balance: $" + balance;
            }

            case "deposit":
            case "d": {
                int id = Integer.parseInt(split.get(1));
                int amount = Integer.parseInt(split.get(2));
                bankAccountService.deposit(id, amount);
                return "deposited: $" + amount + ", new balance: $" + bankAccountService.balance(id);
            }
            case "withdraw":
            case "w": {
                int id = Integer.parseInt(split.get(1));
                int amount = Integer.parseInt(split.get(2));
                bankAccountService.withdraw(id, amount);
                return "withdrew: $" + amount + ", new balance: $" + bankAccountService.balance(id);
            }
            case "fee":
            case "f": {
                int id = Integer.parseInt(split.get(1));
                int amount = Integer.parseInt(split.get(2));
                bankAccountService.fee(id, amount);
                return "fee: $" + amount + ", new balance: $" + bankAccountService.balance(id);
            }
            case "history":
            case "h": {
                int id = Integer.parseInt(split.get(1));
                return String.join("\n", cashMovementsService.history(id));
            }
            case "summary":
            case "s": {
                int id = Integer.parseInt(split.get(1));
                return cashMovementsService.summary(id);
            }
            case "help":
                return String.join("\n",
                        "(c)reate id",
                        "(b)alance id",
                        "(d)eposit id, amount",
                        "(w)ithdraw id, amount",
                        "(f)ee id, amount",
                        "(h)istory id",
                        "(s)ummary id");
            case null:
            default:
                return "Unknown command: " + input;
        }
    }
}
