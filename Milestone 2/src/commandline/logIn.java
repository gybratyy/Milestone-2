package commandline;
import Bank.bank_system;
public class logIn implements commands{
    @Override
    public void execute() throws Exception {
        bank_system.login();
    }
}
