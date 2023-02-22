package commandline;
import Bank.bank_system;

public class closeAccount implements commands {
    @Override
    public void execute() throws Exception {
        bank_system.close_account();
    }
}

