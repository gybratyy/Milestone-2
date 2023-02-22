package commandline;
import Bank.bank_system;
public class myProfile implements commands{
    @Override
    public void execute() throws Exception {
        bank_system.my_account();
    }
}
