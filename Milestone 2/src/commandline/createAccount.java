package commandline;
import Bank.bank_system;
public class createAccount implements commands{
    @Override
    public void execute() throws Exception {
        bank_system.create_user();
    }
}
