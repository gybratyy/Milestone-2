package commandline;
import Bank.bank_system;
public class transfer implements commands{
    @Override
    public void execute() throws Exception {
        bank_system.transfer();
    }
}
