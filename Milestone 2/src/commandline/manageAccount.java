package commandline;
import Bank.bank_system;
public class manageAccount implements commands{
    @Override
    public void execute() throws Exception {
        bank_system.manage();
    }
}
