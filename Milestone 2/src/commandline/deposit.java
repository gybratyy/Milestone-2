package commandline;
import Bank.bank_system;
public class deposit implements commands {
    @Override
    public void execute() throws Exception {
        bank_system.deposit();
    }
}
