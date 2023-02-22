package commandline;
import Bank.bank_system;
public class withdraw implements commands{
    @Override
    public void execute() throws Exception {
        bank_system.withdraw();
    }
}
